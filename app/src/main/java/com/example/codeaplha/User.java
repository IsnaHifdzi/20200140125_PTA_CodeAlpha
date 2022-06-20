package com.example.codeaplha;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User {

    @SerializedName("id")
    private String id;

    @SerializedName("idgame")
    private String idgame;

    @SerializedName("jumplah")
    private String jumplah;

    @SerializedName("value")
    private String value;

    @SerializedName("message")
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdgame() {
        return idgame;
    }

    public void setIdgame(String namapemilik) {
        this.idgame = idgame;
    }

    public String getJumplah() {
        return jumplah;
    }

    public void setJumplah(String jumplah) {
        this.jumplah = jumplah;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
