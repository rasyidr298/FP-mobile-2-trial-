package com.coswick.travelinktrial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.coswick.travelinktrial.dao.ClienteDAO;
import com.coswick.travelinktrial.model.ClientModel;
import com.coswick.travelinktrial.util.Constants;


public class WisataPopuler extends AppCompatActivity implements View.OnClickListener{


    private EditText edtNome, edtIdade, edtEmail;
    private Button btnSave;
    private ClienteDAO dao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_populer);

        TextView textView = findViewById(R.id.textView);
        textView.setText(getIntent().getStringExtra("param"));

        dao = new ClienteDAO(this);
        initUi();
    }



    private void initUi(){
        this.edtNome = (EditText) findViewById(R.id.edt_nome);
        this.edtIdade = (EditText) findViewById(R.id.edt_idade);
        this.edtEmail = (EditText) findViewById(R.id.edt_email);
        this.btnSave = (Button) findViewById(R.id.btn_save);

        configComponents();
    }

    private void configComponents(){
        this.edtNome.requestFocus();
        this.edtNome.setText("");
        this.edtNome.setSelection(edtNome.getText().length());
        this.edtIdade.setText("");
        this.edtIdade.setSelection(edtIdade.getText().length());
        this.edtEmail.setText("");
        this.edtEmail.setSelection(edtEmail.getText().length());

        this.btnSave.setOnClickListener(WisataPopuler.this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_save){
            saveCliente();
        }
    }




    private void saveCliente(){

        if(this.edtNome.getText().toString().equals("")){this.edtNome.setError(Constants.HINT_INPUT_NOME); return;}
        if(this.edtIdade.getText().toString().equals("")){this.edtIdade.setError(Constants.HINT_INPUT_IDADE); return;}
        if(this.edtEmail.getText().toString().equals("")){this.edtEmail.setError(Constants.HINT_INPUT_EMAIL); return;}

        ClientModel clientModel = new ClientModel();
        clientModel.setNome(edtNome.getText().toString());
        clientModel.setIdade(Integer.parseInt(edtIdade.getText().toString()));
        clientModel.setEmail(edtEmail.getText().toString());
        clientModel.setComentario("");

        boolean status = this.dao.insert(clientModel);

        if(status){
            configComponents();
            Toast.makeText(WisataPopuler.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
        }
    }
}
