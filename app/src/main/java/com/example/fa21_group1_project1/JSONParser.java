package com.example.fa21_group1_project1;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONParser {
    static InputStream is = null;
    static JSONObject json = null;
    static String output = "";

    // Constructor
    public JSONParser(){

    }

    public JSONObject getJSONFromUrl(String url){
        URL _url;
        HttpURLConnection urlConnection;

        try{
            _url = new URL(url);
            urlConnection = (HttpURLConnection) _url.openConnection();
        }
        catch(MalformedURLException e){
            Log.e("JSON Parser", "Error due to malformed URL " + e.toString());
            return null;
        }
        catch(IOException e){
            Log.e("JSON parser", "IO error " + e.toString());
            return null;
        }
        try{
            is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder(is.available());
            String line;
            while((line = reader.readLine()) != null){
                total.append(line).append('\n');
            }
            output = total.toString();
        }
        catch(IOException e){
            Log.e("JSON Parser", "IO error " + e.toString());
            return null;
        }
        finally{
            urlConnection.disconnect();
        }

        try{
            json = new JSONObject(output);
        }
        catch(JSONException e){
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return json;
    }
}
