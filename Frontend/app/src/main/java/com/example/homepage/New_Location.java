package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class New_Location extends AppCompatActivity {


    private Button CyldesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__location);


        CyldesButton = findViewById(R.id.clydes_button);

      /*  CyldesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationsMenuActivity.this, South_Side_Market.class);
                Intent.put
            }
        });*/
    }
}
