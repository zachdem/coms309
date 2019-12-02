package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button placeOrder;
    private TextView totaltxt;
    private String placeOrderURL = "http://" + GlobalAppInfo.serverName + ":8080/orders/place_order";
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

            VolleyCallback callback = new VolleyCallback() {
                @Override
                public void onVolleyResponse(String result) {
                    if(result.equals("received")){
                        Cart.cartList.clear();
                        openUserHome();
                    }
                    System.out.println(result);
                }
            };

            HttpRequests.httpPost(jsonArray.toString(), placeOrderURL,this, callback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openUserHome(){
        Intent intent = new Intent(this, UserHomeActivity.class);
        startActivity(intent);
    }

}
