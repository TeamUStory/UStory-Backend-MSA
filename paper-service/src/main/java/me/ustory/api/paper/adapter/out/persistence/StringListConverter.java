package me.ustory.api.paper.adapter.out.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {


    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }

        return String.join(",", attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        if (s == null || s.isBlank()) {
            return List.of();
        }
        return Arrays.asList(s.split(","));
    }
}
