package me.ustory.api.paper.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.paper.domain.DiaryInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DiaryInfoMapper {

    public static DiaryInfoEntity mapToEntity(DiaryInfo diaryInfo) {
        return DiaryInfoEntity.of(
            diaryInfo.getId(),
            diaryInfo.getName(),
            diaryInfo.getImage().getUrl(),
            diaryInfo.getColor(),
            diaryInfo.getMarker().getUrl()
        );
    }

    public static DiaryInfo mapToDomain(DiaryInfoEntity diaryInfoEntity) {
        return DiaryInfo.of(
            diaryInfoEntity.getId(),
            diaryInfoEntity.getName(),
            diaryInfoEntity.getImageUrl(),
            diaryInfoEntity.getColor(),
            diaryInfoEntity.getMarkerUrl()
        );
    }

}
