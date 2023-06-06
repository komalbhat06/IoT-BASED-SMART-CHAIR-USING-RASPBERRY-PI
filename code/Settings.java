package com.example.chair_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URL;

public class Settings extends AppCompatActivity {
    Button b;
    EditText ett,eth,ets1,ets2,ets3;
    String t="",h="",s1="",s2="",s3="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ett=(EditText) findViewById(R.id.ett);
        eth=(EditText) findViewById(R.id.eth);
        ets1=(EditText) findViewById(R.id.ets1);
        ets2=(EditText) findViewById(R.id.ets2);
        ets3=(EditText) findViewById(R.id.ets3);
        b=(Button) findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t=ett.getText().toString();
                h=eth.getText().toString();
                s1=ets1.getText().toString();
                s2=ets2.getText().toString();
                s3=ets3.getText().toString();
                new add_parameters().execute();
            }
        });
    }

    public class add_parameters extends AsyncTask<String, Void, String> {
        public void onPreExecute() {
        }

        public String doInBackground(String... args0) {
            try {

                String status="";


                URL url = new URL(Global.url + "add_parameters");

                JSONObject json = new JSONObject();
                json.put("t",t);
                json.put("h",h);
                json.put("s1",s1);
                json.put("s2",s2);
                json.put("s3",s3);
                json.put("user",MainActivity.mob);



                String response = HttpConnection.getResponse(url, json);
                status = response;



                return status;
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }



        @Override
        protected void onPostExecute(String status) {
            if(status.endsWith("null"))
            {
                status=status.substring(0,status.length()-4);

            }
            Toast.makeText(getApplicationContext(),status,Toast.LENGTH_LONG).show();


        }

    }


}