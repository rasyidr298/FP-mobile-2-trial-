package com.coswick.travelinktrial.wisata_yogyakarta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.adapters.WisataYogyaAdapter;
import com.coswick.travelinktrial.model.PopulerModel;

import java.util.ArrayList;

public class WisataYogya extends AppCompatActivity {
    private WisataYogyaAdapter adapter;
    private ArrayList<PopulerModel> lstResep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_yogya);


        lstResep = new ArrayList<>();
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));
        lstResep.add(new PopulerModel(R.drawable.sedih,"judulllllllll","deskripsiiiii","15.000","kategoriiiii"));


        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);

        adapter = new WisataYogyaAdapter(this,lstResep);

        myrv.setAdapter(adapter);

        myrv.setLayoutManager(new GridLayoutManager(this,2));

        myrv.setHasFixedSize(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
