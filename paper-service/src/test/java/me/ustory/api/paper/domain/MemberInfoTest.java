package me.ustory.api.paper.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberInfoTest {

    @DisplayName("Member가 포함되어 있는지 확인한다.")
    @Test
    void isContains() {
        // given
        MemberId memberId = MemberId.of(1L);
        MemberId falseMemberId = MemberId.of(3L);
        MemberInfo memberInfo = MemberInfo.of(List.of(memberId, MemberId.of(2L)));

        // when & then
        assertThat(memberInfo.isContains(memberId)).isTrue();
        assertThat(memberInfo.isContains(falseMemberId)).isFalse();
    }

    @DisplayName("Member가 1명인지 확인한다.")
    @Test
    void isIndividual() {
        // given
        MemberInfo individualMemberInfo = MemberInfo.of(List.of(MemberId.of(1L)));
        MemberInfo memberInfo = MemberInfo.of(List.of(MemberId.of(1L), MemberId.of(2L)));

        // when & then
        assertThat(individualMemberInfo.isIndividual()).isTrue();
        assertThat(memberInfo.isIndividual()).isFalse();
    }

    @DisplayName("Member는 1명 이상이어야 한다.")
    @Test
    void validate() {
        // given
        List<MemberId> emptyMembers = List.of();

        // when & then
        assertThatThrownBy(() -> MemberInfo.of(emptyMembers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Member는 1명 이상이어야 합니다.");
    }

}