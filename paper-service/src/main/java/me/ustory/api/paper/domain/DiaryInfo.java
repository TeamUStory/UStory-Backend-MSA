package me.ustory.api.paper.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class DiaryInfo {

    private final DiaryId id;

    private final MemberInfo memberInfo;

    private final String name;

    private final Image image;

    private final Color color;

    private final Image marker;

    public static DiaryInfo of(DiaryId id, MemberInfo memberInfo, String name, String imageUrl, String color, String markerUrl) {
        return new DiaryInfo(id, memberInfo, name, Image.of(imageUrl), Color.of(color), Image.of(markerUrl));
    }

    public static DiaryInfo of(DiaryId id, MemberInfo memberInfo, String name, Image imageUrl, Color color, Image markerUrl) {
        return new DiaryInfo(id, memberInfo, name, imageUrl, color, markerUrl);
    }

    public boolean isIndividualDiary() {
        return memberInfo.isIndividual();
    }

    public boolean isSameMemberCount(int memberCount) {
        return memberInfo.getMemberIds().size() == memberCount;
    }

    private DiaryInfo(DiaryId id, MemberInfo memberInfo, String name, Image image, Color color, Image marker) {
        this.id = id;
        this.memberInfo = memberInfo;
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
        return Objects.equals(id, diaryInfo.id) && Objects.equals(memberInfo, diaryInfo.memberInfo) && Objects.equals(name, diaryInfo.name) && Objects.equals(image, diaryInfo.image) && Objects.equals(color, diaryInfo.color) && Objects.equals(marker, diaryInfo.marker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, memberInfo, name, image, color, marker);
    }

    @Override
    public String toString() {
        return "DiaryInfo{" +
            "id=" + id +
            ", memberInfo=" + memberInfo +
            ", name='" + name + '\'' +
            ", image=" + image +
            ", color='" + color + '\'' +
            ", marker=" + marker +
            '}';
    }

}
