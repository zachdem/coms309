package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.homepage.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WestSideMarket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_west_side_market);
    }


    private TextView txtResponse;

    private String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/west_side_market";

    /**
     * Making the JSON Array request
     */
    private void makeJsonArrayRequest(final ArrayList<String> tl, final ArrayAdapter<String> arrAdapt){
        // making the new object


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.optJSONObject(i);
                        String line = object.optString("item_name");
                        if(line != null){
                            tl.add(line);
                        }
                    }

                    arrAdapt.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }
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


}
