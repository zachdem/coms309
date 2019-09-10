package com.example.rock_paper_scissors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {


    Button b_rock, b_paper, b_scissors;
    TextView tv_score;
    ImageView iv_ComputerChoice, iv_HumanChoice;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_rock = findViewById(R.id.b_rock);
        b_paper = findViewById(R.id.b_scissors);
        b_scissors = findViewById(R.id.b_scissors);


        iv_ComputerChoice = findViewById(R.id.iv_ComputerChoice);
        iv_HumanChoice = findViewById(R.id.iv_HumanChoice);

        tv_score = findViewById(R.id.tv_score);



        b_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.rock);
            }
        });

        b_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.paper);
            }
        });


        b_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.scissors);
            }
        });





    }
}
