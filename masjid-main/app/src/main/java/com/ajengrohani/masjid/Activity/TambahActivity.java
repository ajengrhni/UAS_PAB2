 package com.ajengrohani.masjid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ajengrohani.masjid.API.APIRequestData;
import com.ajengrohani.masjid.API.RetroServer;
import com.ajengrohani.masjid.Model.ModelResponse;
import com.ajengrohani.masjid.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class TambahActivity extends AppCompatActivity {
     private EditText etNama, etFoto, etSejarah, etAlamat, etProvinsi;
     private Button btn_tambah;
     private String Nama, Foto, Sejarah, Alamat, Provinsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etFoto = findViewById(R.id.et_foto);
        etSejarah = findViewById(R.id.et_sejarah);
        etAlamat = findViewById(R.id.et_alamat);
        etProvinsi = findViewById(R.id.et_provinsi);
        btn_tambah = findViewById(R.id.btn_tambah);

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nama = etNama.getText().toString();
                Foto = etFoto.getText().toString();
                Sejarah = etSejarah.getText().toString();
                Alamat = etAlamat.getText().toString();
                Provinsi = etProvinsi.getText().toString();

                if (Nama.trim().isEmpty()){
                    etNama.setError("Nama masjid harus di isi");
                }else if (Foto.trim().isEmpty()){
                    etFoto.setError("Link foto masjid harus di isi");
                } else if (Sejarah.trim().isEmpty()) {
                    etSejarah.setError("Sejarah masjid harus di isi");
                } else if (Alamat.trim().isEmpty()) {
                    etAlamat.setError("Alamat masjid harus di isi");
                } else if (Provinsi.trim().isEmpty()) {
                    etProvinsi.setError("Provinsi harus di isi");
                } else {
                    tambahMasjid();
                }
            }
        });
    }
     private void tambahMasjid(){
         APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
         Call<ModelResponse> proses = API.ardCreate(Nama, Foto, Sejarah, Alamat, Provinsi);

         proses.enqueue(new Callback<ModelResponse>() {
             @Override
             public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                 String kode, pesan;
                 kode = response.body().getKode();
                 pesan = response.body().getPesan();
                 Toast.makeText(TambahActivity.this, "kode : " + kode + "Pesan" + pesan , Toast.LENGTH_SHORT).show();
                 finish();
             }

             @Override
             public void onFailure(Call<ModelResponse> call, Throwable t) {
                 Toast.makeText(TambahActivity.this, "Gagal simpan data!", Toast.LENGTH_SHORT).show();
             }
         });
     }


}