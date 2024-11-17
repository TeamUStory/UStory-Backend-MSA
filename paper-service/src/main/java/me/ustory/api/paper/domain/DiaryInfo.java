package me.ustory.api.paper.domain;

import lombok.Getter;

@Getter
public class DiaryInfo {

    private Long id;

    private String name;

    private Image image;

    private String color;

    private Image marker;

    public static DiaryInfo of(Long id, String name, String imageUrl, String color, String markerUrl) {
        return new DiaryInfo(id, name, Image.of(imageUrl), color, Image.of(markerUrl));
    }

    private DiaryInfo(Long id, String name, Image image, String color, Image marker) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.color = color;
        this.marker = marker;
    }

}
