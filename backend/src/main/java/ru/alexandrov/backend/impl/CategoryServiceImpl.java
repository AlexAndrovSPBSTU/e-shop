package ru.alexandrov.backend.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.alexandrov.backend.constants.ProjectConstants;
import ru.alexandrov.backend.dto.CategoryDTO;
import ru.alexandrov.backend.dto.CharacteristicDTO;
import ru.alexandrov.backend.dto.ProductDTO;
import ru.alexandrov.backend.models.Category;
import ru.alexandrov.backend.models.Characteristic;
import ru.alexandrov.backend.models.Product;
import ru.alexandrov.backend.models.Property;
import ru.alexandrov.backend.repositories.CategoryRepository;
import ru.alexandrov.backend.repositories.CharacteristicRepository;
import ru.alexandrov.backend.repositories.ProductRepository;
import ru.alexandrov.backend.services.CategoryService;
import ru.alexandrov.backend.util.ProductSorting;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CharacteristicRepository characteristicRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, CharacteristicRepository characteristicRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.characteristicRepository = characteristicRepository;
        this.modelMapper = modelMapper;
    }

    public List<CategoryDTO> getRootCategories() {
        return categoryRepository.getRootCategories().stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }


    public List<ProductDTO> getCategoryProducts(int id, Integer page, Integer order, String price, String[] filter) {
        //price это isRange хар-ка. Примеры
        //http://localhost:8080/categories/:categoryId/products?price=10-150 - price от 10 до 150
        //http://localhost:8080/categories/:categoryId/products?price=10-_ - price от 10
        //http://localhost:8080/categories/:categoryId/products?price=_-150 - price до 150
        double from = 0, to = Double.MAX_VALUE;
        if (price != null) {
            String[] range = price.split("-");
            from = Objects.equals(range[0], String.valueOf('_')) ? from : Double.parseDouble(range[0]);
            to = Objects.equals(range[1], String.valueOf('_')) ? to : Double.parseDouble(range[1]);
        }
        //Если order не был отправлен, ставим по умолчанию 4(Убывание по рейтингу)
        order = order == null ? 4 : order;

        //Ставим соответствующую сортировку
        ProductSorting productSorting = ProductSorting.BY_RATING;
        switch (order) {
            case 1:
                productSorting = ProductSorting.BY_PRICE_ASCENDING;
                break;
            case 2:
                productSorting = ProductSorting.BY_PRICE_DESCENDING;
                break;
            case 3:
                productSorting = ProductSorting.BY_DISCOUNT;
                break;
        }

        Category category = categoryRepository.findById(id).get();

        //Достаём все дочерние категории.
        List<Category> categories = category.getAllChildren();
        categories.add(category);

        page = (page == null) ? 1 : page;
        //Если мы отправили какие-то фильтры то применяем их.
        if (filter != null) {
            //Достаём отсортированные товары
            Sort sort = Sort.by(productSorting.getDirection(), productSorting.getProperty());

            //Выбираем только те из них, которые сответсвуют выбранными фильтрам
            return getProductsByProperties(productRepository.findAllByCategoryInAndPriceBetween(
                    sort, categories, from, to), filter, page)
                    .stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
        } else {
            PageRequest pageRequest = PageRequest.of(page - 1, ProjectConstants.PAGE_SIZE, Sort.by(productSorting.getDirection(), productSorting.getProperty()));
            return productRepository.findAllByCategoryInAndPriceBetween(pageRequest, categories, from, to).getContent()
                    .stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
        }
    }

    private List<Product> getProductsByProperties(List<Product> products, String[] filter, int page) {
        Stream<Product> productStream = products.stream();

        //Проходимся по всем фильтрам
        for (String q : filter) {
            String[] strings = q.split("-");
            String characteristicName = strings[0];
            Characteristic characteristic = characteristicRepository.findByName(characteristicName.replace('_', ' ')).get();

            //Если фильтр относится к isRange характеристике, проверяем, что указанное значение товара этой хар-ки находится в пределах [from,to].
            //Если у товара не выставлено значение характеристики, но мы проводим filter по нему, такой товар не выведется.
            if (characteristic.getIsRange()) {
                double from = 0, to = Double.MAX_VALUE;
                from = Objects.equals(strings[1], String.valueOf('_')) ? from : Double.parseDouble(strings[1]);
                to = Objects.equals(strings[2], String.valueOf('_')) ? to : Double.parseDouble(strings[2]);

                double finalFrom = from;
                double finalTo = to;
                productStream = productStream
                        .filter(product -> product
                                .getProperties()
                                .stream()
                                .filter(property -> property.getCharacteristic().equals(characteristic))
                                .anyMatch(property -> Double.parseDouble(property.getValue()) >= finalFrom && Double.parseDouble(property.getValue()) <= finalTo));
            } else {
                String[] propertiesId = new String[strings.length - 1];
                System.arraycopy(strings, 1, propertiesId, 0, strings.length - 1);
                productStream = productStream
                        .filter(product -> product
                                .getProperties()
                                .stream()
                                .map(Property::getId)
                                .anyMatch(id -> Arrays.stream(propertiesId).anyMatch(propertyId -> Integer.valueOf(propertyId).equals(id))));
            }
        }

        //Достаём товары, уже отсортированные и прошедшие фильтры
        List<Product> productList = productStream.collect(Collectors.toList());

        //Берём определённый промежуток товаров(page)
        int fromIndex = (page - 1) * ProjectConstants.PAGE_SIZE;
        int toIndex = page * ProjectConstants.PAGE_SIZE;
        if (fromIndex >= productList.size()) {
            return Collections.emptyList();
        }
        toIndex = Math.min(toIndex, productList.size());
        return productList.subList(fromIndex, toIndex);

    }

    public Set<CharacteristicDTO> getCharacteristicsByCategoryId(int id, Boolean isRange) {
        if (isRange == null) {
            return categoryRepository.findById(id).get().getCharacteristics()
                    .stream().map(characteristic -> modelMapper.map(characteristic, CharacteristicDTO.class)).collect(Collectors.toSet());
        } else {
            return categoryRepository.findById(id).get().getCharacteristics()
                    .stream()
                    .map(characteristic -> modelMapper.map(characteristic, CharacteristicDTO.class))
                    .filter(characteristic -> characteristic.getIsRange().equals(isRange))
                    .collect(Collectors.toSet());
        }
    }

    @Transactional
    public void save(CategoryDTO categoryDTO, int parentId) {
        Category category = categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
        categoryRepository.insert(category.getId(), parentId);
    }


    @Transactional
    public void deleteParentChildRelation(int child_id, int parent_id) {
        categoryRepository.deleteParentChildRelation(child_id, parent_id);
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    public void insert(int child_id, int parent) {
        categoryRepository.insert(child_id, parent);
    }

    @Transactional
    public void rename(int categoryId, String newName) {
        categoryRepository.rename(categoryId, newName);
    }
}
