package me.ustory.api.comment.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ustory.api.common.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long paperId;

    @ManyToOne
    @JoinColumn(name = "member_info_id")
    private MemberInfoEntity memberInfo;

    private String content;

    public static CommentEntity withoutId(Long paperId, MemberInfoEntity memberInfoEntity, String content) {
        return new CommentEntity(null, paperId, memberInfoEntity, content);
    }

    public static CommentEntity withId(Long commentId, Long paperId, MemberInfoEntity memberInfoEntity, String content) {
        return new CommentEntity(commentId, paperId, memberInfoEntity, content);
    }

    private CommentEntity(Long id, Long paperId, MemberInfoEntity memberInfo, String content) {
        this.id = id;
        this.paperId = paperId;
        this.memberInfo = memberInfo;
        this.content = content;
    }
}
