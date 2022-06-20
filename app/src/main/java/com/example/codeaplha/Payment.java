package com.example.codeaplha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment extends AppCompatActivity {

    EditText nid, ndibeli;
    Button ganti,hapoos;
    Apiinterface apiinterface;
    String ida,idb,jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        ida = intent.getStringExtra("id");
        idb = intent.getStringExtra("idgame");
        jumlah = intent.getStringExtra("jumplah");

        nid = findViewById(R.id.idgame);
        ndibeli = findViewById(R.id.jumbeli);

        condition(ida);
    }
    private  void  condition(String id){
        if(id == null){
            ganti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Add();
                }
            });
            hapoos.setVisibility(View.GONE);
        }else {
            nid.setText(idb);
            ndibeli.setText(jumlah);

            ganti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Update();
                }
            });
            hapoos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Delete();
                }
            });
        }


    }

    private void Delete() {
        String idg = ida;

        apiinterface = Client.getClient().create(Apiinterface.class);

        Call<User> call = apiinterface.hapos(idg);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String value = response.body().getValue();
                String massage = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(Payment.this, massage, Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(Payment.this, massage, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Payment.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void Update() {
        String idg = ida;
        String idgame = nid.getText().toString();
        String jumplah = ndibeli.getText().toString();

        apiinterface = Client.getClient().create(Apiinterface.class);
        Call <User> call = apiinterface.baru(idg,idgame,jumplah);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(Payment.this, message.toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Payment.this, message.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Payment.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Add() {

        String idgame = nid.getText().toString();
        String jumplah = ndibeli.getText().toString();

        apiinterface = Client.getClient().create(Apiinterface.class);
        Call <User> call = apiinterface.masuk(idgame,jumplah);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")){
                    Toast.makeText(Payment.this, message.toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Payment.this, message.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Payment.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}