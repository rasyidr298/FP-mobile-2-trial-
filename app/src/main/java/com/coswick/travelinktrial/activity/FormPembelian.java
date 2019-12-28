package com.coswick.travelinktrial.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.db_crud_ormite.ClienteDAO;
import com.coswick.travelinktrial.model.TicketModel;
import com.coswick.travelinktrial.util.Constants;

public class FormPembelian extends AppCompatActivity implements View.OnClickListener{


    private EditText edtNama_Wisata, edtHarga, edtNama_Pemesan;
    private Button btnCheckout;
    private ClienteDAO dao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pembelian);
        dao = new ClienteDAO(this);
        initUi();
    }



    private void initUi(){
        this.edtNama_Wisata = findViewById(R.id.edt_nama_wisata);
        this.edtHarga = findViewById(R.id.edt_harga);
        this.edtNama_Pemesan = findViewById(R.id.edt_nama_pemesan);
        this.btnCheckout = findViewById(R.id.btn_checkout);

        configComponents();
    }

    private void configComponents(){
        this.edtNama_Wisata.requestFocus();
        this.edtNama_Wisata.setText("");
        this.edtNama_Wisata.setSelection(edtNama_Wisata.getText().length());
        this.edtHarga.setText("");
        this.edtHarga.setSelection(edtHarga.getText().length());
        this.edtNama_Pemesan.setText("");
        this.edtNama_Pemesan.setSelection(edtNama_Pemesan.getText().length());
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
        if(this.edtNama_Pemesan.getText().toString().equals("")){this.edtNama_Pemesan.setError(Constants.HINT_INPUT_EMAIL); return;}

        TicketModel ticketModel = new TicketModel();
        ticketModel.setNome(edtNama_Wisata.getText().toString());
        ticketModel.setIdade(Integer.parseInt(edtHarga.getText().toString()));
        ticketModel.setEmail(edtNama_Pemesan.getText().toString());
        ticketModel.setComentario("");

        boolean status = this.dao.insert(ticketModel);

        if(status){
            configComponents();
            Toast.makeText(FormPembelian.this, "Pemesanan Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, BottomNav.class));
        }
    }
}
