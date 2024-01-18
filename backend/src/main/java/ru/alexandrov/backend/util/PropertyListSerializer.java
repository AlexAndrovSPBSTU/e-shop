package ru.alexandrov.backend.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.alexandrov.backend.dto.PropertyDTO;

import java.io.IOException;
import java.util.*;

public class PropertyListSerializer extends StdSerializer<List<PropertyDTO>> {
    public PropertyListSerializer() {
        this(null);
    }

    protected PropertyListSerializer(Class<List<PropertyDTO>> t) {
        super(t);
    }


    @Override
    public void serialize(List<PropertyDTO> properties, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Map<String, Set<String>> map = new HashMap<>();
        for (PropertyDTO property : properties) {
            map.computeIfAbsent(property.getCharacteristic().getName(),
                    key -> new HashSet<>()).add(property.getValue());
        }
        jsonGenerator.writeObject(map);
    }
}
