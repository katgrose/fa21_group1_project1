package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SavedImagesActivity extends AppCompatActivity {
    int passedUid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_images);

        // Get passed User Id from MainActivity loginSuccess
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            passedUid = extras.getInt("userId");
        }
        // Test code: display User Id in Toast
        String text = "UserId: " + passedUid;
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public static Intent getIntent(Context context, String toastValue){
        Intent intent = new Intent(context, SavedImagesActivity.class);

        //intent.putExtra(SavedImages.ACTIVITY_LABEL,toastValue);

        return intent;
    }
}