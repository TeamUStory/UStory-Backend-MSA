package me.ustory.api.paper.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoordinateTest {

    @DisplayName("위도 좌표를 생성한다.")
    @Test
    void createLatitude() {
        // given
        Double x = 90.00;

        // when
        Coordinate coordinateX = Coordinate.latitude(x);

        // then
        assertThat(coordinateX.getValue()).isEqualTo(x);
    }

    @DisplayName("위도 좌표를 생성하는 경우, 위도 범위에 해당해야 한다.")
    @Test
    void createLatitudeWhenInvalid() {
        // given
        Double x = 90.01;
        Double x2 = -90.01;

        // when & then
        assertThatThrownBy( () -> Coordinate.latitude(x)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("위도는 -90 이상 90 이하여야 합니다.");
        assertThatThrownBy( () -> Coordinate.latitude(x2)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("위도는 -90 이상 90 이하여야 합니다.");
    }

    @DisplayName("경도 좌표를 생성한다.")
    @Test
    void createLongitude() {
        // given
        Double y = 180.00;

        // when
        Coordinate coordinateY = Coordinate.longitude(y);

        // then
        assertThat(coordinateY.getValue()).isEqualTo(y);
    }

    @DisplayName("경도 좌표를 생성하는 경우, 경도 범위에 해당해야 한다.")
    @Test
    void createLongitudeWhenInvalid() {
        // given
        Double y = 180.01;
        Double y2 = -180.01;

        // when & then
        assertThatThrownBy( () -> Coordinate.longitude(y)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경도는 -180 이상 180 이하여야 합니다.");
        assertThatThrownBy( () -> Coordinate.longitude(y2)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("경도는 -180 이상 180 이하여야 합니다.");
    }
}
