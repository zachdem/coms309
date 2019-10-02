package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class Activity2 extends AppCompatActivity {



    private Button Button;

    public void openActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    // json object response url
    private String urlJsonObj = "https://coms-309-ks-6.misc.iastate.edu:8080/orders";


    private static String TAG = MainActivity.class.getSimpleName();


    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity2);

        Button = findViewById(R.id.Clydes);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openActivity3();
                makeJsonObjectRequest();

            }
        });
        txtResponse  =  findViewById(R.id.txtResponse);





    }

    /**
     * Method to make json object request where json response starts wtih {
     * */
    private void makeJsonObjectRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                urlJsonObj,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try {
                            //System.out.println("Test");
                            // Get the JSON array
                            //JSONArray array = response.getJSONArray("students");

//                            // Loop through the array elements
//                            for(int i=0;i<array.length();i++){
//                                // Get current json object
//                                JSONObject student = array.getJSONObject(i);
//
//                                // Get the current student (json object) data
//                                String firstName = student.getString("firstname");
//                                String lastName = student.getString("lastname");
//                                String age = student.getString("age");
//
//                                // Display the formatted json data in text view
//                                mTextView.append(firstName +" " + lastName +"\nage : " + age);
//                                mTextView.append("\n\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        //System.out.println("Test");
                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
}
