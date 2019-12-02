package com.example.homepage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class UserOrderDetailsActivity extends AppCompatActivity {


    private String orderInfoURL = "http://" + GlobalAppInfo.serverName + ":8080/orders/updateRunner";


    // This page is strictly for the runners and viewing their pending orders that were sent from the users.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders__preview);
    }

    @Override
    protected void onResume(){
        super.onResume();

        //HTTP GET to get information for this order id

    }

}
