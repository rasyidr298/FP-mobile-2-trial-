package com.coswick.travelinktrial.Fragment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.coswick.travelinktrial.activity.LogIn;
import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.SharedPref.SharedPrefManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FragmentProfile extends Fragment {
    View view;
    SharedPrefManager sp;
    TextView nama, email;
    Button btnlogout;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_profile,container,false);
        sp = new SharedPrefManager(getContext());
        nama = (TextView) view.findViewById(R.id.txt_nama);
        email = (TextView) view.findViewById(R.id.txt_email);
        btnlogout = (Button) view.findViewById(R.id.btn_Logout);

        nama.setText("" + sp.getKeyNama());
        email.setText("" + sp.getKeyEmail());
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogIn.class);
                startActivity(intent);
                sp.saveSPBolean(SharedPrefManager.SP_SUDAH_LOGIN, false);

                //		preferenceHelper.setLogin(false);
            }
        });

        //Collapsing Toolbar
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar_profile);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        return view;
    }

}

