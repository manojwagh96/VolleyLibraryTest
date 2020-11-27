package com.manoj.volleylibrarytest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //created que for response from server
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        //created string to store response url for jsonObjectRequest
        String url = "https://official-joke-api.appspot.com/random_joke";

        //created jsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i("RESPONSE_DATA_VALID", response + "");

                try {
                    Log.i("OBJECT_RESPONSE1", String.valueOf(response.getInt("id")));
                    Log.i("OBJECT_RESPONSE2", response.getString("type"));
                    Log.i("OBJECT_RESPONSE3", response.getString("setup"));
                    Log.i("OBJECT_RESPONSE4", response.getString("punchline"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("ERROR_RESPONSE", error.getMessage());
            }
        });


        //created string to store response url for jsonArrayRequest
        String url2 = "https://official-joke-api.appspot.com/random_ten";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.i("ARRAY_RESPONSE", response + "");

                for (int index = 0; index < response.length(); index++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(index);
                        Log.i("JOKE", jsonObject.getString("setup") + " "
                                + jsonObject.getString("punchline"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("ARRAY_ERROR_RESPONSE", error.getMessage());
            }
        });


        //Added jsonObjectRequest and JsonArrayRequest to the queue
        requestQueue.add(jsonObjectRequest);
        requestQueue.add(jsonArrayRequest);
    }
}