package me.ustory.api.paper.adapter.out.feign;

import lombok.RequiredArgsConstructor;
import me.ustory.api.common.feign.DiaryFeignDTO;
import me.ustory.api.paper.application.port.out.feign.GetDiaryFeignPort;
import me.ustory.api.paper.domain.DiaryInfo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiaryFeignAdapter implements GetDiaryFeignPort {

    private final DiaryFeignClient diaryFeignClient;

    @Override
    public DiaryInfo getDiaryById(Long diaryId) {
        DiaryFeignDTO response = diaryFeignClient.findDiaryById(diaryId);
        return DiaryFeignMapper.mapToDomain(response);
    }

}
