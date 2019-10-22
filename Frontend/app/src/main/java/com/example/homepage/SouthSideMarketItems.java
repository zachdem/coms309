package com.example.homepage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.homepage.app.AppController;

import org.json.JSONArray;

public class SouthSideMarketItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_south_side_market_items);
    }

    public String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/southsidemarket";

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
                //txtResponse.setText(response.toString()); //In the screen it should show up the array
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }

}
