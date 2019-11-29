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
import com.coswick.travelinktrial.dao.ClienteDAO;
import com.coswick.travelinktrial.model.ClientModel;
import com.coswick.travelinktrial.util.Constants;


public class EditTicketsFragment extends Fragment {

    private TextView txtNome, txtIdade, txtEmail, txtSalvar, txtCancelar, txtEditar;
    private EditText edt_comentarios;
    private ImageView imgReturn;
    private ClienteDAO dao = null;
    private int idClienteBD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_tickets, container, false);

        this.dao = new ClienteDAO(view.getContext());
        Bundle args = getArguments();
        initUi(view);

        if(args != null){
            idClienteBD = Integer.parseInt(args.getString(Constants.LB_ID));
            this.txtNome.setText(args.getString(Constants.LB_NOME));
            this.txtIdade.setText(args.getString(Constants.LB_IDADE));
            this.txtEmail.setText(args.getString(Constants.LB_EMAIL));
            this.edt_comentarios.setText(args.getString(Constants.LB_COMENTARIO));
        }

        return view;
    }

    private void initUi(View view){
        this.txtNome = (TextView) view.findViewById(R.id.txt_nome);
        this.txtIdade = (TextView) view.findViewById(R.id.txt_idade);
        this.txtEmail = (TextView) view.findViewById(R.id.txt_email);
        this.edt_comentarios = (EditText) view.findViewById(R.id.edt_comentarios);
        this.txtSalvar = (TextView) view.findViewById(R.id.txt_save_comentario);
        this.txtCancelar = (TextView) view.findViewById(R.id.txt_cancel);
        this.txtEditar = (TextView) view.findViewById(R.id.txt_editar);
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
        this.txtEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_comentarios.setEnabled(true);
                edt_comentarios.requestFocus();
                cursorEndText();
                if(edt_comentarios.requestFocus()){
                    txtSalvar.setVisibility(View.VISIBLE);
                    txtCancelar.setVisibility(View.VISIBLE);
                }
            }
        });
        funcBtnSave();
        funcBtnCancel();
    }

    private void funcBtnSave(){
        this.txtSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt_comentarios.getText().equals("")){
                    ClientModel cli = dao.getById(idClienteBD);
                    cli.setComentario(edt_comentarios.getText().toString());
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
        this.txtCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mechanicOfProcess();
            }
        });
    }

    private void mechanicOfProcess(){
        edt_comentarios.setEnabled(false);
        txtSalvar.setVisibility(View.GONE);
        txtCancelar.setVisibility(View.GONE);
    }
}
