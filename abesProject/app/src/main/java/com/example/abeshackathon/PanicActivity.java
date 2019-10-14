package com.example.abeshackathon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PanicActivity extends AppCompatActivity {
    TextView countdown;
    Button cancel;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic);

        countdown = findViewById(R.id.countdown);
        cancel = findViewById(R.id.panicCancel);
        relativeLayout = findViewById(R.id.progress);
        CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {

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
            }
        });


    }


    void panicTrigger() {
        countdown.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);
        Log.e("panic reached", " ");

    }
}
