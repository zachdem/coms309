package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

public class OrdersPreview extends AppCompatActivity {


    private String JsonUrlPost = "http://" + GlobalAppInfo.serverName + ":8080/orders/updateRunner";


    // This page is strictly for the runners and viewing their pending orders that were sent from the users.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_preview);


        TextView Nametitle = findViewById(R.id.textView3);

        TextView fullOrder = findViewById(R.id.textView4);

        TextView FinalTotal = findViewById(R.id.textView5);

        TextView LocationName = findViewById(R.id.textView6);


        Nametitle.setText(getIntent().getStringExtra("Name"));

        fullOrder.setText(getIntent().getStringExtra("item_name"));

        FinalTotal.setText(getIntent().getStringExtra("FinalTotal"));

        LocationName.setText(getIntent().getStringExtra("Location"));


        final Button Pickup = findViewById(R.id.Pickup);


        Pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickupOrder();
            }
        });


    }


    private void PickupOrder() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("netid", Runner.Netid);
            jsonObject.put("order_id", getIntent().getStringExtra("OrderID"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        VolleyCallback callback = new VolleyCallback() {
            @Override
            public void onVolleyResponse(String result) {
                if(result.equals("received")){
                    sendToRunnerHome();
                }
            }
        };

        HttpRequests.httpPost(jsonObject.toString(), JsonUrlPost, this, callback);
    }

    private void sendToRunnerHome(){
        Intent intent = new Intent(this, RunnersPage.class);
        startActivity(intent);
    }
}
