package com.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.util.GlobalAppInfo;
import com.util.HttpRequests;
import com.example.homepage.R;
import com.runner.Runner;
import com.runner.RunnersPage;
import com.util.VolleyCallback;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button loginButton, signUpButton;
    private EditText netidEditText, passwordEditText;


    private String userLoginURL = "http://" + GlobalAppInfo.serverName + ":8080/userlogin";
    private String runnerLoginURL = "http://" + GlobalAppInfo.serverName + ":8080/runnerlogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Binds the activity_main.xml to this activity

        loginButton = findViewById(R.id.login_button);
        signUpButton = findViewById(R.id.sign_up_button);
        netidEditText = findViewById(R.id.net_id_etext);
        passwordEditText = findViewById(R.id.password_etext);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If we have clicked the button we need to pull the text from the EditText fields, netid, and password
                verifyUserCredentials(netidEditText.getText().toString(), passwordEditText.getText().toString());
                verifyRunnerCredentials(netidEditText.getText().toString(), passwordEditText.getText().toString());
            }

        });


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupPage();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //When we leave this activity clear the text fields
        netidEditText.setText("");
        passwordEditText.setText("");
    }

    private void verifyUserCredentials(final String netId, String password) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("netid", netId);
            jsonBody.put("password", password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
            VolleyCallback callback = new VolleyCallback() {
                @Override
                public void onVolleyResponse(String result) {
                    if (verifyUser(result)) {
                        User.userNetid = netId;
                        openHomePageActivity();
                    }
                }
            };
            System.out.println(userLoginURL);
            HttpRequests.httpPost(jsonBody.toString(), userLoginURL, this, callback);
    }

    private void verifyRunnerCredentials(final String netId, String password) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("netid", netId);
            jsonBody.put("password", password);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

            VolleyCallback callback = new VolleyCallback() {
                @Override
                public void onVolleyResponse(String result) {
                    if (verifyUser(result)) {
                        Runner.Netid = netId;
                        openRunnerActivity();
                    }
                }
            };

            HttpRequests.httpPost(jsonBody.toString(), runnerLoginURL, this, callback);

    }


    public void openHomePageActivity() {
        Intent intent = new Intent(this, UserHomeActivity.class);
        startActivity(intent);
    }

    public void openSignupPage() {
        Intent intent = new Intent(this, UserSignUpActivity.class);
        startActivity(intent);
    }

    public void openRunnerActivity() {
        Intent intent = new Intent(this, RunnersPage.class);
        startActivity(intent);
    }

    public boolean verifyUser(String Response) {
        if (Response.equals("success")) {
            return true;
        }
        return false;
    }
}
