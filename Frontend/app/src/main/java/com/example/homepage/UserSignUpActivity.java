package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class UserSignUpActivity extends AppCompatActivity {


    private String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/user_signup";

    private TextView txtResponse;
    private CheckBox Runner;
    private Button signUpButton;
    private EditText firstNameEtext, lastNameEtext, userNameEtext, passwordEtext, isuIDEtext, routingNumberEtext, accountNumberEtext, netIDEtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameEtext = findViewById(R.id.first_name_etext);
        lastNameEtext = findViewById(R.id.last_name_etext);
        userNameEtext = findViewById(R.id.user_name_etext);
        passwordEtext = findViewById(R.id.password_etext2);
        isuIDEtext = findViewById(R.id.isu_id_etext);
        routingNumberEtext = findViewById(R.id.routing_num_etext);
        accountNumberEtext = findViewById(R.id.account_num_etext);
        netIDEtext = findViewById(R.id.netid_etext);
        signUpButton = findViewById(R.id.sign_up_button);
        Runner = findViewById(R.id.Runner);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If we have clicked the button we need to pull the text from the EditText fields, netid, and password
                if (verifySignupInput(firstNameEtext.getText().toString(), lastNameEtext.getText().toString(), userNameEtext.getText().toString(),
                        passwordEtext.getText().toString(), isuIDEtext.getText().toString(), routingNumberEtext.getText().toString(), accountNumberEtext.getText().toString(),
                        netIDEtext.getText().toString()) == true) {
                    Integer isuID = Integer.parseInt(isuIDEtext.getText().toString());
                    Integer routingNumber = Integer.parseInt(routingNumberEtext.getText().toString());
                    Integer accountNumber = Integer.parseInt(accountNumberEtext.getText().toString());
                    sendSignUpRequest(firstNameEtext.getText().toString(), lastNameEtext.getText().toString(), userNameEtext.getText().toString(), passwordEtext.getText().toString(), isuID, routingNumber, accountNumber, netIDEtext.getText().toString());
                }
            }
        });
    }

    private void sendSignUpRequest(String firstName, String lastName, String username, String password, Integer isuID, Integer routingNumber, Integer accountNumber, String netID) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("first_name", firstName);
            jsonBody.put("last_name", lastName);
            jsonBody.put("username", username);
            jsonBody.put("password", password);
            jsonBody.put("isu_id", isuID);
            jsonBody.put("routing_number", routingNumber);
            jsonBody.put("account_number", accountNumber);
            jsonBody.put("netid", netID);
            final String requestBody = jsonBody.toString();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest postRequest = new StringRequest(Request.Method.POST, urlJsonObj, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    if (response.equals("signup_success")) {
                        openMainActivity();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                }
            }) {

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
            };
            requestQueue.add(postRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean verifySignupInput(String isuID, String routingNumber, String accountNumber, String firstName, String lastName, String userName, String password, String netID){
        // If we have clicked the button we need to pull the text from the EditText fields, netid, and password

        if (!isuID.equals("") && !routingNumber.equals("") && !accountNumber.equals("") && !firstName.equals("") && !lastName.equals("") &&
                !userName.equals("") && !password.equals("") && !netID.equals("")) {
            return true;
        }

        return false;
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openRunnerActivity() {
        Intent intent = new Intent(this, Runners_Page.class);
        startActivity(intent);
    }
}
