package com.coswick.travelinktrial.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.coswick.travelinktrial.DetailWIsata;
import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.model.PopulerModel;

import java.util.ArrayList;
import java.util.List;

public class WisataYogyaAdapter extends RecyclerView.Adapter<WisataYogyaAdapter.MyViewHolder>  implements Filterable {

    private List<PopulerModel> mData;
    private Context mContext;
    private List<PopulerModel> exampleListFull;


     static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_resep_title;
        ImageView img_resep_thumbnail;
        CardView cardView;


     MyViewHolder(View itemView){
            super(itemView);

            tv_resep_title = (TextView) itemView.findViewById(R.id.resep_title_id);
            img_resep_thumbnail = (ImageView) itemView.findViewById(R.id.resep_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }


    public WisataYogyaAdapter(Context mContext, List<PopulerModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
        exampleListFull = new ArrayList<>(mData);
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata_yogya,
                parent, false);
        return new MyViewHolder(v);
    }






    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_resep_title.setText(mData.get(position).getTitle());
        holder.img_resep_thumbnail.setImageResource(mData.get(position).getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailWIsata.class);

                //passing data to the book activity
                intent.putExtra("img",mData.get(position).getImage());
                intent.putExtra("title",mData.get(position).getTitle());
                intent.putExtra("kat",mData.get(position).getKategori());
                intent.putExtra("desc",mData.get(position).getDesc());
                intent.putExtra("harga",mData.get(position).getHarga());

                //start the activity
                mContext.startActivity(intent);

            }
        });

    }







    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PopulerModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PopulerModel item : exampleListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };




}
