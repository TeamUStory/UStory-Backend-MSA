package me.ustory.api.comment.adapter.out.feign;

import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.MemberInfo;
import me.ustory.api.common.feign.MemberFeignDTO;
import me.ustory.api.common.vo.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberFeignAdapterTest {

    @Mock
    private MemberFeignClient memberFeignClient;

    @InjectMocks
    private MemberFeignAdapter memberFeignAdapter;

    @DisplayName("Member 정보를 불러온다.")
    @Test
    void getMemberInfo() {
        // given
        Long memberId = 1L;
        String nickname = "닉네임";
        String profileImage = "https://www.example.com/프로필.png";
        MemberFeignDTO response = new MemberFeignDTO(memberId, nickname, profileImage);
        given(memberFeignClient.findMemberById(memberId)).willReturn(response);

        // when
        MemberInfo memberInfo = memberFeignAdapter.getMemberInfoById(MemberId.of(memberId));

        // then
        assertThat(memberInfo.getId()).isEqualTo(MemberId.of(memberId));
        assertThat(memberInfo.getNickname()).isEqualTo(nickname);
        assertThat(memberInfo.getProfile()).isEqualTo(Image.of(profileImage));

        verify(memberFeignClient).findMemberById(memberId);
    }
}