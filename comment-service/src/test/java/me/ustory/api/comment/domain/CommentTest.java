package me.ustory.api.comment.domain;

import me.ustory.api.common.vo.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @DisplayName("CommentId와 CreatedAt 없이 Comment를 만들 수 있다.")
    @Test
    void withoutId() {
        // given
        PaperId paperId = PaperId.of(1L);
        MemberInfo memberInfo = MemberInfo.of(MemberId.of(1L), "닉네임", Image.of("https://www.example.com/이미지.png"));
        String content = "댓글 내용";

        // when
        Comment comment = Comment.withoutId(paperId, memberInfo, content);

        // then
        assertThat(comment.getCommentId()).isNull();
        assertThat(comment.getPaperId()).isEqualTo(paperId);
        assertThat(comment.getMemberInfo()).isEqualTo(memberInfo);
        assertThat(comment.getContent()).isEqualTo(content);
        assertThat(comment.getCreatedAt()).isNull();
    }

    @DisplayName("CommentId와 CreatedAt을 포함한 Comment를 만들 수 있다.")
    @Test
    void withId() {
        // given
        CommentId commentId = CommentId.of(1L);
        PaperId paperId = PaperId.of(1L);
        MemberInfo memberInfo = MemberInfo.of(MemberId.of(1L), "닉네임", Image.of("https://www.example.com/이미지.png"));
        String content = "댓글 내용";
        LocalDateTime createdAt = LocalDateTime.of(2024,10,10,10,10);

        // when
        Comment comment = Comment.withId(commentId, paperId, memberInfo, content, createdAt);

        // then
        assertThat(comment.getCommentId()).isEqualTo(commentId);
        assertThat(comment.getPaperId()).isEqualTo(paperId);
        assertThat(comment.getMemberInfo()).isEqualTo(memberInfo);
        assertThat(comment.getContent()).isEqualTo(content);
        assertThat(comment.getCreatedAt()).isEqualTo(createdAt);
    }

    @DisplayName("PaperId는 필수 값이다.")
    @Test
    void validatePaperId() {
        // given
        MemberInfo memberInfo = MemberInfo.of(MemberId.of(1L), "닉네임", Image.of("https://www.example.com/이미지.png"));
        String content = "댓글 내용";

        // when & then
        assertThatThrownBy(() -> Comment.withoutId(null, memberInfo, content))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("paperId can not be null");
    }

    @DisplayName("MemberInfo는 필수 값이다.")
    @Test
    void validateMemberInfo() {
        // given
        PaperId paperId = PaperId.of(1L);
        String content = "댓글 내용";

        // when & then
        assertThatThrownBy(() -> Comment.withoutId(paperId, null, content))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("memberInfo can not be null");
    }

    @DisplayName("content는 필수 값이다.")
    @Test
    void validateContent() {
        // given
        PaperId paperId = PaperId.of(1L);
        MemberInfo memberInfo = MemberInfo.of(MemberId.of(1L), "닉네임", Image.of("https://www.example.com/이미지.png"));

        // when & then
        assertThatThrownBy(() -> Comment.withoutId(paperId, memberInfo, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("content can not be null");
    }

    @DisplayName("content는 내용이 포함되어 있어야 한다.")
    @Test
    void validateContentBlank() {
        // given
        PaperId paperId = PaperId.of(1L);
        MemberInfo memberInfo = MemberInfo.of(MemberId.of(1L), "닉네임", Image.of("https://www.example.com/이미지.png"));

        // when & then
        assertThatThrownBy(() -> Comment.withoutId(paperId, memberInfo, " "))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("content can not be null");
    }

    @DisplayName("댓글을 수정할 수 있다.")
    @Test
    void changeContent() {
        // given
        CommentId commentId = CommentId.of(1L);
        PaperId paperId = PaperId.of(1L);
        MemberInfo memberInfo = MemberInfo.of(MemberId.of(1L), "닉네임", Image.of("https://www.example.com/이미지.png"));
        String content = "댓글 내용";
        LocalDateTime createdAt = LocalDateTime.of(2024,10,10,10,10);
        Comment comment = Comment.withId(commentId, paperId, memberInfo, content, createdAt);

        String newContent = "변경된 댓글";

        // when
        Comment changedComment = comment.changeContent(newContent);

        // then
        assertThat(changedComment.getContent()).isEqualTo(newContent);
    }

}