package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class PaperId {

    private Long id;

    public static PaperId of(Long id) {
        return new PaperId(id);
    }

    private PaperId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperId paperId = (PaperId) o;
        return Objects.equals(id, paperId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PaperId{" +
            "id=" + id +
            '}';
    }
}
