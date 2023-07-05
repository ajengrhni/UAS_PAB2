package com.ajengrohani.masjid.API;

import com.ajengrohani.masjid.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrive.php")
    Call<ModelResponse> ardRetrive();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("nama") String nama,
            @Field("foto") String foto,
            @Field("sejarah") String sejarah,
            @Field("alamat") String alamat,
            @Field("provinsi") String provinsi
    );

    @FormUrlEncoded
    @POST("update.php")
    Call <ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("foto") String foto,
            @Field("sejarah") String sejarah,
            @Field("alamat") String alamat,
            @Field("provinsi") String provinsi
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call <ModelResponse> ardDelete(
            @Field("id") String id
    );

}
