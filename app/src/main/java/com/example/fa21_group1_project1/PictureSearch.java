package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PictureSearch extends AppCompatActivity {
    Button searchBtn;
    EditText keywordText;

    Button bSavedImages;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<ImageItem> mList;
    int passedUid = 0;
    SavedImage savedItem = null;
    SavedImageDao savedImageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_search);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            passedUid = extras.getInt("userID");
        }
        SavedImageDatabase db = Room.databaseBuilder(getApplicationContext(), SavedImageDatabase.class, "saved_images")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        savedImageDao = db.SavedImageDao();

        //Saved Images activity

        bSavedImages = (Button) findViewById(R.id.btnSavedImages);
        bSavedImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSavedImagesActivity();
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchBtn = findViewById(R.id.searchButton);
        keywordText = findViewById(R.id.keywordField);
        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        mList = new ArrayList<>();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.clear();
                String searchTerm = keywordText.getText().toString();
                fetchData(searchTerm);

            }
        });

        //save image to saved images

    }

    private String fetchData(String searchTerm) {

        String url = "https://pixabay.com/api/?key=20481069-b7515ab630a25b9504d55e812&q="+ searchTerm + "&image_type=photo&pretty=true";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String imageUrl = jsonObject.getString("webformatURL");
                        int likes = jsonObject.getInt("likes");
                        String tags = jsonObject.getString("tags");

                        ImageItem post = new ImageItem(imageUrl, tags, likes);

                        mList.add(post);
                    }

                    PostAdapter adapter = new PostAdapter(PictureSearch.this, mList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    adapter.setOnItemClickListener(new PostAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            savedItem = createSavedImage(mList.get(position));
                            if (savedItem != null) {
                                savedImageDao.insertSavedImage(savedItem);
                            }
                            Toast.makeText(PictureSearch.this, savedItem.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PictureSearch.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
        return url;
    }

    public void openSavedImagesActivity(){
        Intent intent =  SavedImagesActivity.getIntent(getApplicationContext(),"Login successful!!!!");
        startActivity(intent);
    };

    public SavedImage createSavedImage(ImageItem post)
    {

        return new SavedImage(passedUid,post.getImageUrl(),post.getTags(),post.getLikes());
    };





}