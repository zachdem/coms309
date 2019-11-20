package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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

    ImageButton cartButton;
    private Double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__location);

        TextView Pagetitle  = findViewById(R.id.textView2);

        urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/"+getIntent().getStringExtra("URL");

        cartButton = findViewById(R.id.cartButton);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartActivity();
                System.out.println("clicked button");
            }
        });


        Pagetitle.setText(getIntent().getStringExtra("Item"));
        Pagetitle.setTextColor(Color.parseColor("#C30107"));



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

        final ListView Listv = findViewById(R.id.allLists);

        Listv.setAdapter(arrayAdapter);

        makeJsonArrayRequest(tubeLines, arrayAdapter);

        Listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemDetail = ((ArrayAdapter) Listv.getAdapter()).getItem(position).toString();


                String tempItemPrice;
                String tempItemName;
                String tempLocation;

                int index = itemDetail.indexOf('$');

                tempItemPrice = itemDetail.substring(index + 1);
                tempItemName = itemDetail.substring(0, index - 1);
                tempLocation = getIntent().getStringExtra("URL");

                CartItem cartItem = new CartItem(tempItemName, tempItemPrice, tempLocation);
                Cart.cartList.add(cartItem);


                System.out.println("total = " + total);


            }
        });


    }

    String urlJsonObj = " ";

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



