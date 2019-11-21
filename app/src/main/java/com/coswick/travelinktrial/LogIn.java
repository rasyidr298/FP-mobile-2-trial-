package com.coswick.travelinktrial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.coswick.travelinktrial.Fragment.BottomNav;
import com.coswick.travelinktrial.Fragment.FragmentHome;
import com.google.android.material.navigation.NavigationView;

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
                startActivity(new Intent(LogIn.this, BottomNav.class));
                Toast.makeText(getApplicationContext(), "Success Login", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onRegisterClick(View view) {
        startActivity(new Intent(this,Register.class));
    }


}
