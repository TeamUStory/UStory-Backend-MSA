package me.ustory.api.comment.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Comment {

    private final CommentId commentId;

    private final PaperId paperId;

    private final MemberInfo memberInfo;

    private final String content;

    private final LocalDateTime createdAt;


    public static Comment withId(CommentId commentId, PaperId paperId, MemberInfo memberInfo, String content, LocalDateTime createdAt) {
        return new Comment(commentId, paperId, memberInfo, content, createdAt);
    }

    public static Comment withoutId(PaperId paperId, MemberInfo memberInfo, String content) {
        return new Comment(null, paperId, memberInfo, content, null);
    }

    public Comment changeContent(String content) {
        return new Comment(this.commentId, this.paperId, this.memberInfo, content, this.createdAt);
    }

    private Comment(CommentId commentId, PaperId paperId, MemberInfo memberInfo, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.paperId = validatePaperId(paperId);
        this.memberInfo = validateMemberInfo(memberInfo);
        this.content = validateContent(content);
        this.createdAt = createdAt;
    }

    private PaperId validatePaperId(PaperId paperId) {
        if (paperId == null) {
            throw new IllegalArgumentException("paperId can not be null");
        }

        return paperId;
    }

    private MemberInfo validateMemberInfo(MemberInfo memberInfo) {
        if (memberInfo == null) {
            throw new IllegalArgumentException("memberInfo can not be null");
        }

        return memberInfo;
    }

    private String validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("content can not be null");
        }

        return content;
    }
}
