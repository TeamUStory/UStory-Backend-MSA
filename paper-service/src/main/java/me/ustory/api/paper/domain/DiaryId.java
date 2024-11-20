package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class DiaryId {

    private final Long value;

    public static DiaryId of(Long value) {
        return new DiaryId(value);
    }

    private DiaryId(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryId diaryId = (DiaryId) o;
        return Objects.equals(value, diaryId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "DiaryId{" +
            "value=" + value +
            '}';
    }
}
