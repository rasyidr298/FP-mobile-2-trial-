package com.coswick.travelinktrial.wisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.coswick.travelinktrial.R;
import com.coswick.travelinktrial.adapters.WisataBaliAdapter;
import com.coswick.travelinktrial.db_favorite_room.FavoriteDatabase;
import com.coswick.travelinktrial.model.WisataBaliModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WisataBali extends AppCompatActivity {

    //Deklarasi Tipe Data
    private static final String HI = "https://rasyidridla.000webhostapp.com/TRAVELINK/datawisata.json";
    private List<WisataBaliModel> wisataBaliModels;
    private RecyclerView recyclerView;
    WisataBaliAdapter adapter;
    private ShimmerFrameLayout mShimmerViewContainer;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    public static FavoriteDatabase favoriteDatabase_bali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_bali);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_id_bali);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wisataBaliModels =new ArrayList<>();
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container_bali);
        favoriteDatabase_bali= Room.databaseBuilder(getApplicationContext(), FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();
        getData();
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
                        WisataBaliModel pr=new WisataBaliModel(ob.getInt("id"),
                                ob.getString("nama_wisata"),
                                ob.getString("kat_wisata"),
                                ob.getString("desc_wisata"),
                                ob.getString("hrg_wisata"),
                                ob.getString("img_wisata"));
                        wisataBaliModels.add(pr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupData(wisataBaliModels);


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
    private void setupData(List<WisataBaliModel> wisataYogyaModels) {
        adapter=new WisataBaliAdapter(wisataYogyaModels,getApplicationContext());
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

}
