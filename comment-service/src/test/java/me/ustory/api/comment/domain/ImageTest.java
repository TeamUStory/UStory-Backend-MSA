package me.ustory.api.comment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @DisplayName("Image를 생성한다.")
    @ParameterizedTest(name = "{index}: Image 생성 테스트 [{0}]")
    @CsvSource({
        "https://www.image.png",
        "https://www.image.jpg",
        "https://www.image.gif",
        "https://www.image.jpeg",
        "http://www.image.png",
        "http://www.image.jpg",
        "http://www.image.gif",
        "http://www.image.jpeg"
    })
    void createImage(String imageUrl) {

        // when
        Image image = Image.of(imageUrl);

        // then
        assertThat(image.getUrl()).isEqualTo(imageUrl);
    }

    @DisplayName("ImageURL의 스킴이 잘못된 경우, 예외가 발생한다.")
    @Test
    void createImageWhenInvalidateScheme() {
        // given
        String imageUrl = "htp://www.이미지.png";

        // when & then
        assertThatThrownBy(() -> Image.of(imageUrl)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("URL은 http:// 또는 https://로 시작해야 합니다.");
    }

    @DisplayName("ImageURL의 이미지 확장자가 잘못된 경우, 예외가 발생한다.")
    @Test
    void createImageWhenInvalidateExtension() {
        // given
        String imageUrl = "https://www.이미지.pnge";

        // when & then
        assertThatThrownBy(() -> Image.of(imageUrl)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(".gif, .png, .jpg, .jpeg 이미지 확장자만 지원합니다.");

    }

}