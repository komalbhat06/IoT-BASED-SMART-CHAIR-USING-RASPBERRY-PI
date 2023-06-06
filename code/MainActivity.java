package com.example.chair_app;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity{// implements SensorEventListener {
    public static String name="",mob="";
    TextView tv,tv1;
    String msg="";
    Button b,b1;
    Intent s;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView) findViewById(R.id.tv);
        tv1=(TextView) findViewById(R.id.tv1);
        b=(Button) findViewById(R.id.b);
        b1=(Button) findViewById(R.id.b1);

        imageView=(ImageView) findViewById(R.id.imageView);



        tv.setText("Welcome "+name);
        s=new Intent(this, Settings.class);

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            new PerformBackgroundTask().execute();

                        } catch (Exception e) {

                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(s);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new analyze().execute();
            }
        });
    }

public class PerformBackgroundTask extends AsyncTask<String, Void, String> {
    public void onPreExecute() {
    }

    public String doInBackground(String... args0) {
        try {

            String status="";


            URL url = new URL(Global.url + "check_sensor_data");

            JSONObject json = new JSONObject();

            json.put("user",mob);


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
    if(!status.equals(""))
    {
        String a[]=status.split("@@");
        msg=a[0];
        String b[]=a[1].split("#");
        msg=msg+"\nTemperature "+b[0]+"\nHumidity "+b[1]+"\nS1 "+b[2]+"\nS2 "+b[3]+"\nS3 "+b[4];
        tv1.setText(msg);

    }

    }

}
    public class analyze extends AsyncTask<String, Void, String> {
        public void onPreExecute() {
        }

        public String doInBackground(String... args0) {
            try {

                String status="";


                URL url = new URL(Global.url + "analyze");

                JSONObject json = new JSONObject();

                json.put("user",mob);


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
            if(!status.equals(""))
            {
                String a[]=status.split("##");

                msg="Correct "+a[0]+"\nIncorrect "+a[1];
                tv1.setText(msg);
                Picasso.with(getApplicationContext())
                        .load(Global.url+"piechart.png")
                        .into(imageView);
            }

        }

    }

}