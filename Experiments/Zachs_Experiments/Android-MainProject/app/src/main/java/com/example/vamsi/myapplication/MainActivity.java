package com.example.vamsi.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    Button b1;
    TextView tx1;
    String REGISTER_URL= "http://coms-309-ks-6.misc.iastate.edu:8080/orders";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.bt1);
        tx1=(TextView)findViewById(R.id.tx1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //used for GET request
                register("vamsi","123");
            }
        });
    }
    private void register(String username, String password) {
        //Useful for GET request
        String urlSuffix = "?username="+username+"&password="+password;
        //extending the async class
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;//Loader
            //implementing methods for asyncTask
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait",null, true, true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tx1.setText(s);
            }
            @Override
            protected String doInBackground(String... params) {
                String s = params[0];//useful when making GET or post request
                BufferedReader bufferedReader = null;//BR for temporary storage of Input stream
                try {
                    URL url = new URL(REGISTER_URL);//Register_URL should be your API
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result="";
                    for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                        result+=line;
                    }
                    bufferedReader.close();
                    return result;
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Check your Internet connection",Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
        }
        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }

}
