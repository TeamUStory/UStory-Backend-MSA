package me.ustory.api.comment.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {
}
