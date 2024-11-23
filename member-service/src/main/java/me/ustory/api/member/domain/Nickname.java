package me.ustory.api.member.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Nickname {
    private final String nickname;

    private Nickname(String nickname) {this.nickname = nickname;}

    public static Nickname of(String nickname) {
        return new Nickname(nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nickname other = (Nickname) o;
        return Objects.equals(nickname, other.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }

    @Override
    public String toString() {
        return "Nickname{" +
                "nickname=" + nickname +
                '}';
    }
}
