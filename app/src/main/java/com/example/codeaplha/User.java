package com.example.codeaplha;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User {

    @SerializedName("id")
    private String id;

    @SerializedName("usergame")
    private String usergame;

    @SerializedName("idgame")
    private String idgame;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("metode")
    private String metode;

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

    public String getUsergame() {
        return usergame;
    }

    public void setUsergame(String usergame) {
        this.usergame = usergame;
    }

    public String getIdgame() {
        return idgame;
    }

    public void setIdgame(String idgame) {
        this.idgame = idgame;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {
        this.metode = metode;
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
