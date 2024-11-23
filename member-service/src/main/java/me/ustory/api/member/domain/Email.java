package me.ustory.api.member.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Email {
    private final String email;

    private Email(String email) {this.email = email;}

    public static Email of(String email) {
        return new Email(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email other = (Email) o;
        return Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "Email{" +
                "email=" + email +
                '}';
    }
}
