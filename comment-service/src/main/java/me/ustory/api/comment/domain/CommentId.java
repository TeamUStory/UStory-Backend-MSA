package me.ustory.api.comment.domain;

import java.util.Objects;

public class CommentId {

    private final Long value;

    public static CommentId of(Long value) {
        return new CommentId(value);
    }

    public Long getValue() {
        return value;
    }

    private CommentId(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return Objects.equals(value, commentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "CommentId{" +
            "value=" + value +
            '}';
    }
}
