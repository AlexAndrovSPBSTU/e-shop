package ru.alexandrov.backend.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.alexandrov.backend.models.Category;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryListSerializer extends StdSerializer<List<Category>> {
    public CategoryListSerializer() {
        this(null);
    }

    protected CategoryListSerializer(Class<List<Category>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Category> categories, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Map<Integer,String> map = new HashMap<>();
        for (Category category : categories) {
            map.put(category.getId(), category.getName());
        }
        jsonGenerator.writeObject(map);
    }
}
