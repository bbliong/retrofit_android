package com.example.user.ketegori_2;

/**
 * Created by user on 19/3/2018.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;




public class DetailActivity extends AppCompatActivity {

    TextView BuatSingle, JudulSingle, TanggalSingle;
    ImageView ThumbSingle;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ThumbSingle = (ImageView) findViewById(R.id.thumb_single);
        BuatSingle = (TextView)  findViewById(R.id.buat_single);
        JudulSingle = (TextView) findViewById(R.id.judul_single);
        TanggalSingle = (TextView) findViewById(R.id.tanggal_single);

        String titleText = getIntent().getExtras().getString("title");
        String createText = getIntent().getExtras().getString("created");
        String dateText = getIntent().getExtras().getString("date");
        String thumbnail = getIntent().getExtras().getString("thumbnail");

        JudulSingle.setText(titleText);
        TanggalSingle.setText(dateText);
        BuatSingle.setText(createText);
        Glide.with(this)
                .load(thumbnail)
                .placeholder(R.drawable.load)
                .into(ThumbSingle);

        getSupportActionBar().setTitle("Details Activity");
    }
}
