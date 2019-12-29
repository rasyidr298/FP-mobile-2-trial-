package com.coswick.travelinktrial.Fragment;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.coswick.travelinktrial.activity.Notifikasi;
import com.coswick.travelinktrial.activity.Promo;
import com.coswick.travelinktrial.adapter_image_slide.ImageSlideHomeAdapter;
import com.coswick.travelinktrial.adapters.PopulerAdapter;
import com.coswick.travelinktrial.adapater_wisata.WisataTerahirAdapter;
import com.coswick.travelinktrial.db_favorite_room.FavoriteDatabase;
import com.coswick.travelinktrial.model.PopulerModel;
import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.model.WisataTerahirModel;
import com.coswick.travelinktrial.wisata.WisataAceh;
import com.coswick.travelinktrial.wisata.WisataBali;
import com.coswick.travelinktrial.wisata.WisataKlaten;
import com.coswick.travelinktrial.wisata.WisataMedan;
import com.coswick.travelinktrial.wisata.WisataSemua;
import com.coswick.travelinktrial.wisata.WisataSurabaya;
import com.coswick.travelinktrial.wisata.WisataWonogiri;
import com.coswick.travelinktrial.wisata.WisataYogya;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements View.OnClickListener {


    //Deklarasi variable untuk Wisata Terahir Diihat
    private static final String HI = "https://rasyidridla.000webhostapp.com/TRAVELINK/datawisata.json";
    private List<WisataTerahirModel> wisataTerahirModels;
    private RecyclerView recyclerView;
    private  WisataTerahirAdapter adapter;
    private ShimmerFrameLayout mShimmerViewContainer;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    public static FavoriteDatabase favoriteDatabase_terahir;

    //Deklarasi variable untuk SlideZoomViewPager
    private PopulerAdapter populerAdapter;
    private List<PopulerModel> populerModels;
    private Integer[] colors = null;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    //Deklarasi variable untuk Image SlideViewpager
    LinearLayout sliderDotspanel;
    private ViewPager viewPager;
    private ImageSlideHomeAdapter slideAdapter;
    private int dotscount;
    private ImageView[] dots;

    //Dekalrasi variable button macam macam wisata
    private CardView wisata_yogya;
    private  CardView semua_wisata;
    private CardView wisata_aceh;
    private CardView wisata_bali;
    private CardView wisata_klaten;
    private CardView wisata_medan;
    private CardView wisata_surabaya;
    private CardView wisata_wonogiri;

    //DeklARASI bUTON Notifikasi
    private Button notifikasi;


    //DeklARASI bUTON promo
    private Button promo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //Deklarasi Layout untuk Wisata Terahir Diihat
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview_id_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        wisataTerahirModels =new ArrayList<>();
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container_home);
        favoriteDatabase_terahir = Room.databaseBuilder(getActivity(), FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();
        getData();


        // button klik macam macam wisata
        wisata_yogya = view.findViewById(R.id.wisata_yogyakarta);
        wisata_yogya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WisataYogya.class);
                startActivity(intent);
            }
        });
        semua_wisata = view.findViewById(R.id.semua_wisata);
        semua_wisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WisataSemua.class);
                startActivity(intent);
            }
        });
        wisata_aceh = view.findViewById(R.id.wisata_aceh);
        wisata_aceh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WisataAceh.class);
                startActivity(intent);
            }
        });
        wisata_bali = view.findViewById(R.id.wisata_bali);
        wisata_bali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WisataBali.class);
                startActivity(intent);
            }
        });
        wisata_klaten = view.findViewById(R.id.wisata_klaten);
        wisata_klaten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WisataKlaten.class);
                startActivity(intent);
            }
        });
        wisata_medan = view.findViewById(R.id.wisata_medan);
        wisata_medan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WisataMedan.class);
                startActivity(intent);
            }
        });
        wisata_surabaya = view.findViewById(R.id.wisata_surabaya);
        wisata_surabaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WisataSurabaya.class);
                startActivity(intent);
            }
        });
        wisata_wonogiri = view.findViewById(R.id.wisata_wonogiri);
        wisata_wonogiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WisataWonogiri.class);
                startActivity(intent);
            }
        });


        //Button Klik notifikasi
        notifikasi = view.findViewById(R.id.btn_notif);
        notifikasi = view.findViewById(R.id.btn_notiff);
        notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Notifikasi.class);
                startActivity(intent);
            }
        });


        //Button Klik promo
        promo = view.findViewById(R.id.btn_promo);
        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Promo.class);
                startActivity(intent);
            }
        });



        //Deklarasi variable untuk SlideZoomViewPager dan Image Slide
        slideAdapter = new ImageSlideHomeAdapter(getContext());
        viewPager = view.findViewById(R.id.viewPagerSlide);
        sliderDotspanel = view.findViewById(R.id.SliderDots);
        viewPager.setAdapter(slideAdapter);
        dotscount = slideAdapter.getCount();
        dots = new ImageView[dotscount];


        //Proses Image Slide dan Indikator
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


        //Data untuk SlideZoomViewPager
        populerModels = new ArrayList<>();
        populerModels.add(new PopulerModel(R.drawable.brochure, "Candi Prambanan", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.sticker, "Candi Prambanan", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.poster, "Candi Prambanan", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface.","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.namecard, "Candi Prambanan", "Business cards are cards bearing business information about a company or individual.","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.brochure, "Candi Prambanan", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.sticker, "Candi Prambanan", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.poster, "Candi Prambanan", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface.","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.namecard, "Candi Prambanan", "Business cards are cards bearing business information about a company or individual.","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.brochure, "Candi Prambanan", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.sticker, "Candi Prambanan", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.poster, "Candi Prambanan", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface.","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.namecard, "Candi Prambanan", "Business cards are cards bearing business information about a company or individual.","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.brochure, "Candi Prambanan", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.sticker, "Candi Prambanan", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.poster, "Candi Prambanan", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface.","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.namecard, "Candi Prambanan", "Business cards are cards bearing business information about a company or individual.","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.brochure, "Candi Prambanan", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.sticker, "Candi Prambanan", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.poster, "Candi Prambanan", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface.","Rp.40000","Yogyakarta"));
        populerModels.add(new PopulerModel(R.drawable.namecard, "Candi Prambanan", "Business cards are cards bearing business information about a company or individual.","Rp.40000","Yogyakarta"));
        populerAdapter = new PopulerAdapter(getActivity(), populerModels);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(populerAdapter);
        viewPager.setPadding(280, 0, 280, 0);


        //Merubah Warna setiap SlideZoomViewPager
        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color2)
        };

        //Merubah Warna SlideZoomViewPager
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


        //Proses SlideZoomViewPager
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

        //Klick ke Fragment Profile
        Button aboutBtn = (Button) view.findViewById(R.id.button_profile);
        aboutBtn.setOnClickListener(this);

        return view;
    }


    //Proses Pengambilan data Json
    private void getData() {
        request=new JsonArrayRequest(HI, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject ob=response.getJSONObject(i);
                        WisataTerahirModel pr=new WisataTerahirModel(ob.getInt("id"),
                                ob.getString("nama_wisata"),
                                ob.getString("kat_wisata"),
                                ob.getString("desc_wisata"),
                                ob.getString("hrg_wisata"),
                                ob.getString("img_wisata"));
                        wisataTerahirModels.add(pr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupData(wisataTerahirModels);


                // stop animating Shimmer and hide the layout
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }


    //Proses SHimmerLayout
    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmer();
    }

    //Proses SHimmerLayout
    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmer();
        super.onPause();
    }

    //setDaftar terahir dilihat
    private void setupData(List<WisataTerahirModel> wisataYogyaModels) {
        adapter=new WisataTerahirAdapter(wisataYogyaModels,getContext());
        recyclerView.setAdapter(adapter);
    }



    //proses Klick ke Fragment Profile
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
    //proses Klick ke Fragment Profile
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
