package com.example.fa21_group1_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NasaAPOD extends AppCompatActivity {

    public String url = "";
    Button updateImage;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_apod);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        updateImages();
        updateImage = findViewById(R.id.updateNasaApodBtn);
        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateImages();
            }
        });
    }

    public void updateImages(){
        imageView = findViewById(R.id.nasaAPODimageView);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = jsonParser.getJSONFromUrl("https://api.nasa.gov/planetary/apod?api_key=YpGQqSQrVMccgat1saC9g9oLd9AM9pxpGkjlLLSu");
        try{
            url = jsonObject.get("hdurl").toString();
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        imageView.setImageBitmap(getBitmapFromURL(url));
    }

    public static Bitmap getBitmapFromURL(String src){
        try{
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        }
        catch (IOException e){
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }
}