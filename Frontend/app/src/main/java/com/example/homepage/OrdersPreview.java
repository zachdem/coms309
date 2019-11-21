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


    private String JsonUrlPost = " ";


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
        try {
            //JSONArray jsonArray = new JSONArray();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("netid", Runner.Netid);
            jsonObject.put("order_id", getIntent().getStringExtra("OrderID"));



            final String requestBody = jsonObject.toString();

            System.out.println(requestBody);
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, JsonUrlPost, new Response.Listener<String>() {
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
