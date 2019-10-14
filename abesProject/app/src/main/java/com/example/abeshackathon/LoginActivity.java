package com.example.abeshackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.abeshackathon.Apiinterface.Login;
import com.example.abeshackathon.JsonBody.Logindata;
import com.example.abeshackathon.Receiveddata.Loginresponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Gson gson=new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputEditText username,password;
        Button login;

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.buttonlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("button",username.getText().toString());
                Logindata logindata=new Logindata();
                logindata.setPassword(password.getText().toString());
                logindata.setUsername(username.getText().toString());
                Login loginrequest=Retro.createService(Login.class);
                Call<Loginresponse>  call=loginrequest.requestresponse(logindata);
                call.enqueue(new Callback<Loginresponse>() {
                    @Override
                    public void onResponse(Call<Loginresponse> call, Response<Loginresponse> response) {
                        Loginresponse loginresponse=response.body();
                        Log.e("response",gson.toJson(response.body()));
                        if(loginresponse.getStatus().equalsIgnoreCase("invalid username")||loginresponse.getStatus().equalsIgnoreCase("wrong password"))
                        {
                            Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                        }
                        else{

                            Log.e("check","true");
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<Loginresponse> call, Throwable t) {

                    }
                });

            }
        });
    }
}
