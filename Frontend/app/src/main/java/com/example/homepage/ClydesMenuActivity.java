package com.example.homepage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ClydesMenuActivity extends AppCompatActivity {

    private ImageButton cartButton;
    Cart cart = new Cart();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clydes_menu);

        cartButton = findViewById(R.id.cartButton);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartActivity();
            }
        });
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemDetail = ((ArrayAdapter) lv.getAdapter()).getItem(position).toString();
                System.out.println(itemDetail);
                boolean found = false;
                String tempItemPrice;
                String tempItemName;

                int index = itemDetail.indexOf('$');

                tempItemPrice = itemDetail.substring(index + 1);
                tempItemName = itemDetail.substring(0, index - 1);
                CartItem cartItem = new CartItem(tempItemName, tempItemPrice);
                Cart.cartList.add(cartItem);
                System.out.println(Cart.cartList);

            }
            });



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
                    String itemNameString = object.optString("item_name");
                    String itemPriceString = object.optString("item_price");
                    String format = String.format("%1$s $%2$s0", itemNameString, itemPriceString);
                    if(itemNameString != null){
                        tl.add(format);
                        System.out.println(format);
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
        return true;
    }

    public void openCartActivity(){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }




}
