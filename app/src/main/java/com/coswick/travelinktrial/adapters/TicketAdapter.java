package com.coswick.travelinktrial.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.model.TicketModel;

import java.util.List;

public class TicketAdapter extends BaseAdapter {

    private Context context;
    private List<TicketModel> ticketModels;
    private LayoutInflater inflater = null;

    public TicketAdapter(Context context, List<TicketModel> ticketModels) {
        this.context = context;
        this.ticketModels = ticketModels;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ticketModels.size();
    }

    @Override
    public Object getItem(int position) {
        return ticketModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View rowView = convertView;
        rowView = inflater.inflate(R.layout.item_tickets, null);



        holder.txtnama_wisata = (TextView) rowView.findViewById(R.id.txt_nama_wisata);
        holder.txt_harga = (TextView) rowView.findViewById(R.id.txt_harga);
        holder.txt_jumlah = (TextView) rowView.findViewById(R.id.txt_jumlah);
        holder.txt_kategori = (TextView) rowView.findViewById(R.id.txt_kategori_umur);
        holder.txt_tanggal = (TextView) rowView.findViewById(R.id.txt_tanggal);
        holder.txt_pemesan = (TextView) rowView.findViewById(R.id.txt_pemesan);
        holder.txt_nik = (TextView) rowView.findViewById(R.id.txt_nik);





        holder.txtnama_wisata.setText(ticketModels.get(position).getNama_wisata());
        holder.txt_harga.setText( " Rp."+ ticketModels.get(position).getHarga());
        holder.txt_jumlah.setText(ticketModels.get(position).getJumlah_ticket());
        holder.txt_kategori.setText(ticketModels.get(position).getKategori_wisata());
        holder.txt_tanggal.setText(ticketModels.get(position).getTanggal());
        holder.txt_pemesan.setText(ticketModels.get(position).getNama_pemesan());
        holder.txt_nik.setText(ticketModels.get(position).getNik());

        return rowView;
    }

    public class ViewHolder{
        TextView txtnama_wisata;
        TextView txt_harga;
        TextView txt_jumlah;
        TextView txt_kategori;
        TextView txt_tanggal;
        TextView txt_pemesan;
        TextView txt_nik;
    }
}
