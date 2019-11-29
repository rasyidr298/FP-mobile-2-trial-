package com.coswick.travelinktrial.Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.adapters.UserAdapter;
import com.coswick.travelinktrial.dao.ClienteDAO;
import com.coswick.travelinktrial.model.ClientModel;
import com.coswick.travelinktrial.util.Constants;

import java.util.List;

public class FragmentMyTickets extends Fragment {

    private TextView txtMsg;
    private ListView listaClientes;
    private UserAdapter adapter;
    private List<ClientModel> clientModels;
    private ClienteDAO dao = null;
    private int idCli, pos;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_my_tickets,container,false);

        initUi(view);
        return view;
    } private void initUi(View view){
        this.txtMsg = (TextView) view.findViewById(R.id.txt_msg_empty);
        this.listaClientes = (ListView) view.findViewById(R.id.lista_clientes);

        loadData(view);

        this.listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int idCliente = clientModels.get(position).getId();
                String nome = clientModels.get(position).getNome();
                int idade = clientModels.get(position).getIdade();
                String email = clientModels.get(position).getEmail();
                String comentario = clientModels.get(position).getComentario();

                Fragment fragment = new EditTicketsFragment();
                fragment.setArguments(passParams(idCliente,  nome, idade, email, comentario));
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });

        this.listaClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                idCli = clientModels.get(position).getId();
                pos = position;
                confirmation();
                return true;
            }
        });
    }

    private void loadData(View view){
        this.dao = new ClienteDAO(view.getContext());
        clientModels = this.dao.getAll();

        if(clientModels.size() > 0){
            this.adapter = new UserAdapter(getContext(), clientModels);
            this.listaClientes.setAdapter(adapter);
        }else{
            this.txtMsg.setText(Constants.MSG_EMPTY_REGISTERS);
        }
    }

    private Bundle passParams(int id, String nome, int idade, String email, String comentario){
        Bundle params = new Bundle();
        params.putString(Constants.LB_ID, String.valueOf(id));
        params.putString(Constants.LB_NOME, nome);
        params.putString(Constants.LB_IDADE, String.valueOf(idade));
        params.putString(Constants.LB_EMAIL, email);
        params.putString(Constants.LB_COMENTARIO, comentario);
        return params;
    }

    private void confirmation(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle(Constants.TXT_ALERT_TITLE);
        alertDialogBuilder.setIcon(R.drawable.ic_profile);

        alertDialogBuilder.setMessage(Constants.TXT_ALERT_MSG_DEL).setCancelable(false)
                .setPositiveButton(Constants.TXT_ALERT_DEL_POSITIVE_BUTTON,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ClientModel cli = dao.getById(idCli);
                                dao.delete(cli);
                                clientModels.remove(pos);
                                adapter.notifyDataSetChanged();
                            }
                        })
                .setNegativeButton(Constants.TXT_ALERT_NEGATIVE_BUTTON,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

