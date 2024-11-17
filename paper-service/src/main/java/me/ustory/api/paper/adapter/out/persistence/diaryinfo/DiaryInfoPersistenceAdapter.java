package me.ustory.api.paper.adapter.out.persistence.diaryinfo;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.out.CreateDiaryPort;
import me.ustory.api.paper.domain.DiaryInfo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DiaryInfoPersistenceAdapter implements CreateDiaryPort {

    private final DiaryInfoJpaRepository diaryInfoJpaRepository;

    @Override
    public void createDiary(DiaryInfo diaryInfo) {
        diaryInfoJpaRepository.save(DiaryInfoMapper.mapToEntity(diaryInfo));
    }

}
