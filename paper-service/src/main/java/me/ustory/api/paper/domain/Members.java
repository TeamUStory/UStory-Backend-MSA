package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Members {

    private final List<MemberId> memberIds;

    public static Members of(List<MemberId> memberIds) {
        return new Members(memberIds);
    }

    public boolean isContains(MemberId memberId) {
        return memberIds.contains(memberId);
    }

    public boolean isIndividual() {
        return memberIds.size() <= 1;
    }

    private Members(List<MemberId> memberIds) {
        validate(memberIds);
        this.memberIds = memberIds;
    }

    private void validate(List<MemberId> memberIds) {
        if (memberIds.isEmpty()) {
            throw new IllegalArgumentException("Member는 1명 이상이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Members that = (Members) o;
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
