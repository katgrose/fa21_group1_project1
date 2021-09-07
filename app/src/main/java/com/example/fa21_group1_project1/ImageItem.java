package com.example.fa21_group1_project1;

public class ImageItem {

    //private int id;
    private String imageUrl, tags;
    private int likes;

    public ImageItem(String imageUrl, String tags, int likes){
        this.imageUrl = imageUrl;
        this.tags = tags;
        this.likes = likes;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTags() {
        return tags;
    }

    public int getLikes() {
        return likes;
    }
}
