package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class MemberId {

    private final Long value;

    public static MemberId of(Long value) {
        return new MemberId(value);
    }

    private MemberId(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberId memberId = (MemberId) o;
        return Objects.equals(value, memberId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "MemberId{" +
            "value=" + value +
            '}';
    }
}
