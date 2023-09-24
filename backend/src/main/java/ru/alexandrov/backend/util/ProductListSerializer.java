package ru.alexandrov.backend.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.alexandrov.backend.models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductListSerializer extends StdSerializer<List<Product>> {
    public ProductListSerializer(){
        this(null);
    }

    public ProductListSerializer(Class<List<Product>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Product> products, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<String> names = new ArrayList<>();
        for (Product product : products) {
            names.add(product.getName());
        }
        jsonGenerator.writeObject(names);
    }
}
