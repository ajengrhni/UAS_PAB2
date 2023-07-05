package com.ajengrohani.masjid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ajengrohani.masjid.API.APIRequestData;
import com.ajengrohani.masjid.API.RetroServer;
import com.ajengrohani.masjid.Adapter.AdapterMasjid;
import com.ajengrohani.masjid.Model.ModelMasjid;
import com.ajengrohani.masjid.Model.ModelResponse;
import com.ajengrohani.masjid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMasjid;
    private FloatingActionButton fabTambah;
    private ProgressBar pbMasjid;
    private RecyclerView.Adapter adMasjid;
    private RecyclerView.LayoutManager lmMasjid;
    private List<ModelMasjid> listMasjid = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMasjid = findViewById(R.id.rv_masjid);
        fabTambah = findViewById(R.id.fab_tambah);
        pbMasjid = findViewById(R.id.pb_masjid);

        lmMasjid = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvMasjid.setLayoutManager(lmMasjid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retriveMasjid();
    }

    public void retriveMasjid(){
        pbMasjid.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrive();
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listMasjid = response.body().getData();
                adMasjid = new AdapterMasjid(MainActivity.this, listMasjid);
                rvMasjid.setAdapter(adMasjid);
                adMasjid.notifyDataSetChanged();
                pbMasjid.setVisibility(View.GONE);

                fabTambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, TambahActivity.class));
                    }
                });
            }


            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                pbMasjid.setVisibility(View.GONE);
            }

        });
    }
}