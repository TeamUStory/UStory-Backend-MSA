package me.ustory.api.paper.adapter.out.persistence;

import me.ustory.api.paper.adapter.out.persistence.entity.PaperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PaperJpaRepository extends JpaRepository<PaperEntity, Long> {
}
