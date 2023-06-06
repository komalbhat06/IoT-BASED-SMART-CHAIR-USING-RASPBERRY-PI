package com.example.chair_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class login extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    EditText usr;
    EditText pwd;
    TextView studname, studemail;
    Button lbtn;
    Intent intent = null;
    String mob, pass;
    TextView tv;
    Intent i1,i2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usr = findViewById(R.id.etmob);
        pwd = findViewById(R.id.pw);
        lbtn = findViewById(R.id.loginbtn);
        tv = findViewById(R.id.tv);
        i1=new Intent(this, MainActivity.class);
        i2=new Intent(this,signup.class);



        /*to store username and password*/
        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);
        //Toast.makeText(this, username+password, Toast.LENGTH_SHORT).show();
        if (username == null || password == null) {
            getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                    .edit()
                    .putString(PREF_USERNAME, usr.getText().toString())
                    .putString(PREF_PASSWORD, pwd.getText().toString())
                    .apply();
        } else {
            usr.setText(username);
            pwd.setText(password);
        }
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(i2);
            }
        });
        //add the function to connect to database
        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                        .edit()
                        .putString(PREF_USERNAME, usr.getText().toString())
                        .putString(PREF_PASSWORD, pwd.getText().toString())
                        .apply();
                mob = usr.getText().toString().trim();
                pass = pwd.getText().toString().trim();
                login_check();

            }
        });


    }


    public void login_check()
    {
        try
        {
            URL url = new URL(Global.url + "login");
            JSONObject jsn = new JSONObject();
            jsn.put("mob",mob);
            jsn.put("password",pass);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
            StrictMode.setThreadPolicy(policy);
            final String response = HttpConnection.getResponse(url,jsn);
            Log.d("response",response);
            //Toast.makeText(LoginMainActivity.this,response,Toast.LENGTH_LONG).show();
            if(response.equalsIgnoreCase(""))
            {
                Toast.makeText(login.this,"Not Matching", Toast.LENGTH_SHORT).show();

            }
            else
            {

                MainActivity.name=response;
                MainActivity.mob=mob;

                startActivity(i1);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
