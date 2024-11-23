package me.ustory.api.member.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {
    private final MemberId memberId;

    private final Email email;

    private final Name name;

    private final Nickname nickname;

    private final Password password;

    private final ProfileImage profileImage;

    private final ProfileDescription profileDescription;

    private LocalDateTime deletedAt;

    @Builder
    private Member(MemberId memberId, Email email, Name name, Nickname nickname, Password password, ProfileImage profileImage, ProfileDescription profileDescription) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.profileImage = profileImage;
        this.profileDescription = profileDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId) && Objects.equals(email, member.email) && Objects.equals(name, member.name) && Objects.equals(nickname, member.nickname)
                && Objects.equals(password, member.password) && Objects.equals(profileImage, member.profileImage) && Objects.equals(profileDescription, member.profileDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, email, name, nickname, password, profileImage, profileDescription);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", email=" + email +
                ", name=" + name +
                ", nickname=" + nickname +
                ", password=" + password +
                ", profileImage=" + profileImage +
                ", profileDescription=" + profileDescription +
                '}';
    }
}
