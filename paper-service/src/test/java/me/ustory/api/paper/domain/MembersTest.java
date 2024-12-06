package me.ustory.api.paper.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MembersTest {

    @DisplayName("Member가 포함되어 있는지 확인한다.")
    @Test
    void isContains() {
        // given
        MemberId memberId = MemberId.of(1L);
        MemberId falseMemberId = MemberId.of(3L);
        Members members = Members.of(List.of(memberId, MemberId.of(2L)));

        // when & then
        assertThat(members.isContains(memberId)).isTrue();
        assertThat(members.isContains(falseMemberId)).isFalse();
    }

    @DisplayName("Member가 1명인지 확인한다.")
    @Test
    void isIndividual() {
        // given
        Members individualMembers = Members.of(List.of(MemberId.of(1L)));
        Members members = Members.of(List.of(MemberId.of(1L), MemberId.of(2L)));

        // when & then
        assertThat(individualMembers.isIndividual()).isTrue();
        assertThat(members.isIndividual()).isFalse();
    }

    @DisplayName("Member는 1명 이상이어야 한다.")
    @Test
    void validate() {
        // given
        List<MemberId> emptyMembers = List.of();

        // when & then
        assertThatThrownBy(() -> Members.of(emptyMembers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Member는 1명 이상이어야 합니다.");
    }

}