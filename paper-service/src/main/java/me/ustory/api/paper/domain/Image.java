package me.ustory.api.paper.domain;

public class Image {

    private final String url;

    public static Image of(String url) {
        return new Image(url);
    }

    public String getUrl() {
        return url;
    }

    private Image(String url) {
        validate(url);
        this.url = url;
    }

    private void validate(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            throw new IllegalArgumentException("URL은 http:// 또는 https://로 시작해야 합니다.");
        }

        if (!url.endsWith(".gif") && !url.endsWith(".png") && !url.endsWith(".jpg") && !url.endsWith(".jpeg")) {
            throw new IllegalArgumentException(".gif, .png, .jpg, .jpeg 이미지 확장자만 지원합니다.");
        }
    }
}
