package me.ustory.api.paper.adapter.out.persistence;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class MembersInfoEntity {

    @ElementCollection
    @CollectionTable(name = "members_info_entity", joinColumns = @JoinColumn(name = "diary_id"))
    @Column(name = "member_id")
    private List<Long> memberIds;

    public static MembersInfoEntity of(List<Long> memberIds) {
        return new MembersInfoEntity(memberIds);
    }

    private MembersInfoEntity(List<Long> memberIds) {
        this.memberIds = memberIds;
    }

    public List<Long> getMemberIds() {
        return Collections.unmodifiableList(memberIds);
    }

}
