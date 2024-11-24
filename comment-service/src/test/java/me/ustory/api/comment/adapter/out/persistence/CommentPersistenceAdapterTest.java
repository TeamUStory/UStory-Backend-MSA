package me.ustory.api.comment.adapter.out.persistence;

import me.ustory.api.comment.domain.Comment;
import me.ustory.api.comment.domain.CommentId;
import me.ustory.api.comment.domain.Image;
import me.ustory.api.comment.domain.MemberId;
import me.ustory.api.comment.domain.MemberInfo;
import me.ustory.api.comment.domain.PaperId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import({CommentPersistenceAdapter.class, JpaConfig.class, QueryDslConfig.class, CommentMapper.class, MemberInfoMapper.class})
@ActiveProfiles("test")
class CommentPersistenceAdapterTest {

    @Autowired
    private CommentPersistenceAdapter commentPersistenceAdapter;

    @Autowired
    private CommentJpaRepository commentJpaRepository;

    @DisplayName("댓글을 저장한다.")
    @Test
    void createComment() {
        // given
        PaperId paperId = PaperId.of(1L);
        MemberInfo memberInfo = MemberInfo.of(
            MemberId.of(1L),
            "닉네임",
            Image.of("https://www.example.com/프로필.png")
        );
        String content = "댓글 내용";
        Comment comment = Comment.withoutId(paperId, memberInfo, content);

        // when
        CommentId savedCommentId = commentPersistenceAdapter.createComment(comment);

        // then
        CommentEntity commentEntity = commentJpaRepository.findById(savedCommentId.getValue()).orElseThrow();

        assertThat(commentEntity.getPaperId()).isEqualTo(paperId.getValue());
        assertThat(commentEntity.getContent()).isEqualTo(content);
        assertThat(commentEntity.getCreatedAt()).isNotNull();
    }

    @DisplayName("댓글을 불러온다.")
    @Sql("CommentPersistenceAdapterTest.sql")
    @Test
    void getCommentById() {
        // given
        CommentId commentId = CommentId.of(1L);

        // when
        Comment comment = commentPersistenceAdapter.getComment(commentId);

        // then
        assertThat(comment.getCommentId()).isEqualTo(commentId);
    }

    @DisplayName("Paper에 속한 모든 댓글을 불러온다.")
    @Sql("CommentPersistenceAdapterTest.sql")
    @Test
    void getCommentsByPaperId() {
        // given
        PaperId paperId = PaperId.of(1L);

        // when
        List<Comment> comments = commentPersistenceAdapter.getComments(paperId);

        // then
        assertThat(comments).hasSize(3);
        assertThat(comments.get(0).getPaperId()).isEqualTo(paperId);
        assertThat(comments.get(1).getPaperId()).isEqualTo(paperId);
        assertThat(comments.get(2).getPaperId()).isEqualTo(paperId);
    }

    @DisplayName("댓글을 수정한다.")
    @Sql("CommentPersistenceAdapterTest.sql")
    @Test
    void updateComment() {
        // given
        CommentId commentId = CommentId.of(1L);

        Comment comment = commentPersistenceAdapter.getComment(commentId);
        comment.changeContent("수정된 댓글");

        // when
        CommentId updatedCommentId = commentPersistenceAdapter.updateComment(comment);

        // then
        assertThat(commentId).isEqualTo(updatedCommentId);

        CommentEntity updatedCommentEntity = commentJpaRepository.findById(updatedCommentId.getValue()).orElseThrow();
        assertThat(updatedCommentEntity.getContent()).isEqualTo(comment.getContent());
    }
}