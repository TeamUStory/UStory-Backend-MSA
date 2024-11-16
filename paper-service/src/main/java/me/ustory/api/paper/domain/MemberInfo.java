package me.ustory.api.paper.domain;

import lombok.Getter;

@Getter
public class MemberInfo {

    private Long id;

    // TODO: Paper 애그리게이트에는 사용자 닉네임이 필요하다.

    public static MemberInfo of(Long id) {
        return new MemberInfo(id);
    }

    private MemberInfo(Long id) {
        this.id = id;
    }

}
