package me.ustory.api.paper.adapter.out.lock;

import lombok.RequiredArgsConstructor;
import me.ustory.api.paper.application.port.out.PaperConcurrencyLockPort;
import me.ustory.api.paper.domain.PaperId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisLockRepositoryPaper implements PaperConcurrencyLockPort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean lock(PaperId paperId) {
        return Boolean.TRUE.equals(redisTemplate
            .opsForValue()
            .setIfAbsent(privateKey(paperId), "lock", Duration.ofMillis(3_000)));
    }

    @Override
    public boolean unlock(PaperId paperId) {
        return Boolean.TRUE.equals(redisTemplate
            .delete(privateKey(paperId)));
    }

    private String privateKey(PaperId paperId) {
        return "paper" + paperId.toString();
    }

}
