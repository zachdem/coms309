package com.runner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homepage.R;

public class RunnersPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runners_page);





        Button ordersPage = findViewById(R.id.Orders2);




        ordersPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openPendingOrders();
            }
        });
    }



    private void openPendingOrders() {
        Intent intent = new Intent(this, RunnerPendingOrders.class);
        startActivity(intent);
    }


}
