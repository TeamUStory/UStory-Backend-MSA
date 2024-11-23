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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Import({CommentPersistenceAdapter.class, JpaConfig.class, CommentMapper.class, MemberInfoMapper.class})
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
}