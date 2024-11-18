package me.ustory.api.paper.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Paper {

    private PaperId paperId;

    private PaperBasicInfo basicInfo;

    private PaperDetail detail;

    private MemberInfo writer;

    private DiaryInfo diary;

    private boolean locked;

    @Builder
    private Paper(PaperId paperId, PaperBasicInfo paperBasicInfo, PaperDetail paperDetail, MemberInfo writer, DiaryInfo diary) {
        this.paperId = paperId;
        this.basicInfo = paperBasicInfo;
        this.detail = paperDetail;
        this.writer = writer;
        this.diary = diary;
        this.locked = !diary.isIndividual();
    }

    public void unLock() {
        this.locked = false;
    }

    public String getTitle() {
        return basicInfo.getTitle();
    }

    public String getThumbnailUrl() {
        return basicInfo.getThumbnailImage().getUrl();
    }

    public String getStore() {
        return basicInfo.getStore();
    }

    public LocalDate getVisitedDate() {
        return basicInfo.getVisitedAt();
    }

}
