package com.example.fa21_group1_project1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedImageDao {
    @Insert
    void insertSavedImage(SavedImage image);

    @Query("SELECT * FROM saved_images WHERE uid LIKE :uid")
    List<SavedImage> getSavedImages(int uid);

    @Delete
    void deleteImage(SavedImage image);

}