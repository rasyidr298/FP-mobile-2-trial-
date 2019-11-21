package com.coswick.travelinktrial.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coswick.travelinktrial.R;

public class FragmentMyTickets extends Fragment {
    View v;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.fragment_my_tickets,container,false);

        return v;
    }
}
