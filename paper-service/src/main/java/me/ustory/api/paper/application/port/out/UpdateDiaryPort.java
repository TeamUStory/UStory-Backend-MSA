package me.ustory.api.paper.application.port.out;

import me.ustory.api.paper.domain.DiaryInfo;

public interface UpdateDiaryPort {
    void updateDiary(DiaryInfo diaryInfo);
}
