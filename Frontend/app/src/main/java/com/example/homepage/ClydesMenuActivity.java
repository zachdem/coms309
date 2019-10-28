package com.example.homepage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.homepage.app.AppController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ClydesMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clydes_menu);

        final ArrayList<String> tubeLines = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tubeLines){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.parseColor("#C30107"));

                // Generate ListView Item using TextView
                return view;
            }
        };
        final ListView lv = findViewById(R.id.clydesListView);
        lv.setAdapter(arrayAdapter);
        makeJsonArrayRequest(tubeLines,arrayAdapter);

    }

    private String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/clydes";


    /**
     * Making the JSON Array request
     */
    public boolean makeJsonArrayRequest(final ArrayList<String> tl, final ArrayAdapter<String> arrAdapt){
        // making the new object


        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                for (int i = 0; i < response.length(); i++){
                    JSONObject object = response.optJSONObject(i);
                    String line = object.optString("item_name");
                    String line1 = object.optString("item_price");
                    String format = String.format("%1$s $%2$s0", line, line1);
                    if(line != null){
                        tl.add(format);

                    }
                }

                arrAdapt.notifyDataSetChanged();

                //System.out.println("Succcessfull"); //Console printout that it was in the onResponse methods
                //System.out.println(response.toString()); // Console print out of the request
                //txtResponse.setText(response.toString()); //In the screen it should show up the array
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return true;
    }






}
