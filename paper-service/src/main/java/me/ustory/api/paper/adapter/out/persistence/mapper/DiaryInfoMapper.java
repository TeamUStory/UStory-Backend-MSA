package me.ustory.api.paper.adapter.out.persistence.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.common.vo.Color;
import me.ustory.api.paper.adapter.out.persistence.entity.DiaryInfoEntity;
import me.ustory.api.paper.adapter.out.persistence.entity.MembersInfoEntity;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.Members;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryInfoMapper {

    public static DiaryInfoEntity mapToEntity(DiaryInfo diaryInfo) {
        return DiaryInfoEntity.of(
            diaryInfo.getId().getValue(),
            mapToMembersInfoEntity(diaryInfo.getMembers().getMemberIds()),
            diaryInfo.getName(),
            ImageMapper.mapToJpaEntity(diaryInfo.getImage()),
            diaryInfo.getColor().getValue(),
            ImageMapper.mapToJpaEntity(diaryInfo.getMarker())
        );
    }

    public static DiaryInfo mapToDomain(DiaryInfoEntity diaryInfoEntity) {
        return DiaryInfo.of(
            DiaryId.of(diaryInfoEntity.getId()),
            mapToMemberIdsDomain(diaryInfoEntity.getMembers()),
            diaryInfoEntity.getName(),
            ImageMapper.mapToDomain(diaryInfoEntity.getImage()),
            Color.of(diaryInfoEntity.getColor()),
            ImageMapper.mapToDomain(diaryInfoEntity.getMarker())
        );
    }

    private static MembersInfoEntity mapToMembersInfoEntity(List<MemberId> memberIds) {
        return MembersInfoEntity.of(
            memberIds.stream().map(MemberId::getValue).toList()
        );
    }

    private static Members mapToMemberIdsDomain(MembersInfoEntity membersInfoEntity) {
        return Members.of(membersInfoEntity.getMemberIds().stream().map(MemberId::of).toList());
    }

}
