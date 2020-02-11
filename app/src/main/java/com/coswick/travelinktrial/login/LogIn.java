package com.coswick.travelinktrial.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.activity.BottomNav;

public class LogIn extends AppCompatActivity {
    Button btn_login;
    SharedPrefManager sp;
    EditText nama, email;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        sqliteHelper = new SqliteHelper(this);
        nama = findViewById(R.id.edit_username);
        email = findViewById(R.id.edit_password);
        btn_login = findViewById(R.id.btn_login);
        sp = new SharedPrefManager(this);

        ceklogin();
        login();

    }

    public void login() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = nama.getText().toString();
                String pass = email.getText().toString();


                User currentUser = sqliteHelper.Authenticate(new User(null, user, pass));

                sp.saveSPString(SharedPrefManager.SP_NAMA, user);
                sp.saveSPString(SharedPrefManager.SP_EMAIL, pass);

                sp.saveSPBolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                if (currentUser != null) {
                    createNotification("Berhasil Login",LogIn.this);
                    Intent intent = new Intent(LogIn.this, BottomNav.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LogIn.this, "Periksa Kembali Username atau Password Anda", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void ceklogin() {
        if (sp.Login()) {
            startActivity(new Intent(LogIn.this, BottomNav.class));
            finish();
        }
    }

    public void createNotification(String aMessage, Context context) {
        NotificationManager notifManager = null;
        final int NOTIFY_ID = 0;
        String id = "TestChannel";
        String title = "simple Notification";
        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if (notifManager == null) {
            notifManager = (NotificationManager) context.getSystemService(
                    Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, title, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, BottomNav.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentTitle(aMessage)                            // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                    .setContentText(context.getString(R.string.app_name)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else {
            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, BottomNav.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentTitle(aMessage)                            // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                    .setContentText(context.getString(R.string.app_name)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }

    public void onRegisterClick(View view) {
        startActivity(new Intent(this, Register.class));
    }
}
