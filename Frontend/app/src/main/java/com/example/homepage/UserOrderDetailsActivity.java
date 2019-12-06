package com.example.homepage;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserOrderDetailsActivity extends AppCompatActivity {


    private String orderInfoURL = "http://" + GlobalAppInfo.serverName + ":8080/orders/singleorder/";
    private TextView orderNumDisplay, locationDisplay, itemDisplay, totalDisplay, pendingDisplay, runnerDisplay;



    // This page is strictly for the runners and viewing their pending orders that were sent from the users.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        orderNumDisplay = findViewById(R.id.order_details_id);
        locationDisplay = findViewById(R.id.order_details_location);
        itemDisplay = findViewById(R.id.order_details_item);
        totalDisplay = findViewById(R.id.order_detail_total);
        pendingDisplay = findViewById(R.id.order_details_pending_order);
        runnerDisplay = findViewById(R.id.order_details_runner_name);
        orderNumDisplay.setText("Order Details");
    }

    @Override
    protected void onResume(){
        super.onResume();

        Integer orderID = getIntent().getIntExtra("orderID", 0);
        if(orderID != 0){
            VolleyCallback callback = new VolleyCallback() {
                @Override
                public void onVolleyResponse(String result) {
                    System.out.println("RESULT: " + result);
                    JSONObject obj = null;
                    try{
                        obj = new JSONObject(result);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    Integer orderNum = obj.optInt("order_id");
                    String location = obj.optString("location_name");
                    String item = obj.optString("item_name");
                    double total = obj.optDouble("item_price");
                    String strTotal = String.format("%.2f", total);
                    String pending = obj.optString("pending_order");
                    String runnerFirstName = obj.optString("runner_first_name");
                    String runnerLastName = obj.optString("runner_last_name");

                    orderNumDisplay.setText(Integer.toString(orderNum));
                    locationDisplay.setText(location);
                    itemDisplay.setText(item);
                    totalDisplay.setText(strTotal);
                    pendingDisplay.setText(pending);
                    runnerDisplay.setText(runnerFirstName + " " + runnerLastName);
                }

            };


            HttpRequests.httpGet( orderInfoURL + Integer.toString(orderID), this, callback);
        }

    }

}
