package me.ustory.api.paper.adapter.out.feign;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.out.GetDiaryFeignPort;
import me.ustory.api.paper.domain.DiaryInfo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiaryFeignFeignAdapter implements GetDiaryFeignPort {

    private final DiaryFeignClient diaryFeignClient;

    @Override
    public DiaryInfo getDiaryById(Long diaryId) {
        DiaryFeignResponse response = diaryFeignClient.findDiaryById(diaryId);
        return DiaryFeignMapper.mapToDomain(response);
    }

}
