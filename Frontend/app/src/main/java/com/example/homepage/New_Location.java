package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.homepage.app.AppController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class New_Location extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__location);

        TextView Pagetitle  = findViewById(R.id.textView2);



        Pagetitle.setText(getIntent().getStringExtra("Item"));
        Pagetitle.setTextColor(Color.parseColor("#C30107"));

         urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/"+getIntent().getStringExtra("URL");





        final ArrayList<String> tubeLines = new ArrayList<>();

        final ArrayAdapter<String> ArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tubeLines);
        final ListView Listv = findViewById(R.id.allLists);
        Listv.setAdapter(ArrayAdapter);
        makeJsonArrayRequest(tubeLines, ArrayAdapter);

    }

    private String urlJsonObj = "";


    /**
     * Making the JSON Array request
     */
    private void makeJsonArrayRequest(final ArrayList<String> tl, final ArrayAdapter<String> arrAdapt){
        // making the new object


        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                for (int i = 0; i < response.length(); i++){
                    JSONObject object = response.optJSONObject(i);
                    String line = object.optString("item_name");
                    String line1 = object.optString("item_price");
                    String format = String.format("%1$s $%2$s", line, line1);
                    if(line != null){
                        tl.add(format);
                    }
                }

                arrAdapt.notifyDataSetChanged();

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



