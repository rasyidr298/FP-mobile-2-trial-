package com.coswick.travelinktrial.Fragment;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.coswick.travelinktrial.activity.LogIn;
import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.SharedPref.SharedPrefManager;

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

        return view;
    }
}
