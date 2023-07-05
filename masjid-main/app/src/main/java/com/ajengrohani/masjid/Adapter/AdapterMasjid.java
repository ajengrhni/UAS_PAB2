package com.ajengrohani.masjid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajengrohani.masjid.API.APIRequestData;
import com.ajengrohani.masjid.API.RetroServer;
import com.ajengrohani.masjid.Activity.DetailActivity;
import com.ajengrohani.masjid.Activity.MainActivity;
import com.ajengrohani.masjid.Activity.UbahActivity;
import com.ajengrohani.masjid.Model.ModelMasjid;
import com.ajengrohani.masjid.Model.ModelResponse;
import com.ajengrohani.masjid.R;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterMasjid extends RecyclerView.Adapter<AdapterMasjid.VHMasjid>{
    private Context ctx;
    private List<ModelMasjid> listMasjid;

    public AdapterMasjid(Context ctx, List<ModelMasjid> listMasjid) {
        this.ctx = ctx;
        this.listMasjid = listMasjid;
    }

    @NonNull
    @Override
    public VHMasjid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_masjid, parent, false);
        return new VHMasjid(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHMasjid holder, int position) {
        ModelMasjid MM = listMasjid.get(position);
        holder.tvId.setText(MM.getId());
        holder.tvNama.setText(MM.getNama());
        holder.tvFoto.setText(MM.getFoto());
        holder.tvSejarah.setText(MM.getSejarah());
        holder.tvAlamat.setText(MM.getAlamat());
        holder.tvProvinsi.setText(MM.getProvinsi());
        Glide
                .with(ctx)
                .load(MM.getFoto())
                .into(holder.ivFoto);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xNama, xFoto,xSejarah, xAlamat, xProvinsi;
                xNama = MM.getNama();
                xFoto = MM.getFoto();
                xSejarah = MM.getSejarah();
                xAlamat = MM.getAlamat();
                xProvinsi = MM.getProvinsi();

                Intent kirim = new Intent(ctx, DetailActivity.class);
                kirim.putExtra("xNama",xNama);
                kirim.putExtra("xFoto",xFoto);
                kirim.putExtra("xSejarah",xSejarah);
                kirim.putExtra("xAlamat",xAlamat);
                kirim.putExtra("xProvinsi",xProvinsi);
                ctx.startActivity(kirim);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listMasjid.size();
    }

    public class VHMasjid extends RecyclerView.ViewHolder{
        private TextView tvId,tvNama, tvFoto,tvSejarah, tvAlamat, tvProvinsi;
        private Button btnHapus, btnUbah, btnDetail;
        private ImageView ivFoto;
        public VHMasjid(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvFoto = itemView.findViewById(R.id.tv_foto);
            tvSejarah = itemView.findViewById(R.id.tv_sejarah);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvProvinsi = itemView.findViewById(R.id.tv_provinsi);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
            btnUbah = itemView.findViewById(R.id.btn_ubah);
            btnDetail = itemView.findViewById(R.id.btn_detail);

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteMasjid(tvId.getText().toString());

                }
            });

            btnUbah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent kirim = new Intent(ctx, UbahActivity.class);
                    kirim.putExtra("xId", tvId.getText().toString());
                    kirim.putExtra("xNama", tvNama.getText().toString());
                    kirim.putExtra("xFoto", tvFoto.getText().toString());
                    kirim.putExtra("xSejarah", tvSejarah.getText().toString());
                    kirim.putExtra("xAlamat", tvAlamat.getText().toString());
                    kirim.putExtra("xProvinsi", tvProvinsi.getText().toString());
                    ctx.startActivity(kirim);
                }
            });

            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


        void deleteMasjid(String id){
            APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = API.ardDelete(id);
            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode:"+kode+"Pesan : "+ pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retriveMasjid();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
