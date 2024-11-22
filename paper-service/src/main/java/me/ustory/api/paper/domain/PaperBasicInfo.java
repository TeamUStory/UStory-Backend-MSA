package me.ustory.api.paper.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class PaperBasicInfo {

    private final String title;

    private final Image thumbnailImage;

    private final String store;

    private final LocalDate visitedAt;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperBasicInfo that = (PaperBasicInfo) o;
        return Objects.equals(title, that.title) && Objects.equals(thumbnailImage, that.thumbnailImage) && Objects.equals(store, that.store) && Objects.equals(visitedAt, that.visitedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, thumbnailImage, store, visitedAt);
    }

    @Override
    public String toString() {
        return "PaperBasicInfo{" +
            "title='" + title + '\'' +
            ", thumbnailImage=" + thumbnailImage +
            ", store='" + store + '\'' +
            ", visitedAt=" + visitedAt +
            '}';
    }
}
