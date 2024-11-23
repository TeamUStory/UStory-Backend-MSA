package me.ustory.api.member.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ProfileDescription {
    private final String profileDescription;

    private ProfileDescription(String profileDescription) {this.profileDescription = profileDescription;}

    public static ProfileDescription of(String profileDescription) {
        return new ProfileDescription(profileDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDescription other = (ProfileDescription) o;
        return Objects.equals(profileDescription, other.profileDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(profileDescription);
    }

    @Override
    public String toString() {
        return "ProfileDescription{" +
                "profileDescription=" + profileDescription +
                '}';
    }
}
