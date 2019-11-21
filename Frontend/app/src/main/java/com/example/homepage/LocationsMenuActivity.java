package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class LocationsMenuActivity extends AppCompatActivity {


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_locations_menu);


            Button clydesButton = findViewById(R.id.clydes_button);
            Button westSideButton = findViewById(R.id.west_side_button);
            Button southSideButton = findViewById(R.id.south_side_button);
            Button HawthornButton = findViewById(R.id.hawthorn_button);
            Button eastSideButton = findViewById(R.id.east_side_button);
            Button whirlyBirdsButton = findViewById(R.id.whirly_button);
            Button MUbutton = findViewById(R.id.mu_button);
            Button bookendsButton = findViewById(R.id.bookends_button);


            clydesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LocationsMenuActivity.this, NewLocation.class);
                    intent.putExtra("Item", "Clydes");
                    intent.putExtra("URL", "clydes");
                    startActivity(intent);
                }
            });


            westSideButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LocationsMenuActivity.this, NewLocation.class);
                    intent.putExtra("Item", "West Side Market");
                    intent.putExtra("URL", "west_side_market");
                    startActivity(intent);
                }
            });


            southSideButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LocationsMenuActivity.this, NewLocation.class);
                    intent.putExtra("Item", "South Side Market");
                    intent.putExtra("URL", "south_side_market");
                    startActivity(intent);
                }
            });

            HawthornButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LocationsMenuActivity.this, NewLocation.class);
                    intent.putExtra("Item", "Hawthorn Market");
                    intent.putExtra("URL", "hawthorn");
                    startActivity(intent);
                }
            });

            eastSideButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LocationsMenuActivity.this, NewLocation.class);
                    intent.putExtra("Item", "East Side Market");
                    intent.putExtra("URL", "east_side_market");
                    startActivity(intent);
                }
            });

            whirlyBirdsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LocationsMenuActivity.this, NewLocation.class);
                    intent.putExtra("Item", "WhirlyBirds Market");
                    intent.putExtra("URL", "whirlybirds");
                    startActivity(intent);
                }
            });

            MUbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LocationsMenuActivity.this, NewLocation.class);
                    intent.putExtra("Item", "Memorial Union");
                    intent.putExtra("URL", "memorial_union_market_and_cafe");
                    startActivity(intent);
                }
            });

            bookendsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LocationsMenuActivity.this, NewLocation.class);
                    intent.putExtra("Item", "Bookends Cafe");
                    intent.putExtra("URL", "bookends_cafe");
                    startActivity(intent);
                }
            });
        }
    }
