package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class SavedImagesActivity extends AppCompatActivity {
    int passedUid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_images);

        SavedImageDatabase db = Room.databaseBuilder(getApplicationContext(), SavedImageDatabase.class, "accounts_table")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        SavedImageDao accountsDao = db.SavedImageDao();

        // Get passed User Id from MainActivity loginSuccess
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            passedUid = extras.getInt("userId");
        }
        // Test code: display User Id in Toast
        //String text = "UserId: " + passedUid;
        //Toast.makeText(this, text, Toast.LENGTH_LONG).show();

        List<SavedImage> savedImages = accountsDao.getSavedImages(passedUid);

        // Test code: display first saved image info in Toast
        if(savedImages.size() != 0) {
            String text = "First Saved Image: " + savedImages.get(0);
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }
    }

    public static Intent getIntent(Context context, String toastValue){
        Intent intent = new Intent(context, SavedImagesActivity.class);

        //intent.putExtra(SavedImages.ACTIVITY_LABEL,toastValue);

        return intent;
    }
}