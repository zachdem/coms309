package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.homepage.app.AppController;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;

    private String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/userlogin";
    private static String TAG = MainActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
                makeJsonObjectRequest();

            }
        });
    }

    private void makeJsonObjectRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                System.out.println("Hello");
                params.put("user_hid", "1");
                params.put("netid", "test_netid");
                params.put("password", "test_password");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_obj);
    }


    public void openActivity2() {
        Intent intent = new Intent(this, Acitivity2.class);
        startActivity(intent);
    }
}
