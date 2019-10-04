package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Acitivity2 extends AppCompatActivity {



    private Button Button;

    public void openActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    // json object response url
    private String urlJsonObj = "https://coms-309-ks-6.misc.iastate.edu:8080/orders";


    private static String TAG = MainActivity.class.getSimpleName();


    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity2);

        Button = findViewById(R.id.Clydes);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
                makeJsonObjectRequest();

            }
        });
        txtResponse = findViewById(R.id.txtResponse);





    }

    /**
     * Method to make json object request where json response starts wtih {
     * */
    private void makeJsonObjectRequest() {
        try {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("user_id",1);
        jsonBody.put("netid", "test_netid");
        jsonBody.put("password", "test_password");
        final String mRequestBody = jsonBody.toString();

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("test");
                Log.i("LOG_RESPONSE", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
                Log.e("LOG_RESPONSE", error.toString());
            }
        });

        requestQueue.add(stringRequest);
    } catch (JSONException e) {
        e.printStackTrace();
    }
}
}
