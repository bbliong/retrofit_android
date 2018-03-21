package com.example.user.ketegori_2;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.ketegori_2.adapter.beasiswaAdapter;
import com.example.user.ketegori_2.api.Client;
import com.example.user.ketegori_2.api.Service;
import com.example.user.ketegori_2.model.Scholarship;
import com.example.user.ketegori_2.model.ScholarshipsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private beasiswaAdapter adapter;
    private List<Scholarship> beasiswaList;
    private Scholarship beasiswa;
    private TextView judul;
    private TextView deskripsi;
   private  List<ScholarshipsResponse> items;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

     private  void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Mengambil Data");
        pd.setCancelable(true);
        pd.dismiss();
        pd.show();
//
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        beasiswaList = new ArrayList<>();
        adapter = new beasiswaAdapter(this, beasiswaList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        loadJson();
    }

    private void loadJson(){

        try{
            Client client = new Client();
            Service apiService =  Client.getClient().create(Service.class);
            Call<List<ScholarshipsResponse>> call = apiService.getData("komputer");

            call.enqueue(new Callback<List<ScholarshipsResponse>>() {
                @Override
                public void onResponse(Call<List<ScholarshipsResponse>> call, Response<List<ScholarshipsResponse>> response) {
                    items = response.body();
                    laodMenu();
//                    Toast.makeText(MainActivity.this, items.get(0).getScholarships().get(0).getNamaBeasiswa(), Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(new beasiswaAdapter(getApplicationContext(), items.get(0).getScholarships()));
                    recyclerView.smoothScrollToPosition(0);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<List<ScholarshipsResponse>> call, Throwable t) {
                    Log.d("error", t.getMessage());
                    Toast.makeText(MainActivity.this, "error fetching data", Toast.LENGTH_SHORT).show();
                    pd.hide();
                }
            });
        }catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void laodMenu(){
        judul = (TextView) findViewById(R.id.judul_menu);
        deskripsi = (TextView) findViewById(R.id.deskripsi_menu);
        judul.setText(items.get(0).getJudul());
        deskripsi.setText(items.get(0).getKonten());
        String data = items.get(0).getJudul();
        try {
        switch(data){
            case "komputer" :
                Glide.with(this).load(R.drawable.computer_528px).into((ImageView) findViewById(R.id.gambar_menu));
                break;
            case "luar negeri" :
                Glide.with(this).load(R.drawable.statue_of_liberty_528px).into((ImageView) findViewById(R.id.gambar_menu));
            break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
