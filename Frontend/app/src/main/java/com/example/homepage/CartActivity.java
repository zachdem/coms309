package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button placeOrder;
    private TextView totaltxt;
    private String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/orders/place_order";
    private Double total = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.itemsRview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        totaltxt = findViewById(R.id.totalField);

        total = 0.0;
        for(int i = 0; i < Cart.cartList.size(); i++){
            total += Cart.cartList.get(i).itemPrice;
        }

        totaltxt.setText("$" + total.toString());

        if(totaltxt.length() == 5){
            totaltxt.setText("$" + total.toString() + "0");
        }
        placeOrder = findViewById(R.id.placeOrderButton);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOrder();
            }
        });
    }


    private void sendOrder() {
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < Cart.cartList.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("item_name", Cart.cartList.get(i).itemName);
                jsonObject.put("location_name", Cart.cartList.get(i).locationName);
                jsonObject.put("netid", User.userNetid);
                jsonArray.put(jsonObject);


            }

            System.out.println(jsonArray.toString());

            final String requestBody = jsonArray.toString();

            RequestQueue requestQueue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlJsonObj, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("error");
                    System.out.println(error);
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");

                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
            };
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
