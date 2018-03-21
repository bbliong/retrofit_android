package com.example.user.ketegori_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.ketegori_2.DetailActivity;
import com.example.user.ketegori_2.R;
import com.example.user.ketegori_2.model.Scholarship;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 17/3/2018.
 */

public class beasiswaAdapter extends RecyclerView.Adapter<beasiswaAdapter.MyViewHolder>{

    private Context mContext;
    private List<Scholarship> Scholarship_list;

    public beasiswaAdapter(Context mContext, List<Scholarship> Scholarship) {
        this.mContext = mContext;
        this.Scholarship_list = Scholarship;
    }

    @Override
    public beasiswaAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final beasiswaAdapter.MyViewHolder viewHolder, int i) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd MMMMM yyyy ";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(Scholarship_list.get(i).getMasaBerlaku());
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.judul_item.setText(Scholarship_list.get(i).getNamaBeasiswa());
        viewHolder.tahun_item.setText("Pendaftaran sampai " + str);
        viewHolder.konten_item.setText(Scholarship_list.get(i).getKonten());

        if(Scholarship_list.get(i).getAlamatGambar() == "" || Scholarship_list.get(i).getAlamatGambar() == null){
            Log.d("asa", (Scholarship_list.get(i).getAlamatGambar()));
        }else {
            Picasso.with(mContext)

                    .load(Scholarship_list.get(i).getAlamatGambar())
//                .memoryPolicy(MemoryPolicy.NO_CACHE )
//                .networkPolicy(NetworkPolicy.NO_CACHE)
                    .placeholder(R.drawable.load)

                    .into(viewHolder.thumbnail);
        }


//        viewHolder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(viewHolder.overflow);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return Scholarship_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView judul_item, tahun_item,konten_item;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            judul_item = (TextView) view.findViewById(R.id.judul_item);
            tahun_item = (TextView) view.findViewById(R.id.tahun_item);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            konten_item = (TextView) view.findViewById(R.id.konten_item);

            //on item click
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    String inputPattern = "yyyy-MM-dd";
                    String outputPattern = "dd MMMMM yyyy ";
                    SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
                    SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
                    Date date = null;
                    String str = null;

                    try {
                        date = inputFormat.parse(Scholarship_list.get(pos).getCreatedAt());
                        str = outputFormat.format(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (pos != RecyclerView.NO_POSITION){
                        Scholarship clickedDataItem = Scholarship_list.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("title", Scholarship_list.get(pos).getNamaBeasiswa());
                        intent.putExtra("date", "Dipublikasikan sejak " + str);
                        intent.putExtra("created", "Oleh " + Scholarship_list.get(pos).getNamaInstantsi());
                        intent.putExtra("konten", Scholarship_list.get(pos).getKonten());
                        intent.putExtra("thumbnail", Scholarship_list.get(pos).getAlamatGambar());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}
