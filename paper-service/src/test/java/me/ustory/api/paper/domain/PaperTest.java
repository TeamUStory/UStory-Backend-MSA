package me.ustory.api.paper.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaperTest {

    @DisplayName("Paper는 처음 생성될 때, 잠금 상태이다.")
    @Test
    void createPaper() {
        // given & when
        Paper paper = Paper.builder().build();

        // then
        assertThat(paper.isLocked()).isTrue();
    }

}
