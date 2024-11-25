package me.ustory.api.comment.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class QueryDslConfig {

    private final EntityManager em;

    public QueryDslConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(em);
    }

}
