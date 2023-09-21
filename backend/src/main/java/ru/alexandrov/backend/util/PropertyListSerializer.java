package ru.alexandrov.backend.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.alexandrov.backend.models.Property;

import java.io.IOException;
import java.util.*;

public class PropertyListSerializer extends StdSerializer<List<Property>> {
    public PropertyListSerializer() {
        this(null);
    }
    protected PropertyListSerializer(Class<List<Property>> t) {
        super(t);
    }


    @Override
    public void serialize(List<Property> properties, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Map<String, Set<String>> map = new HashMap<>();
        for (Property property : properties) {
            map.computeIfAbsent(property.getCharacteristic().getName(),
                    key -> new HashSet<>()).add(property.getValue());
        }
        jsonGenerator.writeObject(map);
    }
}
