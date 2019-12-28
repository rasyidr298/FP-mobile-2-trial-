package com.coswick.travelinktrial.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.activity.FormPembelian;

public class DetailWisataPopuler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata_populer);

        ImageView gambar = findViewById(R.id.pop_det_gambar);
        TextView nm_wisata = findViewById(R.id.pop_det_nm_wisata);
        TextView kategori = findViewById(R.id.pop_det_kategori);
        TextView deskripsi = findViewById(R.id.pop_det_desc);
        TextView harga = findViewById(R.id.pop_det_harga);

        gambar.setImageResource(getIntent().getExtras().getInt("img"));
        nm_wisata.setText(getIntent().getStringExtra("title"));
        kategori.setText(getIntent().getStringExtra("kat"));
        deskripsi.setText(getIntent().getStringExtra("desc"));
        harga.setText(getIntent().getStringExtra("harga"));

    }

    public void pop_onClickBeli(View view) {
        startActivity(new Intent(this, FormPembelian.class));
    }
}
