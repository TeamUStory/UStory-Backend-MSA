package me.ustory.api.member.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Name {
    private final String name;

    private Name(String name) {this.name = name;}

    public static Name of(String name) {
        return new Name(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name other = (Name) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Name{" +
                "name=" + name +
                '}';
    }
}
