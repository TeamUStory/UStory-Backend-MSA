package me.ustory.api.comment.domain;

import lombok.Getter;

@Getter
public class MemberInfo {

    private final MemberId id;

    private final String nickname;

    private final Image profile;

    public static MemberInfo of(MemberId id, String nickname, Image profile) {
        return new MemberInfo(id, nickname, profile);
    }

    private MemberInfo(MemberId id, String nickname, Image profile) {
        this.id = id;
        this.nickname = validateNickname(nickname);
        this.profile = validateProfile(profile);
    }

    private String validateNickname(String nickname) {
        if (nickname.isBlank()) {
            throw new IllegalArgumentException("nickname is blank");
        }

        return nickname;
    }

    private Image validateProfile(Image profile) {
        if (profile == null) {
            throw new IllegalArgumentException("profile is null");
        }

        return profile;
    }
}
