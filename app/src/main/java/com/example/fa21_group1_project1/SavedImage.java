package com.example.fa21_group1_project1;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_images", foreignKeys = @ForeignKey(entity = Accounts.class,
        parentColumns = "uid",
        childColumns = "uid",
        onDelete = CASCADE))

public class SavedImage {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "uid")
    public int uid;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "image_tags")
    public String tags;

    @ColumnInfo(name = "image_likes")
    public int likes;

    public SavedImage(int uid, String imageUrl, String tags, int likes) {
        this.uid = uid;
        this.imageUrl = imageUrl;
        this.tags = tags;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    @Override
    public String toString() {
        return "SavedImage{" +
                "img='" + imageUrl + '\'' +
                ", tags='" + tags + '\'' +
                ", lies='" + likes + '\'' +
                '}';
    }

}