package com.coswick.travelinktrial.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.db_crud_ormite.ClienteDAO;
import com.coswick.travelinktrial.model.TicketModel;
import com.coswick.travelinktrial.util.Constants;


public class FragmentEditTickets extends Fragment {

    private TextView txtNamaWisata, txtHarga,txt_Jumlah, txt_Kategori,txt_Tanggal,txtNama_Pemesan,txt_Nik,txtSave, txtCancle, txtEdit;
    private EditText edt_comentarios;
    private ImageView imgReturn;
    private ClienteDAO dao = null;
    private int idClienteBD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_tickets, container, false);

        this.dao = new ClienteDAO(view.getContext());
        Bundle args = getArguments();
        initUi(view);

        if(args != null){
            idClienteBD = Integer.parseInt(args.getString(Constants.LB_ID));

            this.txtNamaWisata.setText(args.getString(Constants.TXT_NAMA_WISATA));
            this.txtHarga.setText(args.getString(Constants.TXT_HARGA));
            this.txt_Jumlah.setText(args.getString(Constants.TXT_JUMLAH));
            this.txt_Kategori.setText(args.getString(Constants.TXT_KATEGORI));
            this.txt_Tanggal.setText(args.getString(Constants.TXT_TANGGAL));
            this.txtNama_Pemesan.setText(args.getString(Constants.TXT_NAMA_PEMESAN));
            this.txtNama_Pemesan.setText(args.getString(Constants.TXT_NAMA_PEMESAN));
            this.txt_Nik.setText(args.getString(Constants.TXT_NIK));;

            this.edt_comentarios.setText(args.getString(Constants.TXT_KOMENTAR));
        }

        return view;
    }

    private void initUi(View view){
        this.txtNamaWisata = (TextView) view.findViewById(R.id.txt_nama_wisata);
        this.txtHarga = (TextView) view.findViewById(R.id.txt_harga);
        this.txt_Jumlah = (TextView) view.findViewById(R.id.txt_jumlah);
        this.txt_Kategori = (TextView) view.findViewById(R.id.txt_kategori_umur);
        this.txt_Tanggal = (TextView) view.findViewById(R.id.txt_tanggal);
        this.txtHarga = (TextView) view.findViewById(R.id.txt_harga);
        this.txtNama_Pemesan = (TextView) view.findViewById(R.id.txt_pemesan);
        this.txt_Nik = (TextView) view.findViewById(R.id.txt_nik);

        this.edt_comentarios = (EditText) view.findViewById(R.id.edt_comentar);
        this.txtSave = (TextView) view.findViewById(R.id.txt_save_comentar);
        this.txtCancle = (TextView) view.findViewById(R.id.txt_cancel);
        this.txtEdit = (TextView) view.findViewById(R.id.txt_edit);
        this.imgReturn = (ImageView) view.findViewById(R.id.img_return);

        callFragList();
        editComentario();
    }

    private void callFragList(){
        this.imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentMyTickets();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });
    }

    private void cursorEndText(){
        this.edt_comentarios.setSelection(edt_comentarios.getText().length());
    }

    private void editComentario(){
        this.txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_comentarios.setEnabled(true);
                edt_comentarios.requestFocus();
                cursorEndText();
                if(edt_comentarios.requestFocus()){
                    txtSave.setVisibility(View.VISIBLE);
                    txtCancle.setVisibility(View.VISIBLE);
                }
            }
        });
        funcBtnSave();
        funcBtnCancel();
    }

    private void funcBtnSave(){
        this.txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt_comentarios.getText().equals("")){
                    TicketModel cli = dao.getById(idClienteBD);
                    cli.setComentar(edt_comentarios.getText().toString());
                    boolean status = dao.update(cli);
                    if(status == true){
                        mechanicOfProcess();
                    }else{
                        Toast.makeText(getContext(), Constants.ERR_UPDATE, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void funcBtnCancel(){
        this.txtCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mechanicOfProcess();
            }
        });
    }

    private void mechanicOfProcess(){
        edt_comentarios.setEnabled(false);
        txtSave.setVisibility(View.GONE);
        txtCancle.setVisibility(View.GONE);
    }
}
