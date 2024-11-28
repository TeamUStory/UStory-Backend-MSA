package me.ustory.api.paper.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.MemberInfo;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DiaryInfoMapper {

    public static DiaryInfoEntity mapToEntity(DiaryInfo diaryInfo) {
        return DiaryInfoEntity.of(
            diaryInfo.getId().getValue(),
            mapToMembersInfoEntity(diaryInfo.getMemberInfo().getMemberIds()),
            diaryInfo.getName(),
            diaryInfo.getImage().getUrl(),
            diaryInfo.getColor().getValue(),
            diaryInfo.getMarker().getUrl()
        );
    }

    public static DiaryInfo mapToDomain(DiaryInfoEntity diaryInfoEntity) {
        return DiaryInfo.of(
            DiaryId.of(diaryInfoEntity.getId()),
            mapToMemberIdsDomain(diaryInfoEntity.getMembers()),
            diaryInfoEntity.getName(),
            diaryInfoEntity.getImageUrl(),
            diaryInfoEntity.getColor(),
            diaryInfoEntity.getMarkerUrl()
        );
    }

    private static MembersInfoEntity mapToMembersInfoEntity(List<MemberId> memberIds) {
        return MembersInfoEntity.of(
            memberIds.stream().map(MemberId::getValue).toList()
        );
    }

    private static MemberInfo mapToMemberIdsDomain(MembersInfoEntity membersInfoEntity) {
        return MemberInfo.of(membersInfoEntity.getMemberIds().stream().map(MemberId::of).toList());
    }

}
