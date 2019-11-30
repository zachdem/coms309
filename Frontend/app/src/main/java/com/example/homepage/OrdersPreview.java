package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;

public class OrdersPreview extends AppCompatActivity {


    private String JsonUrlPost = "http://" + GlobalAppInfo.serverName + ":8080/orders/updateRunner";


    // This page is strictly for the runners and viewing their pending orders that were sent from the users.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders__preview);


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
                System.out.println(result);
            }
        };

        HttpRequests.httpPost(jsonObject.toString(), JsonUrlPost, this, callback);
    }
}
