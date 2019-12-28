package com.coswick.travelinktrial.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.activity.BottomNav;
import com.coswick.travelinktrial.adapters.FavoriteAdapter;
import com.coswick.travelinktrial.model.FavoriteModel;
import com.coswick.travelinktrial.wisata.WisataAceh;
import com.coswick.travelinktrial.wisata.WisataBali;
import com.coswick.travelinktrial.wisata.WisataKlaten;
import com.coswick.travelinktrial.wisata.WisataMedan;
import com.coswick.travelinktrial.wisata.WisataSemua;
import com.coswick.travelinktrial.wisata.WisataSurabaya;
import com.coswick.travelinktrial.wisata.WisataWonogiri;
import com.coswick.travelinktrial.wisata.WisataYogya;

import java.util.List;

public class FragmentFavorite extends Fragment {

    private RecyclerView rv;
    private FavoriteAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rec_favorite);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        getFavData_terahir();

        return view;
    }

    private void getFavData_terahir() {
        List<FavoriteModel> favoriteLists = FragmentHome.favoriteDatabase_terahir.favoriteDao().getFavoriteData();
        adapter = new FavoriteAdapter(favoriteLists, getActivity());
        rv.setAdapter(adapter);
    }
}