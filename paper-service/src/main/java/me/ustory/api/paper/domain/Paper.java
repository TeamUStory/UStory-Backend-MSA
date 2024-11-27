package me.ustory.api.paper.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Paper {

    private final PaperId paperId;

    private final MemberId writer;

    private PaperBasicInfo basicInfo;

    private PaperDetail detail;

    private DiaryInfo diary;

    private boolean locked;

    @Builder
    private Paper(PaperId paperId, PaperBasicInfo paperBasicInfo, PaperDetail paperDetail, MemberId writer, DiaryInfo diary, Boolean locked) {
        this.paperId = paperId;
        this.writer = writer;
        this.basicInfo = paperBasicInfo;
        this.detail = paperDetail;
        this.diary = diary;
        this.locked = (locked != null) ? locked : !diary.isIndividualDiary();
    }

    public void changeBasicInfo(PaperBasicInfo paperBasicInfo) {
        this.basicInfo = paperBasicInfo;
    }

    public void changeDetail(PaperDetail paperDetail) {
        this.detail = paperDetail;
    }

    public void unLock() {
        this.locked = false;
    }

    public boolean isCanUnlock(int memberCount) {
        return diary.isSameMemberCount(memberCount);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paper paper = (Paper) o;
        return locked == paper.locked && Objects.equals(paperId, paper.paperId) && Objects.equals(basicInfo, paper.basicInfo) && Objects.equals(detail, paper.detail) && Objects.equals(writer, paper.writer) && Objects.equals(diary, paper.diary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paperId, basicInfo, detail, writer, diary, locked);
    }

    @Override
    public String toString() {
        return "Paper{" +
            "paperId=" + paperId +
            ", basicInfo=" + basicInfo +
            ", detail=" + detail +
            ", writer=" + writer +
            ", diary=" + diary +
            ", locked=" + locked +
            '}';
    }
}
