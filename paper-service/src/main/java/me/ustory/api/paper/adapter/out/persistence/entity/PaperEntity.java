package me.ustory.api.paper.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ustory.api.common.BaseEntity;
import me.ustory.api.paper.adapter.out.persistence.StringListConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PaperEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long writerId;

    @ManyToOne
    @JoinColumn
    private DiaryInfoEntity diaryInfo;

    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(50)")
    private String title;

    @Column(name = "thumbnail_image", nullable = false, columnDefinition = "VARCHAR(1000)")
    private String thumbnail;

    @Column(name = "visited_at", nullable = false, columnDefinition = "DATE")
    private LocalDate visitedAt;

    @Convert(converter = StringListConverter.class)
    private List<String> images;

    @Embedded
    private AddressEntity address;

    @Column(name = "deleted_at", columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    private Boolean isLocked;

    public static PaperEntity of(Long id, Long writerId, DiaryInfoEntity diaryInfoEntity, String title, String thumbnail, LocalDate visitedAt, List<String> images, AddressEntity address, Boolean isLocked) {
        return new PaperEntity(id, writerId, diaryInfoEntity, title, thumbnail, visitedAt, images, address, null, isLocked);
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

}
