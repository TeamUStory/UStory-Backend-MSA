package me.ustory.api.paper.application.port.out.feign;

import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;

public interface GetDiaryFeignPort {

    DiaryInfo getDiaryById(DiaryId diaryId);

}
