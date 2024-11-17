package me.ustory.api.paper.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PaperBasicInfo {

    private String title;

    private Image thumbnailImage;

    private String store;

    private LocalDate visitedAt;

    public static PaperBasicInfo of(String title, Image thumbnailImage, String store, LocalDate visitedAt) {
        return new PaperBasicInfo(title, thumbnailImage, store, visitedAt);
    }

    private PaperBasicInfo(String title, Image thumbnailImage, String store, LocalDate visitedAt) {
        validateVisitedDate(visitedAt);

        this.title = title;
        this.thumbnailImage = thumbnailImage;
        this.store = store;
        this.visitedAt = visitedAt;
    }

    private void validateVisitedDate(LocalDate visitedAt) {
        if (visitedAt.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Visited date is after now");
        }
    }

}
