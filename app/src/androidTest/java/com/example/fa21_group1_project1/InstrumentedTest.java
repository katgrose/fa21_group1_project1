package com.example.fa21_group1_project1;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    @Test
    public void pixabayAPITest() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(appContext, PictureSearch.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);
        String url = "https://pixabay.com/api/?key=20481069-b7515ab630a25b9504d55e812&q=animal&image_type=photo&pretty=true";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String imageUrl = jsonObject.getString("webformatURL");
                    int likes = jsonObject.getInt("likes");
                    String tags = jsonObject.getString("tags");

                    assertNotNull(jsonObject);
                    assertNotNull(imageUrl);
                    assertNotEquals(likes, -1);
                    assertNotNull(tags);
                } catch (JSONException e) {
                    assertTrue(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                assertTrue(false);
            }
        });

    }

    @Test
    public void daoTest(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AccountsDatabase db = AccountsDatabase.getInstance(appContext);
        AccountsDao accountsDao = db.AccountsDao();
        // get account
        // save account to variable
        Accounts savedAccount = accountsDao.findTodoByUsername("admin1");
        // delete account
        accountsDao.deleteAccount(savedAccount);
        // Assert deleted
        assertNull(accountsDao.findTodoByUsername("admin1"));
        // insert account
        Accounts replacementAccount = new Accounts("admin1", "admin1");
        accountsDao.insertAccount(replacementAccount);
        // check if it exists
        assertNotNull(accountsDao.findTodoByUsername("admin1"));
    }

//    @Test
//    public void mainIntentSwitchTest(){
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        appContext.startActivity(new Intent(appContext, MainActivity.class));
//        appContext
//    }

}