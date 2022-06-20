package com.example.codeaplha;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apiinterface {
    @FormUrlEncoded
    @POST("user/masuk.php")
    public Call<User> masuk(
            @Field("idgame") String idgame,
            @Field("jumplah") String jumplah
    );

    @POST("user/membaca.php")
    public  Call<List<User>> read();

    @FormUrlEncoded
    @POST("user/baru.php")
    public  Call<User> baru(
            @Field("id") String id,
            @Field("idgame") String idgame,
            @Field("jumplah") String jumplah
    );

    @FormUrlEncoded
    @POST("user/hapos.php")
    public  Call<User>hapos(
            @Field("id") String id
    );
}
