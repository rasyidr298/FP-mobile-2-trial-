package com.coswick.travelinktrial.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.db_crud_ormite.ClienteDAO;
import com.coswick.travelinktrial.model.TicketModel;
import com.coswick.travelinktrial.util.Constants;

public class FormPembelian extends AppCompatActivity implements View.OnClickListener{


    private EditText edtNama_Wisata, edtHarga,edtJumlahTicket, edtKategoriUmur, edtTanggalTransaksi, edtNama_Pemesan,edtNIK;
    private Button btnCheckout;
    private ClienteDAO dao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pembelian);
        dao = new ClienteDAO(this);
        initUi();


        //Collapsing Toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_form);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUi(){
        this.edtNama_Wisata = findViewById(R.id.edt_nama_wisata);
        this.edtHarga = findViewById(R.id.edt_harga);
        this.edtJumlahTicket = findViewById(R.id.edt_jumlah_ticket);
        this.edtKategoriUmur = findViewById(R.id.edt_kategori_umur);
        this.edtTanggalTransaksi = findViewById(R.id.edt_tanggal_transaksi);
        this.edtNama_Pemesan = findViewById(R.id.edt_nama_pemesan);
        this.edtNIK = findViewById(R.id.edt_nik);
        this.btnCheckout = findViewById(R.id.btn_checkout);
        configComponents();
    }

    private void configComponents(){
        this.edtNama_Wisata.requestFocus();
        this.edtNama_Wisata.setText("");
        this.edtNama_Wisata.setSelection(edtNama_Wisata.getText().length());

        this.edtHarga.setText("");
        this.edtHarga.setSelection(edtHarga.getText().length());

        this.edtJumlahTicket.setText("");
        this.edtJumlahTicket.setSelection(edtJumlahTicket.getText().length());

        this.edtKategoriUmur.setText("");
        this.edtKategoriUmur.setSelection(edtKategoriUmur.getText().length());

        this.edtTanggalTransaksi.setText("");
        this.edtTanggalTransaksi.setSelection(edtTanggalTransaksi.getText().length());

        this.edtNama_Pemesan.setText("");
        this.edtNama_Pemesan.setSelection(edtNama_Pemesan.getText().length());

        this.edtNIK.setText("");
        this.edtNIK.setSelection(edtNIK.getText().length());

        this.btnCheckout.setOnClickListener(FormPembelian.this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_checkout){
            saveCliente();
        }

    }

    private void saveCliente(){
        if(this.edtNama_Wisata.getText().toString().equals("")){this.edtNama_Wisata.setError(Constants.HINT_INPUT_NOME); return;}
        if(this.edtHarga.getText().toString().equals("")){this.edtHarga.setError(Constants.HINT_INPUT_IDADE); return;}
        if(this.edtJumlahTicket.getText().toString().equals("")){this.edtJumlahTicket.setError(Constants.HINT_INPUT_IDADE); return;}
        if(this.edtKategoriUmur.getText().toString().equals("")){this.edtKategoriUmur.setError(Constants.HINT_INPUT_IDADE); return;}
        if(this.edtTanggalTransaksi.getText().toString().equals("")){this.edtTanggalTransaksi.setError(Constants.HINT_INPUT_IDADE); return;}
        if(this.edtNama_Pemesan.getText().toString().equals("")){this.edtNama_Pemesan.setError(Constants.HINT_INPUT_EMAIL); return;}
        if(this.edtNIK.getText().toString().equals("")){this.edtNIK.setError(Constants.HINT_INPUT_EMAIL); return;}


        TicketModel ticketModel = new TicketModel();
        ticketModel.setNama_wisata(edtNama_Wisata.getText().toString());
        ticketModel.setHarga(Integer.parseInt(edtHarga.getText().toString()));
        ticketModel.setJumlah_ticket(edtJumlahTicket.getText().toString());
        ticketModel.setKategori_wisata(edtKategoriUmur.getText().toString());
        ticketModel.setTanggal(edtTanggalTransaksi.getText().toString());
        ticketModel.setNama_pemesan(edtNama_Pemesan.getText().toString());
        ticketModel.setNik(edtNIK.getText().toString());
        ticketModel.setComentar("");

        boolean status = this.dao.insert(ticketModel);

        if(status){
            configComponents();
            Toast.makeText(FormPembelian.this, "Pemesanan Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, BottomNav.class));
        }
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
}
