package me.ustory.api.paper.application.port.out.persistence;

import me.ustory.api.paper.domain.DiaryId;

public interface GetDiaryPort {
    boolean isExistDiary(DiaryId diaryId);
}
