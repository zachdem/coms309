package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    private Button loginButton, signUpButton;
    private EditText netidEditText, passwordEditText;
    private TextView textview;


    private String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/userlogin";
    private String urlJsonRunner = "http://coms-309-ks-6.misc.iastate.edu:8080/runnerlogin";
    private Boolean isUser = true;
    private static String TAG = MainActivity.class.getSimpleName();


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
                if (isUser) {
                    verifyCredentials(netidEditText.getText().toString(), passwordEditText.getText().toString());
                } else if(!isUser) {
                    verifyCredentialsRunner(netidEditText.getText().toString(), passwordEditText.getText().toString());
                }
            }

        });


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupPage();
            }
        });
    }

    private void verifyCredentials(String netId, String password) {
            try {
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("netid", netId);
                jsonBody.put("password", password);
                final String requestBody = jsonBody.toString();
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                StringRequest postRequest = new StringRequest(Request.Method.POST, urlJsonObj, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        if (verifyUser(response)) {
                            openHomePageActivity();
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
            isUser = false;

    }

    private void verifyCredentialsRunner(String netId, String password) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("netid", netId);
            jsonBody.put("password", password);
            final String requestBody = jsonBody.toString();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest postRequest = new StringRequest(Request.Method.POST, urlJsonRunner, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    if (verifyUser(response)) {
                        openRunnerActivity();
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

        isUser = false;

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
        Intent intent = new Intent(this, Runners_Page.class);
        startActivity(intent);
    }


    public boolean verifyUser(String Response) {
        if(Response.equals("success")){
            return true;
        }
        return false;
    }
}
