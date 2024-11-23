package me.ustory.api.member.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Password {
    private final String password;

    private Password(String password) {this.password = password;}

    public static Password of(String password) {
        return new Password(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password other = (Password) o;
        return Objects.equals(password, other.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(password);
    }

    @Override
    public String toString() {
        return "Password{" +
                "password=" + password +
                '}';
    }
}
