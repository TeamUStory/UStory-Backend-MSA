package me.ustory.api.paper.domain;

import lombok.Getter;

@Getter
public class DiaryInfo {

    private Long id;

    // TODO: Paper 애그리게이트에는 다이어리의 종류가 필요하다.

    public static DiaryInfo of(Long id) {
        return new DiaryInfo(id);
    }

    private DiaryInfo(Long id) {
        this.id = id;
    }

}
