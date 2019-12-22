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


/**
 * Created by Lucas Albuquerque on 09/03/2018.
 */

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

        holder.txtCliente = (TextView) rowView.findViewById(R.id.txt_nama_wisata);
        holder.txtEmail = (TextView) rowView.findViewById(R.id.txt_pemesan);

        String texto = ticketModels.get(position).getNome() + ", " + ticketModels.get(position).getIdade() + " Rp.";
        holder.txtCliente.setText(texto);

        holder.txtEmail.setText(ticketModels.get(position).getEmail());

        return rowView;
    }

    public class ViewHolder{
        TextView txtCliente;
        TextView txtEmail;
    }
}
