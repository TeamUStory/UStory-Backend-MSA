package me.ustory.api.paper.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.out.CreateDiaryPort;
import me.ustory.api.paper.domain.DiaryInfo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DiaryInfoPersistenceAdapter implements CreateDiaryPort {

    private final DiaryInfoJpaRepository diaryInfoJpaRepository;

    @Override
    public DiaryInfo createDiary(DiaryInfo diaryInfo) {
        return DiaryInfoMapper.mapToDomain(diaryInfoJpaRepository.save(DiaryInfoMapper.mapToEntity(diaryInfo)));
    }

}
