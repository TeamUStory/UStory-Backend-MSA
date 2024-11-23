package me.ustory.api.comment.domain;

import java.util.Objects;

public class PaperId {

    private final Long value;

    public static PaperId of(Long value) {
        return new PaperId(value);
    }

    public Long getValue() {
        return value;
    }

    private PaperId(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperId paperId = (PaperId) o;
        return Objects.equals(value, paperId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "PaperId{" +
            "value=" + value +
            '}';
    }
}
