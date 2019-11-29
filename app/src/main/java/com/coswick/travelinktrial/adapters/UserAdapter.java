package com.coswick.travelinktrial.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.model.ClientModel;

import java.util.List;


/**
 * Created by Lucas Albuquerque on 09/03/2018.
 */

public class UserAdapter extends BaseAdapter {

    private Context context;
    private List<ClientModel> clientModels;
    private LayoutInflater inflater = null;

    public UserAdapter(Context context, List<ClientModel> clientModels) {
        this.context = context;
        this.clientModels = clientModels;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return clientModels.size();
    }

    @Override
    public Object getItem(int position) {
        return clientModels.get(position);
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

        holder.txtCliente = (TextView) rowView.findViewById(R.id.txt_cliente);
        holder.txtEmail = (TextView) rowView.findViewById(R.id.txt_email);

        String texto = clientModels.get(position).getNome() + ", " + clientModels.get(position).getIdade() + " anos.";
        holder.txtCliente.setText(texto);

        holder.txtEmail.setText(clientModels.get(position).getEmail());

        return rowView;
    }

    public class ViewHolder{
        TextView txtCliente;
        TextView txtEmail;
    }
}
