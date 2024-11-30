package me.ustory.api.paper.adapter.out.feign;

import me.ustory.api.common.feign.DiaryFeignDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "diary-service", path = "/api/diaries")
public interface DiaryFeignClient {

    @GetMapping
    DiaryFeignDTO findDiaryById(@RequestParam("id") Long id);

}
