package com.example.abeshackathon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.location.Address;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abeshackathon.Apiinterface.Login;
import com.example.abeshackathon.JsonBody.PanicData;
import com.example.abeshackathon.Receiveddata.Loginresponse;
import com.example.abeshackathon.Receiveddata.PanicResponse;
import com.example.abeshackathon.Receiveddata.WarnResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanicActivity extends AppCompatActivity implements LocationListener{
    TextView countdown;
    Button cancel;
    RelativeLayout relativeLayout;
    ProgressBar progressBar;
    // location
    LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;
    //location

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic);

        final SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String data = sharedPreferences.getString("logindata","data not stored");
        Loginresponse loginresponse=new Gson().fromJson(data,Loginresponse.class);
        PanicData panicdata = new PanicData();
        panicdata.setId(loginresponse.getId());
        txtLat = findViewById(R.id.textMsg1);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        context=this;


        Login login = Retro.createService(Login.class);
        Call<List<WarnResponse>> call = login.warnResponse(panicdata);
        call.enqueue(new Callback<List<WarnResponse>>() {
            @Override
            public void onResponse(Call<List<WarnResponse>> call, Response<List<WarnResponse>> response) {
                if(!response.isSuccessful()){
                    Log.e("widget Confirmation",response.message());
                    return;
                }

                int flag = response.body().get(0).getWarn();
                Log.e("confirmat...",flag+"");
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("id",flag+"");
                editor.apply();


            }

            @Override
            public void onFailure(Call<List<WarnResponse>> call, Throwable t) {
                Log.e("widget confirmation",t.getMessage());
            }
        });

        String flag = sharedPreferences.getString("id",-1+"");

        Log.e("warn code",flag+"");
        if (flag.equals("0")){
            startCountdown();
        }
        if(flag.equals("1")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are You Sure")
                    .setCancelable(false)
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startCountdown();
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    finish();
                }
            }).
             show();
        }
        if(flag.equals("-1")){
            Toast.makeText(this, "Login First", Toast.LENGTH_SHORT).show();
            return;
        }
        if(flag.equals("2")){
            Toast.makeText(this, "Unauthorised", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    String PanicText(){
        String resp="Alcohol:Frequesnt Consumer\nBlood Group:B+\nDiabetes:High\nSugar:Normal";
        try{
            SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
            String response = sharedPreferences.getString("PanicData","null");
            Type type = new TypeToken<List<PanicResponse>>() {
            }.getType();
            List<PanicResponse> panicResponses=new Gson().fromJson(response,type);
            resp = "Address:"+panicResponses.get(0).getLinkAddress()+"\nBlood Group:"+panicResponses.get(0).getLinkBloudGroup();
        }
        catch(Exception e){

        }

        return resp;
    }

    void startCountdown(){
        countdown = findViewById(R.id.countdown);
        cancel = findViewById(R.id.panicCancel);
        relativeLayout = findViewById(R.id.progress);
        progressBar = findViewById(R.id.ProgressBar2);

        final CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long l) {
                long sec = l / 1000;
                countdown.setText("SOS triggers in : "+sec);
                Log.e("time in Mili",sec+"  "+l);
            }
            @Override
            public void onFinish() {
                panicTrigger();
            }
        }.start();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                countDownTimer.cancel();
            }
        });
    }

//    void confirmation() {
//
//
//
//        Log.e("confirmation flag",flag[0]+"");
//
//
//    }
//

    void panicTrigger() {


        progressBar.setProgress(20);
        countdown.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String data = sharedPreferences.getString("logindata","data not stored");
        Log.e("panic reached",data);
        Loginresponse loginresponse=new Gson().fromJson(data,Loginresponse.class);
        PanicData panicdata = new PanicData();
        panicdata.setId(loginresponse.getId());
        Log.e("panic reached",loginresponse.getId());

        final Login login = Retro.createService(Login.class);
        Call<List<PanicResponse>> call = login.panicResponse(panicdata);
        call.enqueue(new Callback<List<PanicResponse>>() {
            @Override
            public void onResponse(Call<List<PanicResponse>> call, Response<List<PanicResponse>> response) {
                if (!response.isSuccessful()){
                    Log.e("unsuccessful"," ");
                    return;
                }
                SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("PanicData",new Gson().toJson(response.body()));
                editor.apply();

                if (sharedPreferences.getString("PanicData","null")=="null"){
                    Log.e("data not stored","");
                    return;
                }
                setPanicData();
                Log.e("panic data",new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<List<PanicResponse>> call, Throwable t) {
                Log.e("panic failed",t.getMessage());
            }
        });

    }


    void setPanicData(){
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String response = sharedPreferences.getString("PanicData","null");
        Type type = new TypeToken<List<PanicResponse>>() {
        }.getType();
        List<PanicResponse> panicResponses=new Gson().fromJson(response,type);
//            finalResponse = "Address:"+panicResponses.get(0).getLinkAddress()+"\nBlood Group:"+panicResponses.get(0).getLinkBloudGroup();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//        Log.e("location",new Gson().toJson(locationManager));
        progressBar.setProgress(30);
        onMsg(panicResponses.get(0).getLinkEmergencyContact());
        progressBar.setProgress(60);
        onMsg(panicResponses.get(0).getLinkDocNo());
        progressBar.setProgress(80);
        onCall("7376148354");
        progressBar.setProgress(100);
        finish();
        Log.e("call1",panicResponses.get(0).getLinkEmergencyContact());
        Log.e("call2",panicResponses.get(0).getLinkDocName());
    }


    private boolean permissionGrantedCall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.CALL_PHONE}, 1000);
            return false;
        }
        return true;
    }

    @SuppressLint("MissingPermission")
    private void onCall(String number) {
        if (number.length() > 0) {
            if (permissionGrantedCall()) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + number));
                Log.e("number call",number);
                this.startActivity(callIntent);
            }
        } else {
            Toast.makeText(this, "enter valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean permissionGrantedMsg() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.SEND_SMS}, 1000);
            return false;
        }
        return true;
    }

    private void onMsg(final String number){
        if (number.length() == 0) {
            return;
        }
        if (permissionGrantedMsg()) {

            sendSMS(number);


        }
    }

    public void locationRequest(){
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

    }

    public void sendSMS(String number) {

//        Uri sms_uri = Uri.parse("smsto:" + number);
//        Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
//        sms_intent.putExtra("sms_body", "-- From team ERP");
//        this.startActivity(sms_intent);
//        SmsManager.getDefault().sendTextMessage(number,null,"message here",null,null);


        String SMS_SENT_INTENT_FILTER = "com.yourapp.sms_send";
        String SMS_DELIVERED_INTENT_FILTER = "com.yourapp.sms_delivered";

        String message = "SOS!! I am seriously ill and being taken to nearest hospital " +"Current Location:"+"ABES Engineering College ,Campus-1,19th Km Stone,NH 24,Ghaziabad,Uttar Pradesh ";
        Log.e("number",number);

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(
                SMS_SENT_INTENT_FILTER), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(
                SMS_DELIVERED_INTENT_FILTER), 0);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(number, null, message, sentPI, deliveredPI);

    }

    @Override
    public void onLocationChanged(Location location) {
        txtLat = (TextView) findViewById(R.id.textMsg1);
        Log.e("location",new Gson().toJson(location));
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

}
