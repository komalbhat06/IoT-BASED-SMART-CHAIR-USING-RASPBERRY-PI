package com.example.chair_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class signup extends AppCompatActivity {
    EditText etName;


    EditText etMob;
    EditText etEmail;
    EditText etPass;


    Spinner sp;
    Button button;

    String name, mobile,  email,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etName = (EditText) findViewById(R.id.etName);


        etMob = (EditText) findViewById(R.id.etMob);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);



        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();

                mobile = etMob.getText().toString();
                email = etEmail.getText().toString();
                password = etPass.getText().toString();

                try {
                    send_data();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void send_data() throws IOException, JSONException {
        URL url = new URL(Global.url + "signup");
        JSONObject jsn = new JSONObject();

        jsn.put("name", name);
        jsn.put("mobile", mobile);
        jsn.put("email", email);
        jsn.put("password", password);




        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        String response = null;
        response = HttpConnection.getResponse(url, jsn);
        if (response.equalsIgnoreCase("ok")) {
            Intent intennt = new Intent(signup.this, login.class);
            startActivity(intennt);
            Toast.makeText(signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        }  else {
            Toast.makeText(signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
