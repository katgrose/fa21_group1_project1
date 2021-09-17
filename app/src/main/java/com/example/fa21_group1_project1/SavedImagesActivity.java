package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SavedImagesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    int passedUid;
    private List<ImageItem> xList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_images);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            passedUid = extras.getInt("userID");
        }

        SavedImageDatabase sdb = Room.databaseBuilder(getApplicationContext(), SavedImageDatabase.class, "saved_images")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        recyclerView = findViewById(R.id.viewR);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<SavedImage> mList = sdb.SavedImageDao().getSavedImages(passedUid);

        xList = new ArrayList<>();
        if(!mList.isEmpty()) {

            for (SavedImage element : mList) {
                ImageItem item = new ImageItem(element.getImageUrl(), element.getTags(), element.getLikes());
                xList.add(item);
            }


            PostAdapter adapter = new PostAdapter(SavedImagesActivity.this, xList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    public static Intent getIntent(Context context, String toastValue){
        Intent intent = new Intent(context, SavedImagesActivity.class);

        //intent.putExtra(SavedImages.ACTIVITY_LABEL,toastValue);

        return intent;
    }
}