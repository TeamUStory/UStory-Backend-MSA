package me.ustory.api.paper.domain;

import lombok.Getter;

@Getter
public class DiaryInfo {

    private Long id;

    private String name;

    private Image image;

    private String color;

    private Image marker;

    public static DiaryInfo of(Long id) {
        return new DiaryInfo(id);
    }

    private DiaryInfo(Long id) {
        this.id = id;
    }

}
