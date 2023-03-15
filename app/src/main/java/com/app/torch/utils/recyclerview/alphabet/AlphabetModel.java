package com.app.torch.utils.recyclerview.alphabet;

public class AlphabetModel {
    private String title;
    private String imageUrl;
    private boolean isTitle;

    public AlphabetModel(String title, boolean isTitle) {
        this.title = title;
        this.isTitle = isTitle;
    }

    public AlphabetModel(String title, String imageUrl, boolean isTitle) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.isTitle = isTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }
}
