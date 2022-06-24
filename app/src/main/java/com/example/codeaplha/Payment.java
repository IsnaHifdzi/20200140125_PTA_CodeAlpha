package com.example.codeaplha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment extends AppCompatActivity {

    EditText input_nama, input_id, input_jumlah, input_metode;
    Button btn_beli, btn_delete;
    ApiInterface apiInterface;

    String yid,usergame,idgame,jumlah,metode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        yid = intent.getStringExtra("id");
        usergame = intent.getStringExtra("usergame");
        idgame = intent.getStringExtra("idgame");
        jumlah = intent.getStringExtra("jumlah");
        metode = intent.getStringExtra("metode");


        input_nama = findViewById(R.id.nama);
        input_id = findViewById(R.id.idgame);
        input_jumlah = findViewById(R.id.jumlah);
        input_metode = findViewById(R.id.metodebayar);
        btn_beli = findViewById(R.id.btnbeli);
        btn_delete = findViewById(R.id.btnDelete);

        condition(yid);

    }

    private void condition(String id) {

        if(id == null){
            btn_beli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Add();
                }
            });
            btn_delete.setVisibility(View.GONE);
        }else {
            TextView text_title = findViewById(R.id.textTitle);
            text_title.setText("KERANJANG");
            input_nama.setText(usergame);
            input_id.setText(idgame);
            input_jumlah.setText(jumlah);
            input_metode.setText(metode);

            btn_beli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    update();
                }
            });
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Delete();
                }
            });
        }
    }

    private void Delete() {
        String aid = yid;

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<User> call = apiInterface.hapus(aid);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if(value.equals("1")){
                    Toast.makeText(Payment.this, message.toString(), Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(Payment.this, message.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Payment.this, t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }


    private void update() {
        String aid = yid;
        String usergame = input_nama.getText().toString();
        String idgame = input_id.getText().toString();
        String jumlah = input_jumlah.getText().toString();
        String metode = input_metode.getText().toString();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<User> call = apiInterface.baru(aid,usergame,idgame,jumlah,metode);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if(value.equals("1")){
                    Toast.makeText(Payment.this, message.toString(), Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(Payment.this, message.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Payment.this, t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void Add() {
        String usergame = input_nama.getText().toString().trim();
        String idgame = input_id.getText().toString().trim();
        String jumlah = input_jumlah.getText().toString().trim();
        String metode = input_metode.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<User> call = apiInterface.masuk(usergame,idgame,jumlah,metode);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if(value.equals("1")){
                    Toast.makeText(Payment.this, message.toString(), Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Payment.this, message.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Payment.this, t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
}