package com.coswick.travelinktrial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.coswick.travelinktrial.Fragment.FragmentHome;

public class LogIn extends AppCompatActivity {
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this,FragmentHome.class);
                startActivity(intent);
            }
        });

    }
    public void onRegisterClick(View view) {
        startActivity(new Intent(this,Register.class));
    }


}
