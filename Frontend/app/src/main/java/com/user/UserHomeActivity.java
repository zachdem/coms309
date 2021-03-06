package com.user;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.homepage.WebSocketUtil;
import com.util.GlobalAppInfo;
import com.util.HttpRequests;
import com.example.homepage.R;
import com.util.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class UserHomeActivity extends AppCompatActivity {

    private Button orderButton, refreshButton;
    //private Button chatButton;

    private TextView orderNotification, currentBalance;

    private ArrayAdapter<String>  arrayAdapter;

    private ArrayList<String> orderList;

    private ListView lv;

    private JSONArray orderHistoryArr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        orderButton = findViewById(R.id.order_button);
        //chatButton = findViewById(R.id.chat_button);
        refreshButton = findViewById(R.id.refresh_orders_button);
        orderNotification = findViewById(R.id.order_notificiation_tv);
        lv = findViewById(R.id.order_list_view);
        currentBalance = findViewById(R.id.current_balance_tv);
        orderList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orderList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = view.findViewById(android.R.id.text1);

                tv.setTextColor(Color.BLACK);

                tv.setTypeface(null, Typeface.BOLD);

                // Generate ListView Item using TextView
                return view;
            }
        };
        lv.setAdapter(arrayAdapter);


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
                refreshCurrentBalance();

            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject record = null;
                try{
                    record = orderHistoryArr.optJSONObject(position);
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                Integer orderID = record.optInt("order_id");

                //Pass orderID with via intent to activity that displays order information
                openOrderDetails(orderID);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Open websocket

        VolleyCallback callback = new VolleyCallback() {
            @Override
            public void onVolleyResponse(String result) {
                orderNotification.setText(result);
            }
        };

        WebSocketUtil.connectWebSocket(callback);
        WebSocketUtil.sendText(User.userNetid);
        updateOrderList();
        refreshCurrentBalance();

    }

    @Override
    protected void onPause() {
        super.onPause();
        orderNotification.setText("");
        WebSocketUtil.disconnectWebSocket();
        //Disconnect websocket
    }
    private void openLocationsActivity() {
        Intent intent = new Intent(this, LocationsMenuActivity.class);
        startActivity(intent);
    }

  /*  public void openChatActivity() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }*/

    private void updateOrderList() {
        VolleyCallback callback = new VolleyCallback() {
            @Override
            public void onVolleyResponse(String result) {
                ArrayList<String> tempList = new ArrayList<>();
                try {
                    orderHistoryArr = new JSONArray(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < orderHistoryArr.length(); i++) {
                    JSONObject obj = orderHistoryArr.optJSONObject(i);
                    String orderID = obj.optString("order_id");
                    String pendingOrder = obj.optString("pending_order");
                    String order = "                Order ID: " + orderID + ", Pending Order: " + pendingOrder;
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

    private void openOrderDetails(Integer orderID){
        Intent intent = new Intent(this, UserOrderDetailsActivity.class);
        intent.putExtra("orderID", orderID);
        startActivity(intent);
    }


    private void refreshCurrentBalance(){
        VolleyCallback callback = new VolleyCallback() {
            @Override
            public void onVolleyResponse(String result) {
                currentBalance.setText("Current Balance: $" + result);
            }
        };

        HttpRequests.httpGet("http://" + GlobalAppInfo.serverName + ":8080/user/balance/" + User.userNetid, this, callback);

    }



}
