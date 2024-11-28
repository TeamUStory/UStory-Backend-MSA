package me.ustory.api.paper.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @DisplayName("Color의 값은 비어있을 수 없다.")
    @Test
    void validateWhenBlank() {
        // given
        String value = " ";

        // when & then
        assertThatThrownBy(() -> Color.of(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("값이 비어있습니다.");
    }

    @DisplayName("Color의 값은 Hex Code 형식이어야 한다.")
    @Test
    void validateWhenInvalidHexCode() {
        // given
        String value1 = "0000";
        String value2 = "#0000";

        // when & then
        assertThatThrownBy(() -> Color.of(value1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("HEX Code 형식에 일치하지 않습니다.");

        assertThatThrownBy(() -> Color.of(value2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("HEX Code 형식에 일치하지 않습니다.");
    }
}