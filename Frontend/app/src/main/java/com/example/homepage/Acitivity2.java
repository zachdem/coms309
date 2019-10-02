package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.homepage.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;


public class Acitivity2 extends AppCompatActivity {



    private Button Button;

    public void openActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    // json object response url
    private String urlJsonObj = "https://coms-309-ks-6.misc.iastate.edu:8080/orders";


    private static String TAG = MainActivity.class.getSimpleName();


    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity2);

        Button = findViewById(R.id.Clydes);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
                makeJsonObjectRequest();

            }
        });
        txtResponse = findViewById(R.id.txtResponse);





    }

    /**
     * Method to make json object request where json response starts wtih {
     * */
    private void makeJsonObjectRequest() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
        @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String order_id = response.getString("1");
                    String customer_id = response.getString("1");
                    String runner_id = response.getString("1");
                    String pending_order = response.getString("Yes");
                    String item_id = response.getString("mobile");
                    String final_order = response.getString("No");
                    String delivery_address = response.getString("123 Cyclone Ave");

                    jsonResponse = "";
                    jsonResponse += "order_id:" + order_id + "\n\n";
                    jsonResponse += "customer_id: " + customer_id + "\n\n";
                    jsonResponse += "runner_id: " + runner_id + "\n\n";
                    jsonResponse += "pending order" + pending_order + "\n\n";
                    jsonResponse += "item_id:" + item_id + "\n\n";
                    jsonResponse += "Delivery Address" + delivery_address + "\n\n";
                    jsonResponse += "Order Finalized" + final_order + "\n\n";
                    txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
