package com.coswick.travelinktrial.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.coswick.travelinktrial.activity.DetailWIsata;
import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.model.FavoriteModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<FavoriteModel> favoriteModels;
    Context context;

    public FavoriteAdapter(List<FavoriteModel> favoriteModels, Context context) {
        this.favoriteModels = favoriteModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemfavorite,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final FavoriteModel productList= favoriteModels.get(i);
        String pimg=productList.getImage();

        viewHolder.tv_nama.setText(favoriteModels.get(i).getName());
        Picasso.with(context).load(favoriteModels.get(i).getImage());
        viewHolder.tv_kat.setText(favoriteModels.get(i).getKategori());
        viewHolder.tv_desc.setText(favoriteModels.get(i).getDeskripsi());
        viewHolder.tv_harga.setText(favoriteModels.get(i).getHarga());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailWIsata.class);
                //passing data to the book activity
                intent.putExtra("title", favoriteModels.get(i).getName());
                intent.putExtra("img", favoriteModels.get(i).getImage());
                intent.putExtra("kat", favoriteModels.get(i).getKategori());
                intent.putExtra("desc", favoriteModels.get(i).getDeskripsi());
                intent.putExtra("harga", favoriteModels.get(i).getHarga());
                //start the activity
                context.startActivity(intent);

            }
        });
        Picasso.with(context).load(pimg).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return favoriteModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView tv_nama,tv_kat,tv_desc,tv_harga;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.fimg_pr);
            tv_nama=(TextView)itemView.findViewById(R.id.ftv_name);
            tv_kat=(TextView)itemView.findViewById(R.id.fkat);
            tv_desc=(TextView)itemView.findViewById(R.id.fdesc);
            tv_harga=(TextView)itemView.findViewById(R.id.fhrg);
            cardView = (CardView) itemView.findViewById(R.id.card_favorite);
        }
    }
}
