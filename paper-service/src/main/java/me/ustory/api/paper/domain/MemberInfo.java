package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class MemberInfo {

    private final List<MemberId> memberIds;

    public static MemberInfo of(List<MemberId> memberIds) {
        return new MemberInfo(memberIds);
    }

    private MemberInfo(List<MemberId> memberIds) {
        this.memberIds = memberIds;
    }

    public boolean isIndividual() {
        return memberIds.size() <= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberInfo that = (MemberInfo) o;
        return Objects.equals(memberIds, that.memberIds);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(memberIds);
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
            "memberIds=" + memberIds +
            '}';
    }

}
