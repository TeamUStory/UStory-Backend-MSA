package me.ustory.api.paper.domain;

import lombok.Getter;

@Getter
public class DiaryInfo {

    private Long id;

    private String name;

    private Image image;

    private String color;

    private Image marker;

    private String category;

    public static DiaryInfo of(Long id, String name, String imageUrl, String color, String markerUrl, String category) {
        return new DiaryInfo(id, name, Image.of(imageUrl), color, Image.of(markerUrl), category);
    }

    private DiaryInfo(Long id, String name, Image image, String color, Image marker, String category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.color = color;
        this.marker = marker;
        this.category = category;
    }

    public boolean isIndividual() {
        if ("개인".equals(category)) {
            return true;
        }

        return false;
    }
}
