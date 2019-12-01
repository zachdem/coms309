package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class UserHomeActivity extends AppCompatActivity {

    private Button orderButton, refreshButton;
    //private Button chatButton;

    private ArrayAdapter arrayAdapter;

    private ArrayList<String> orderList;

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        orderButton = findViewById(R.id.order_button);
        //chatButton = findViewById(R.id.chat_button);
        refreshButton = findViewById(R.id.refresh_orders_button);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openLocationsActivity();

            }
        });


        /*chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openChatActivity();

                updateOrderList();
            }
        });*/


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOrderList();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateOrderList();

    }

    public void openLocationsActivity() {
        Intent intent = new Intent(this, LocationsMenuActivity.class);
        startActivity(intent);
    }

  /*  public void openChatActivity() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }*/

    private void updateOrderList() {
        orderList = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orderList);

        lv = findViewById(R.id.order_list_view);

        lv.setAdapter(arrayAdapter);

        VolleyCallback callback = new VolleyCallback() {
            @Override
            public void onVolleyResponse(String result) {
                ArrayList<String> tempList = new ArrayList<>();
                JSONArray arr = null;
                try {
                    arr = new JSONArray(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("JSON Array: " + arr);
                System.out.println(result);
                System.out.println(arr.length());
                for (int i = 0; i < arr.length(); i++) {
                    System.out.println(i);
                    JSONObject obj = arr.optJSONObject(i);
                    System.out.println(obj);
                    String orderID = obj.optString("order_id");
                    String pendingOrder = obj.optString("pending_order");
                    String order = "     Order Number: " + orderID + ", Pending Order: " + pendingOrder;
                    if (orderID != null) {
                        tempList.add(order);
                    }
                }

                orderList.clear();
                orderList.addAll(tempList);
                arrayAdapter.notifyDataSetChanged();
            }
        };

        HttpRequests.httpGet("http://" + GlobalAppInfo.serverName + ":8080/orders/" + User.userNetid, this, callback);
    }


}
