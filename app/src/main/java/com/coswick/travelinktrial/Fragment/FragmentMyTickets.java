package com.coswick.travelinktrial.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.coswick.travelinktrial.adapters.TicketAdapter;
import com.coswick.travelinktrial.db_crud_ormite.ClienteDAO;
import com.coswick.travelinktrial.model.TicketModel;
import com.coswick.travelinktrial.util.Constants;

import java.util.List;

public class FragmentMyTickets extends Fragment {

    private TextView txtMsg;
    private ListView listView;
    private TicketAdapter adapter;
    private List<TicketModel> ticketModels;
    private ClienteDAO dao = null;
    private int idCli, pos;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_my_tickets,container,false);
        initUi(view);

        return view;
    }

    private void initUi(View view){
        this.txtMsg =  view.findViewById(R.id.txt_msg_empty);
        this.listView = view.findViewById(R.id.list_view_ticket);
        loadData(view);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idCliente = ticketModels.get(position).getId();
                String nama_wisata = ticketModels.get(position).getNama_wisata();
                int harga = ticketModels.get(position).getHarga();
                String jumlah = ticketModels.get(position).getJumlah_ticket();
                String kategori = ticketModels.get(position).getKategori_wisata();
                String tanggal = ticketModels.get(position).getTanggal();
                String nama_pemesan = ticketModels.get(position).getNama_pemesan();
                String nik = ticketModels.get(position).getNik();
                String comentar = ticketModels.get(position).getComentar();
                Fragment fragment = new FragmentEditTickets();
                fragment.setArguments(passParams(idCliente,  nama_wisata, harga, jumlah, kategori, tanggal,  nama_pemesan, nik, comentar));
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        });

        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                idCli = ticketModels.get(position).getId();
                pos = position;
                confirmation();
                return true;
            }
        });
    }

    private void loadData(View view){
        this.dao = new ClienteDAO(view.getContext());

        ticketModels = this.dao.getAll();

        if(ticketModels.size() > 0){
            this.adapter = new TicketAdapter(getContext(), ticketModels);
            this.listView.setAdapter(adapter);
        }else{
            this.txtMsg.setText(Constants.MSG_EMPTY_REGISTERS);
        }
    }

    private Bundle passParams(int id, String nama_wisata, int harga, String jumlah, String kategori, String tanggal, String nama_pemesan,String nik, String comentar){
        Bundle params = new Bundle();
        params.putString(Constants.LB_ID, String.valueOf(id));
        params.putString(Constants.TXT_NAMA_WISATA, nama_wisata);
        params.putString(Constants.TXT_HARGA, String.valueOf(harga));
        params.putString(Constants.TXT_JUMLAH, jumlah);
        params.putString(Constants.TXT_KATEGORI, kategori);
        params.putString(Constants.TXT_TANGGAL, tanggal);
        params.putString(Constants.TXT_NAMA_PEMESAN, nama_pemesan);
        params.putString(Constants.TXT_NIK, nik);
        params.putString(Constants.TXT_KOMENTAR, comentar);
        return params;
    }

    private void confirmation(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle(Constants.TXT_ALERT);
        alertDialogBuilder.setIcon(R.drawable.ic_profile);

        alertDialogBuilder.setMessage(Constants.TXT_ALERT_PILIHAN_KELUAR).setCancelable(false)
                .setPositiveButton(Constants.TXT_ALERT_KELUAR_YA,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                TicketModel cli = dao.getById(idCli);
                                dao.delete(cli);
                                ticketModels.remove(pos);
                                adapter.notifyDataSetChanged();
                            }
                        })
                .setNegativeButton(Constants.TXT_BUTUN_NO,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

