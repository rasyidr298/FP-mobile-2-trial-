package com.coswick.travelinktrial.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.coswick.travelinktrial.model.ModelPopuler;
import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.WisataPopuler;

import java.util.List;

public class AdapterPopuler extends PagerAdapter{

    private List<ModelPopuler> modelPopulers;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterPopuler(Context context, List<ModelPopuler> modelPopulers) {
        this.modelPopulers = modelPopulers;
        this.context = context;
    }


    @Override
    public int getCount() {
        return modelPopulers.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_wisata_populer, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);

        imageView.setImageResource(modelPopulers.get(position).getImage());
        title.setText(modelPopulers.get(position).getTitle());
        desc.setText(modelPopulers.get(position).getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WisataPopuler.class);
                intent.putExtra("param", modelPopulers.get(position).getTitle());
                context.startActivity(intent);
                // finish();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
