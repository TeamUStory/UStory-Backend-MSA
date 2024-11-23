package me.ustory.api.member.domain;

import java.util.Objects;

public class ProfileImage {

    private final String url;

    private ProfileImage(String url) {
        validate(url);
        this.url = url;
    }

    public static ProfileImage of(String url) {
        return new ProfileImage(url);
    }

    public String getUrl() {return url;}

    private void validate(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            throw new IllegalArgumentException("URL은 http:// 또는 https://로 시작해야 합니다.");
        }

        if (!url.endsWith(".gif") && !url.endsWith(".png") && !url.endsWith(".jpg") && !url.endsWith(".jpeg")) {
            throw new IllegalArgumentException(".gif, .png, .jpg, .jpeg 이미지 확장자만 지원합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileImage image = (ProfileImage) o;
        return Objects.equals(url, image.url);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url);
    }

    @Override
    public String toString() {
        return "ProfileImage{" +
                "url='" + url + '\'' +
                '}';
    }
}
