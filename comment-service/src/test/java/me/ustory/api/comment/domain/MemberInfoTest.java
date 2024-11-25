package me.ustory.api.comment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberInfoTest {

    @DisplayName("Member Id가 동일한지 확인할 수 있다.")
    @Test
    void isNotSameMember() {
        // given
        MemberId memberId = MemberId.of(1L);
        MemberId differentMemberId = MemberId.of(2L);
        MemberInfo memberInfo = MemberInfo.of(memberId, "닉네임", Image.of("https://www.example.com/이미지.png"));

        // when
        boolean isSameMember = memberInfo.isNotSameMember(memberId);
        boolean isNotSameMember = memberInfo.isNotSameMember(differentMemberId);

        // then
        assertThat(isSameMember).isFalse();
        assertThat(isNotSameMember).isTrue();
    }
}