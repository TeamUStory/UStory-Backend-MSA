package me.ustory.api.paper.adapter.out.feign;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.common.feign.DiaryFeignDTO;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.Members;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryFeignMapper {

    public static DiaryInfo mapToDomain(DiaryFeignDTO diaryFeignDTO) {
        return DiaryInfo.of(
            DiaryId.of(diaryFeignDTO.diaryId()),
            Members.of(diaryFeignDTO.membersId().stream()
                .map(MemberId::of).toList()),
            diaryFeignDTO.name(),
            diaryFeignDTO.imageUrl(),
            diaryFeignDTO.color(),
            diaryFeignDTO.markerUrl()
        );
    }

}
