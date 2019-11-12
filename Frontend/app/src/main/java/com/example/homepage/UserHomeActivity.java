package com.example.homepage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class UserHomeActivity extends AppCompatActivity {

    private Button orderButton, chatButton;
    private WebSocket ws = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        orderButton = findViewById(R.id.order_button);
        chatButton = findViewById(R.id.chat_button);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openLocationsActivity();

            }
        });


        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openChatActivity();

            }
        });

        final ArrayList<String> orderList = new ArrayList<>();

        final ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orderList);

        final ListView lv = findViewById(R.id.order_list_view);
        lv.setAdapter(arrayAdapter);


        WebSocketFactory factory = new WebSocketFactory().setConnectionTimeout(5000);

        // Create a WebSocket. The timeout value set above is used.
        try {
            ws = factory.createSocket("ws://10.26.162.153:8080/userhome");

            ws.addListener(new WebSocketAdapter() {

                @Override
                public void onTextMessage(WebSocket websocket, String message) throws Exception {
                    ArrayList<String> tempList = new ArrayList<>();
                    Gson g = new Gson();
                    JSONArray arr = new JSONArray(message);
                    System.out.println("JSON Array: " + arr);
                    System.out.println(message);
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
                    for (int i = 0; i < tempList.size(); i++) {
                        orderList.add(tempList.get(i));
                    }
                    arrayAdapter.notifyDataSetChanged();
                }
            });

            ws.connectAsynchronously();
            Thread.sleep(500);
            ws.sendText("test_netid");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void openLocationsActivity() {
        Intent intent = new Intent(this, LocationsMenuActivity.class);
        startActivity(intent);
    }

    public void openChatActivity() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

}
