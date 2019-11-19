package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.homepage.app.AppController;

import org.json.JSONArray;


public class UserHomeActivity extends AppCompatActivity {

    private Button orderButton, chatButton;


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
