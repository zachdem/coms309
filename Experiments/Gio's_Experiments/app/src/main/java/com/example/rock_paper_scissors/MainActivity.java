package com.example.rock_paper_scissors;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;




public class MainActivity extends AppCompatActivity {


    Button b_rock, b_paper, b_scissors;
    TextView tv_score;
    ImageView iv_ComputerChoice, iv_HumanChoice;

    int HumanScore, ComputerScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_rock = (Button) findViewById(R.id.b_rock);
        b_paper = findViewById(R.id.b_scissors);
        b_scissors = findViewById(R.id.b_scissors);


        iv_ComputerChoice = findViewById(R.id.iv_ComputerChoice);
        iv_HumanChoice = findViewById(R.id.iv_HumanChoice);

        tv_score = findViewById(R.id.tv_score);


        b_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.rock);

                String message = play_turn("rock");
                Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
                tv_score .setText("Score Human: " + Integer.toString(HumanScore) + "Computer: " + Integer.toString(ComputerScore) );
            }
        });

        b_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.paper);

                String message = play_turn("paper");
                Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
                tv_score .setText("Score Human: " + Integer.toString(HumanScore) + "Computer: " + Integer.toString(ComputerScore) );
            }
        });


        b_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.scissors);
                String message = play_turn("scissors");
                Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
                tv_score .setText("Score Human: " + Integer.toString(HumanScore) + "Computer: " + Integer.toString(ComputerScore) ); 
            }
        });
    }

    public String play_turn(String player_choice) {
        String Computer_choice = "";
        Random r = new Random();

        // choose
        int Computer_choice_number = r.nextInt(3) + 1;

        if (Computer_choice_number == 1) {
            Computer_choice = "rock";
        } else if (Computer_choice_number == 2) {
            Computer_choice = "scissors";

        } else if (Computer_choice_number == 3) {
            Computer_choice = "paper";
        } else


            // setting the comptuer to the image based on their choice
            if (Computer_choice == "rock") {
                iv_ComputerChoice.setImageResource(R.drawable.rock);
            }

            else if (Computer_choice == "paper") {
                iv_ComputerChoice.setImageResource(R.drawable.paper);
            }

            else if (Computer_choice == "scissors") {
                iv_ComputerChoice.setImageResource(R.drawable.scissors);
            }

        if (Computer_choice == player_choice) {
            return "Its a Tie, nobody won :(";
        } else if (player_choice == "rock" && Computer_choice == "scissors") {
            HumanScore++;
            return "Rock crushes scissors. You win!";
        } else if (player_choice == "rock" && Computer_choice == "paper") {
            ComputerScore++;
            return "Paper Covers Rock. Computer Wins :(";
        } else if (player_choice == "scissors" && Computer_choice == "rock") {
            ComputerScore++;
            return "Rock crushed Scissors. Computer Wins! :(";

        } else if (player_choice == "scissors" && Computer_choice == "paper") {
            HumanScore++;
            return "Scissors cuts paper. You win! :)";
        } else if (player_choice == "paper" && Computer_choice == "rock") {
            HumanScore++;
            return "Paper Covers rock. You win! :)";
        } else if (player_choice == "paper" && Computer_choice == "scissors") {
            ComputerScore++;
            return "Scissors cuts paper. Computer wins :(";
        }
        else return "Not sure";
    }
}