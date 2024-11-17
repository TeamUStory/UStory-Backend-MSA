package me.ustory.api.paper.adapter.out.feign;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.paper.domain.DiaryInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryFeignMapper {

    public static DiaryInfo mapToDomain(DiaryFeignResponse diaryFeignResponse) {
        return DiaryInfo.of(
            diaryFeignResponse.diaryId(),
            diaryFeignResponse.name(),
            diaryFeignResponse.imageUrl(),
            diaryFeignResponse.color(),
            diaryFeignResponse.markerUrl()
        );
    }

}
