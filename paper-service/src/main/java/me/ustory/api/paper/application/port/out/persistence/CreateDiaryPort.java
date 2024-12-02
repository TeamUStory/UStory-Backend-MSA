package me.ustory.api.paper.application.port.out.persistence;

import me.ustory.api.paper.domain.DiaryInfo;

public interface CreateDiaryPort {

    DiaryInfo createDiary(DiaryInfo diaryInfo);

}
