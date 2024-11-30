package me.ustory.api.comment.adapter.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member-service", path = "/api/members")
interface MemberFeignClient {

    @GetMapping
    MemberFeignDTO findMemberById(@RequestParam("id") Long id);

}
