package me.ustory.api.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = {
    "me.ustory.api.comment",
    "me.ustory.api.common"
})
@EnableFeignClients
@EnableDiscoveryClient
public class CommentServiceApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        SpringApplication.run(CommentServiceApplication.class, args);
    }

}
