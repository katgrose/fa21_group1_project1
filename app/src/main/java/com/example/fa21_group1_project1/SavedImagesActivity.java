package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SavedImagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_images);
    }

    public static Intent getIntent(Context context, String toastValue){
        Intent intent = new Intent(context, SavedImagesActivity.class);

        //intent.putExtra(SavedImages.ACTIVITY_LABEL,toastValue);

        return intent;
    }
}