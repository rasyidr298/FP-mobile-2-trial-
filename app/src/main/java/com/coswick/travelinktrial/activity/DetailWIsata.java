package com.coswick.travelinktrial.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coswick.travelinktrial.R;
import com.squareup.picasso.Picasso;

public class DetailWIsata extends AppCompatActivity {
    Spanned Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        ImageView gambar = findViewById(R.id.det_gambar);
        TextView nm_wisata = findViewById(R.id.det_nm_wisata);
        TextView kategori = findViewById(R.id.det_kategori);
        TextView deskripsi = findViewById(R.id.det_desc);
        TextView harga = findViewById(R.id.det_harga);

        Intent getImage = getIntent();

        String gettingImageUrl = getImage.getStringExtra("img");
        Picasso.with(DetailWIsata.this).load(gettingImageUrl).into(gambar);
        nm_wisata.setText(getIntent().getStringExtra("title"));
        kategori.setText(getIntent().getStringExtra("kat"));
        deskripsi.setText(getIntent().getStringExtra("desc"));
        harga.setText(getIntent().getStringExtra("harga"));

        deskripsi.setMovementMethod(LinkMovementMethod.getInstance());


        //Collapsing Toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_detail_wisata);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //Proses Back
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClickBeli(View view) {
        startActivity(new Intent(this, FormPembelian.class));
    }
}
