package com.example.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;

    private String urlJsonObj = "http://coms-309-ks-6.misc.iastate.edu:8080/orders";

    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.Login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
                makeJsonObjectRequest();

            }
        });
    }

    private void makeJsonObjectRequest() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            // Initialize a new JsonObjectRequest instance
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, urlJsonObj, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            // Do something with response
                            //mTextView.setText(response.toString());

                            // Process the JSON
                            try {
                                System.out.println("Test");
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


            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private void makeJsonObjectRequest() {
        JSONObject js = new JSONObject();
        try {
            js.put("user_id",1);
            js.put("netid", "test_netid");
            js.put("password", "test_password");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlJsonObj, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Success");
                Log.d(TAG, response.toString() + " hello");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });

                queue.add(jsonObjectRequest);
                //AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
*/

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}
