package com.example.abeshackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView user;
        user=findViewById(R.id.user);
        String a=getIntent().getStringExtra("username");
        user.setText("Hello"+" "+a);

    }
}
