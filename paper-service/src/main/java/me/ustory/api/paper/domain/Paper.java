package me.ustory.api.paper.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class Paper {

    private PaperId id;

    private String title;

    private String thumbnailImageUrl;

    private String store;

    private LocalDate visitedAt;

    private MemberInfo writer;

    private DiaryInfo diary;

    private LocalDateTime deletedAt;

    private boolean locked;

    @Builder
    private Paper(String title, String thumbnailImageUrl, String store, LocalDate visitedAt, MemberInfo writer, DiaryInfo diary) {
        this.title = title;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.store = store;
        this.visitedAt = visitedAt;
        this.writer = writer;
        this.diary = diary;
        this.locked = true;
    }
}
