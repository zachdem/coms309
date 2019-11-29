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
import java.util.HashMap;
import java.util.Scanner;

public class RunnerPendingOrders extends AppCompatActivity {



    private HashMap<String, RunnersOrders> map;
    private String JsonUrl = "http://" + GlobalAppInfo.serverName + ":8080/orders/active_orders";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runner_pending_orders);


        /**
         *  Making the JSON Array request for Pending orders
         */


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

        final ListView Listv = findViewById(R.id.Pending);
        Listv.setAdapter(arrayAdapter);

        makeJsonArrayRequest(tubeLines, arrayAdapter);

        Listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String OrderDetail = ((ArrayAdapter) Listv.getAdapter()).getItem(position).toString();
                Scanner scan = new Scanner(OrderDetail);

                scan.next();
                String OrderNum = scan.next();

                scan.close();



                Intent intent = new Intent(RunnerPendingOrders.this, OrdersPreview.class);
                intent.putExtra("OrderID", OrderNum);
                intent.putExtra("Name", map.get(OrderNum).firstname + " " + map.get(OrderNum).lastname);
                intent.putExtra("item_name", map.get(OrderNum).item_name);
                intent.putExtra("FinalTotal", map.get(OrderNum).Finaltotal);
                intent.putExtra("Location", map.get(OrderNum).LocationName);
                startActivity(intent);
            }
        });
    }


    private void makeJsonArrayRequest(final ArrayList<String> tl, final ArrayAdapter<String> arrAdapt){


        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JsonUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                map = new HashMap<>();
                for (int i = 0; i < response.length(); i++){
                    JSONObject object = response.optJSONObject(i);
                    String line5 = object.optString("order_id");
                    String line2 = object.optString("first_name");
                    String line = object.optString("location_name");
                    String line1 = object.optString("item_price");
                    String line3 = object.optString("last_name");
                    String line4 = object.optString("item_name");

                    map.put(line5, new RunnersOrders(line2, line3, line, line4, line1));

                    String format = String.format("Order: %1$s Name: %2$s Location: %3$s", line5, line2, line);
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
