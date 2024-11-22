package me.ustory.api.paper.application.port.out;

import me.ustory.api.paper.domain.DiaryInfo;

public interface GetDiaryFeignPort {

    DiaryInfo getDiaryById(Long diaryId);

}
