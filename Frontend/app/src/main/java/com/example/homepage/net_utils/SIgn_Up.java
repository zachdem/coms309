package com.example.homepage.net_utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.homepage.R;
import com.example.homepage.app.AppController;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class SIgn_Up extends AppCompatActivity {



    private String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/Signup";

    private TextView txtResponse;



    /**
     * Making the JSON Array request
     */
    private void makeJsonArrayRequest(){
        // making the new object
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                System.out.println("Succcessfull"); //Console printout that it was in the onResponse methods
                System.out.println(response.toString()); // Console print out of the request
                txtResponse.setText(response.toString()); //In the screen it should show up the array
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
    }
}
