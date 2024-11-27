package me.ustory.api.paper.adapter.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "diary-service", path = "/api/diaries")
public interface DiaryFeignClient {

    @GetMapping
    DiaryFeignResponse findDiaryById(@RequestParam("id") Long id);

}
