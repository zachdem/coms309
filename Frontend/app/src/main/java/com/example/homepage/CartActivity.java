package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/orders/place_order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.itemsRview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        placeOrder = findViewById(R.id.placeOrderButton);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("helllllloooooo");
                sendOrder();
            }
        });
    }

    private void sendOrder(){
        //JSONArray jsonArray = new JSONArray();
        //for(int i = 0; i < Cart.cartList.size(); i++){
            JSONObject jsonObject = new JSONObject();
            try{
//                jsonObject.put("item_name", Cart.cartList.get(i).itemName);
//                jsonObject.put("location_name", "clydes");
//                jsonObject.put("netid", User.userNetid);
                //jsonObject.put("hello");
                final String requestBody = "hello";
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlJsonObj, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("success");

                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error");
                        System.out.println(error);
                    }
                }){
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try{
                            return requestBody == null ? null : requestBody.getBytes("utf-8");

                        }catch (UnsupportedEncodingException uee){
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                            return null;
                        }
                    }
                };
                requestQueue.add(stringRequest);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }


//}
