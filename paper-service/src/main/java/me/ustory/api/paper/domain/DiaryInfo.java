package me.ustory.api.paper.domain;

import lombok.Getter;
import me.ustory.api.common.vo.Color;
import me.ustory.api.common.vo.Image;

import java.util.Objects;

@Getter
public class DiaryInfo {

    private final DiaryId id;

    private final Members members;

    private final String name;

    private final Image image;

    private final Color color;

    private final Image marker;

    public static DiaryInfo of(DiaryId id, Members members, String name, String imageUrl, String color, String markerUrl) {
        return new DiaryInfo(id, members, name, Image.of(imageUrl), Color.of(color), Image.of(markerUrl));
    }

    public static DiaryInfo of(DiaryId id, Members members, String name, Image imageUrl, Color color, Image markerUrl) {
        return new DiaryInfo(id, members, name, imageUrl, color, markerUrl);
    }

    public boolean isIndividualDiary() {
        return members.isIndividual();
    }

    public boolean isSameMemberCount(int memberCount) {
        return members.getMemberIds().size() == memberCount;
    }

    private DiaryInfo(DiaryId id, Members members, String name, Image image, Color color, Image marker) {
        this.id = id;
        this.members = members;
        this.name = name;
        this.image = image;
        this.color = color;
        this.marker = marker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryInfo diaryInfo = (DiaryInfo) o;
        return Objects.equals(id, diaryInfo.id) && Objects.equals(members, diaryInfo.members) && Objects.equals(name, diaryInfo.name) && Objects.equals(image, diaryInfo.image) && Objects.equals(color, diaryInfo.color) && Objects.equals(marker, diaryInfo.marker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, members, name, image, color, marker);
    }

    @Override
    public String toString() {
        return "DiaryInfo{" +
            "id=" + id +
            ", memberInfo=" + members +
            ", name='" + name + '\'' +
            ", image=" + image +
            ", color='" + color + '\'' +
            ", marker=" + marker +
            '}';
    }

}
