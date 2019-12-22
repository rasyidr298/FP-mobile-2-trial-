package com.coswick.travelinktrial.Fragment;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coswick.travelinktrial.adapters.ImageSlideAdapter;
import com.coswick.travelinktrial.adapters.PopulerAdapter;
import com.coswick.travelinktrial.model.PopulerModel;
import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.wisata_yogyakarta.WisataYogya;
import com.j256.ormlite.stmt.query.In;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentHome extends Fragment implements View.OnClickListener {


    ViewPager viewPager;
    PopulerAdapter populerAdapter;
    ImageSlideAdapter slideAdapter;
    List<PopulerModel> populerModels;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    CardView wisata_yg;


    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        wisata_yg = view.findViewById(R.id.wisata_yogyakarta);
        wisata_yg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WisataYogya.class);
                startActivity(intent);
            }
        });




        slideAdapter = new ImageSlideAdapter(getContext());
        viewPager = view.findViewById(R.id.viewPagerSlide);
        sliderDotspanel = view.findViewById(R.id.SliderDots);
        viewPager.setAdapter(slideAdapter);



        dotscount = slideAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        populerModels = new ArrayList<>();
        populerModels.add(new PopulerModel(R.drawable.brochure, "Candi Prambanan", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.sticker, "Candi Prambanan", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.poster, "Candi Prambanan", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface.","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.namecard, "Candi Prambanan", "Business cards are cards bearing business information about a company or individual.","Rp.40000","Yogyakarta"));

        populerAdapter = new PopulerAdapter(getActivity(), populerModels);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(populerAdapter);
        viewPager.setPadding(250, 0, 250, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.blue),
                getResources().getColor(R.color.blue_soft),
                getResources().getColor(R.color.blue),
                getResources().getColor(R.color.blue_soft)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (populerAdapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setPageMargin(20);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer()
        {
            @Override
            public void transformPage(View page, float position)
            {
                int pageWidth = viewPager.getMeasuredWidth() -
                        viewPager.getPaddingLeft() - viewPager.getPaddingRight();
                int pageHeight = viewPager.getHeight();
                int paddingLeft = viewPager.getPaddingLeft();
                float transformPos = (float) (page.getLeft() -
                        (viewPager.getScrollX() + paddingLeft)) / pageWidth;
                int max = pageHeight / 10;
                if (transformPos < -1)
                {
                    // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    //page.setAlpha(0.5f);// to make left transparent
                    page.setScaleY(0.7f);
                }
                else if (transformPos <= 1)
                {
                    // [-1,1]
                    page.setScaleY(1f);
                }
                else
                {
                    // (1,+Infinity]
                    // This page is way off-screen to the right.
                   // page.setAlpha(0.5f);// to make right transparent
                    page.setScaleY(0.7f);
                }
            }
        });

        Button aboutBtn = (Button) view.findViewById(R.id.button_profile);

        aboutBtn.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.button_profile:
                fragment = new FragmentProfile();
                replaceFragment(fragment);
                break;

        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
