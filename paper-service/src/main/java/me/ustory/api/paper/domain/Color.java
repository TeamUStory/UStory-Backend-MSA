package me.ustory.api.paper.domain;

import java.util.Objects;

public class Color {

    private final String value;

    public static Color of(String value) {
        return new Color(value);
    }

    public String getValue() {
        return this.value;
    }

    private Color(String value) {
        this.value = validate(value);
    }

    private String validate(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("값이 비어있습니다.");
        }

        if (!value.startsWith("#") || value.length() != 7) {
            throw new IllegalArgumentException("HEX Code 형식에 일치하지 않습니다.");
        }

        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return Objects.equals(value, color.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Color{" +
            "value='" + value + '\'' +
            '}';
    }
}
