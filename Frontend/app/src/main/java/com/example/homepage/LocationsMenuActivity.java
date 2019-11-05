package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LocationsMenuActivity extends AppCompatActivity {

    private Button clydesButton;
    private Button westSideButton;
    private Button southSideButton;
    private Button muButton;
    private Button hawthornButton;
    private Button eastSideButton;
    private Button whirlyBirdsButton;
    private Button bookendsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_menu);

        clydesButton = findViewById(R.id.clydes_button);

        westSideButton = findViewById(R.id.west_side_button);

        southSideButton = findViewById(R.id.south_side_button);

        clydesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationsMenuActivity.this, New_Location.class);
                //Intent.putExtra("Cydles", "Cyldes Menu Page");
                startActivity(intent);
                //openClydesMenuActivity();
            }
        });

        westSideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openWestSideActivity();
            }
        });

        southSideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSouthsideActivity();
            }
        });
    }


    public void openClydesMenuActivity() {
        Intent intent = new Intent(this, ClydesMenuActivity.class);
        startActivity(intent);
    }


    public void openWestSideActivity() {
        Intent intent = new Intent(this, WestSideMarket.class);
        startActivity(intent);
    }

    public void openSouthsideActivity() {
        Intent intent = new Intent(this, South_Side_Market.class);
        startActivity(intent);
    }


}
