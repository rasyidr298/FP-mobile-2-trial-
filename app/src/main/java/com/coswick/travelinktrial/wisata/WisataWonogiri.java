package com.coswick.travelinktrial.wisata;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.adapater_wisata.WisataWonogiriAdapter;
import com.coswick.travelinktrial.adapter_image_slide.ImageSlideSemuaAdapter;
import com.coswick.travelinktrial.adapter_image_slide.ImageSlideSurabayaAdapter;
import com.coswick.travelinktrial.adapter_image_slide.ImageSlideWonogiriAdapter;
import com.coswick.travelinktrial.db_favorite_room.FavoriteDatabase;
import com.coswick.travelinktrial.model.WisataWonogiriModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WisataWonogiri extends AppCompatActivity {

    //Deklarasi variable untuk Image SlideViewpager
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private ImageSlideWonogiriAdapter slideAdapter;
    private ViewPager viewPager;

    //Deklarasi Tipe Data
    private static final String HI = "https://rasyidridla.000webhostapp.com/TRAVELINK/datawisata.json";
    private List<WisataWonogiriModel> wisataWonogiriModels;
    private RecyclerView recyclerView;
    WisataWonogiriAdapter adapter;
    private ShimmerFrameLayout mShimmerViewContainer;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    public static FavoriteDatabase favoriteDatabase_wonogiri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_wonogiri);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_id_wonogiri);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wisataWonogiriModels =new ArrayList<>();
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container_wonogiri);
        favoriteDatabase_wonogiri= Room.databaseBuilder(getApplicationContext(), FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();
        getData();

        //Deklarasi variable untuk SlideZoomViewPager dan Image Slide
        slideAdapter = new ImageSlideWonogiriAdapter(WisataWonogiri.this);
        viewPager = findViewById(R.id.viewPagerSlide_wonogiri);
        sliderDotspanel = findViewById(R.id.SliderDots_wonogiri);
        viewPager.setAdapter(slideAdapter);
        dotscount = slideAdapter.getCount();
        dots = new ImageView[dotscount];

        //Proses Image Slide dan Indikator
        for(int i = 0; i < dotscount; i++){
            dots[i] = new ImageView(WisataWonogiri.this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(WisataWonogiri.this, R.drawable.ic_non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(WisataWonogiri.this, R.drawable.ic_active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(WisataWonogiri.this, R.drawable.ic_non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(WisataWonogiri.this, R.drawable.ic_active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });



        //Collapsing Toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_wonogiri);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Proses pengambilan data json
    private void getData() {
        request=new JsonArrayRequest(HI, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject ob=response.getJSONObject(i);
                        WisataWonogiriModel pr=new WisataWonogiriModel(ob.getInt("id"),
                                ob.getString("nama_wisata"),
                                ob.getString("kat_wisata"),
                                ob.getString("desc_wisata"),
                                ob.getString("hrg_wisata"),
                                ob.getString("img_wisata"));
                        wisataWonogiriModels.add(pr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupData(wisataWonogiriModels);


                // stop animating Shimmer and hide the layout
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    //Proses ShimmerLayout
    @Override
    protected void onPostResume() {
        super.onPostResume();
        mShimmerViewContainer.startShimmer();
    }

    //Proses ShimmerLayout
    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmer();
        super.onPause();
    }

    //Set Data
    private void setupData(List<WisataWonogiriModel> wisataYogyaModels) {
        adapter=new WisataWonogiriAdapter(wisataYogyaModels,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }


    //Proses Search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    //Proses Back
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
