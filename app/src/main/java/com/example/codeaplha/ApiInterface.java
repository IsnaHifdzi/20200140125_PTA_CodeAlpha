package com.example.codeaplha;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("user/masuk.php")
    public Call<User> masuk(
            @Field("usergame") String usergame,
            @Field("idgame") String idgame,
            @Field("jumlah") String jumlah,
            @Field("metode") String metode

    );

    @POST("user/read.php")
    public Call<List<User>> read();

    @FormUrlEncoded
    @POST("user/baru.php")
    public Call<User> baru(
            @Field("id") String id,
            @Field("usergame") String usergame,
            @Field("idgame") String idgame,
            @Field("jumlah") String jumlah,
            @Field("metode") String metode

    );
    @FormUrlEncoded
    @POST("user/hapus.php")
    public Call<User> hapus(
            @Field("id") String id
    );
}