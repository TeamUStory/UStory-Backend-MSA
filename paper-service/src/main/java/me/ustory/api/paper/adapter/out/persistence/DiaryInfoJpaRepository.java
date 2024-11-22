package me.ustory.api.paper.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DiaryInfoJpaRepository extends JpaRepository<DiaryInfoEntity, Long> {
}
