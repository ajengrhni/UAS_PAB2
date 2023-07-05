package com.ajengrohani.masjid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajengrohani.masjid.R;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvSejarah, tvAlamat, tvProvinsi;
    private ImageView ivFoto;
    String yNama, yFoto, ySejarah, yAlamat, yProvinsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tv_nama);
        tvSejarah = findViewById(R.id.tv_sejarah);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvProvinsi = findViewById(R.id.tv_provinsi);
        ivFoto = findViewById(R.id.iv_foto);

        Intent tangkap = getIntent();
        yNama = tangkap.getStringExtra("xNama");
        yFoto = tangkap.getStringExtra("xFoto");
        ySejarah = tangkap.getStringExtra("xSejarah");
        yAlamat = tangkap.getStringExtra("xAlamat");
        yProvinsi = tangkap.getStringExtra("xProvinsi");

        tvNama.setText(yNama);
        tvSejarah.setText(ySejarah);
        tvAlamat.setText(yAlamat);
        tvProvinsi.setText(yProvinsi);
        Glide
                .with(DetailActivity.this)
                .load(yFoto)
                .into(ivFoto);
    }
}