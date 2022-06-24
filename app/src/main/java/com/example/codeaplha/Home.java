package com.example.codeaplha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class Home extends AppCompatActivity {

    private TextView nama,telpon;
    private Button btn_logout;
    SessionManager sessionManager;
    CardView mlbb, keranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();


        nama = findViewById(R.id.namauser);
        telpon = findViewById(R.id.telponuser);
        btn_logout = findViewById(R.id.btn_logout);
        mlbb = (CardView) findViewById(R.id.bangbang);
        keranjang = (CardView) findViewById(R.id.keranjangpem);

        HashMap<String,String> user = sessionManager.getUserDetail();
        String nNama = user.get(sessionManager.NAMA);
        String nTelpon = user.get(sessionManager.TELPON);

        nama.setText(nNama);
        telpon.setText(nTelpon);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });

        mlbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Payment.class);
                startActivity(intent);
            }
        });

        keranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Riwayat.class);
                startActivity(intent);
            }
        });

    }
}