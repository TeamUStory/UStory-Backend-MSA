package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class MemberInfo {

    private Long id;

    // TODO: Paper 애그리게이트에는 사용자 닉네임이 필요하다.

    public static MemberInfo of(Long id) {
        return new MemberInfo(id);
    }

    private MemberInfo(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberInfo that = (MemberInfo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
            "id=" + id +
            '}';
    }
}
