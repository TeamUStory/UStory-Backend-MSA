package me.ustory.api.paper.adapter.out.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringListConverterTest {

    private final StringListConverter converter = new StringListConverter();

    @DisplayName("String 리스트를 쉼표를 기준으로 String으로 변환한다.")
    @Test
    void convertToDatabaseColumn() {
        List<String> list = List.of(
            "https://example.com/image1.jpg",
            "https://example.com/image2.png"
        );

        String result = converter.convertToDatabaseColumn(list);
        assertThat(result).isEqualTo("https://example.com/image1.jpg,https://example.com/image2.png", result);
    }

    @DisplayName("String 리스트가 비어있다면, null을 반환한다.")
    @Test
    void convertToDatabaseColumnWhenEmpty() {
        List<String> list = List.of(

        );

        String result = converter.convertToDatabaseColumn(list);
        assertThat(result).isNull();
    }

    @DisplayName("쉼표를 기준으로 String을 List<String>으로 변환한다.")
    @Test
    void convertToEntityAttribute() {
        String s = "https://example.com/image1.jpg,https://example.com/image2.png";

        List<String> result = converter.convertToEntityAttribute(s);

        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo("https://example.com/image1.jpg");
        assertThat(result.get(1)).isEqualTo("https://example.com/image2.png");
     }

    @DisplayName("String이 비어있다면, 빈 List를 반환한다.")
    @Test
    void convertToEntityAttributeWhenBlank() {
        String s = " ";

        List<String> result = converter.convertToEntityAttribute(s);

        assertThat(result).isEmpty();
    }
}