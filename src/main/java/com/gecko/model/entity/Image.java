package com.gecko.model.entity;

public class Image {
    private int imageId;
    private String url;
    private int productId;

    public Image() {
    }

    public Image(int imageId, String url, int productId) {
        this.imageId = imageId;
        this.url = url;
        this.productId = productId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
