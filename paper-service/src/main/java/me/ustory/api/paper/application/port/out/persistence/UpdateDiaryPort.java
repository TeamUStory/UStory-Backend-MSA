package me.ustory.api.paper.application.port.out.persistence;

import me.ustory.api.paper.domain.DiaryInfo;

public interface UpdateDiaryPort {
    void updateDiary(DiaryInfo diaryInfo);
}
