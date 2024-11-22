package me.ustory.api.paper.adapter.out.feign;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import me.ustory.api.paper.domain.MemberId;
import me.ustory.api.paper.domain.MemberInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryFeignMapper {

    public static DiaryInfo mapToDomain(DiaryFeignResponse diaryFeignResponse) {
        return DiaryInfo.of(
            DiaryId.of(diaryFeignResponse.diaryId()),
            MemberInfo.of(diaryFeignResponse.membersId().stream()
                .map(MemberId::of).toList()),
            diaryFeignResponse.name(),
            diaryFeignResponse.imageUrl(),
            diaryFeignResponse.color(),
            diaryFeignResponse.markerUrl()
        );
    }

}
