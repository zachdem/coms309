package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.homepage.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;

    private String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/userlogin";

    private static String TAG = MainActivity.class.getSimpleName();

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
        JSONObject js = new JSONObject();
        try {
            js.put("name","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlJsonObj, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Succes");
                Log.d(TAG, response.toString() + " hello");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("userid", "1");
                params.put("netid", "test_netid");
                params.put("password", "test_password");
                return params;
            }
        };
                AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    public void openActivity2() {
        Intent intent = new Intent(this, Acitivity2.class);
        startActivity(intent);
    }
}
