package me.ustory.api.paper.adapter.out.persistence;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.adapter.out.persistence.entity.DiaryInfoEntity;
import me.ustory.api.paper.adapter.out.persistence.mapper.DiaryInfoMapper;
import me.ustory.api.paper.application.port.out.persistence.CreateDiaryPort;
import me.ustory.api.paper.application.port.out.persistence.GetDiaryPort;
import me.ustory.api.paper.application.port.out.persistence.UpdateDiaryPort;
import me.ustory.api.paper.domain.DiaryId;
import me.ustory.api.paper.domain.DiaryInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class DiaryInfoPersistenceAdapter implements CreateDiaryPort, GetDiaryPort, UpdateDiaryPort {

    private final DiaryInfoJpaRepository diaryInfoJpaRepository;

    @Override
    public DiaryInfo createDiary(DiaryInfo diaryInfo) {
        return DiaryInfoMapper.mapToDomain(diaryInfoJpaRepository.save(DiaryInfoMapper.mapToEntity(diaryInfo)));
    }

    @Override
    public boolean isExistDiary(DiaryId diaryId) {
        Optional<DiaryInfoEntity> diaryInfoEntity = diaryInfoJpaRepository.findById(diaryId.getValue());
        return diaryInfoEntity.isPresent();
    }

    @Override
    @Transactional
    public void updateDiary(DiaryInfo diaryInfo) {
        diaryInfoJpaRepository.save(DiaryInfoMapper.mapToEntity(diaryInfo));
    }
}
