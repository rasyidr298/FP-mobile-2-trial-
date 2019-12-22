package com.coswick.travelinktrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.coswick.travelinktrial.Fragment.FragmentHome;
import com.coswick.travelinktrial.Fragment.FragmentMyTickets;
import com.coswick.travelinktrial.Fragment.FragmentProfile;
import com.coswick.travelinktrial.util.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNav extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle togle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);

        loadFragment(new FragmentHome());

        navigationView = findViewById(R.id.menu_Nav);
        navigationView. setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment !=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
            return  true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case  R.id.home_menu:
                fragment = new FragmentHome();
                break;
            case R.id.tickets_menu:
                fragment = new FragmentMyTickets();
                break;
            case R.id.profile_menu:
                fragment = new FragmentProfile();
                break;

        }
        return loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        confirmation();
    }

    private void confirmation(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BottomNav.this);
        alertDialogBuilder.setTitle(Constants.TXT_ALERT);
        alertDialogBuilder.setIcon(R.drawable.ic_profile);

        alertDialogBuilder.setMessage(Constants.TXT_ALERT_MSG).setCancelable(false)
                .setPositiveButton(Constants.TXT_BUTUN_YES,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                .setNegativeButton(Constants.TXT_BUTUN_NO,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}