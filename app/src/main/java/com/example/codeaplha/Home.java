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
    private TextView nama, telpon;
    private Button btn_logout;
    SessionManager sessionManager;
    CardView btn_PAYMENT,btn_RIWAYAR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        nama = findViewById(R.id.nama);
        telpon = findViewById(R.id.telpon);
        btn_logout = findViewById(R.id.btnlogout);
        btn_RIWAYAR = (CardView) findViewById(R.id.pesanan);


        HashMap<String, String> user = sessionManager.getUserDetail();
        String mNama = user.get(sessionManager.NAMA);
        String mTelpon = user.get(sessionManager.TELPON);

        nama.setText(mNama);
        telpon.setText(mTelpon);

        btn_PAYMENT = (CardView) findViewById(R.id.service);
        btn_PAYMENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Payment.class);
                startActivity(intent);

            }
        });

        btn_RIWAYAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Riwayat.class);
                startActivity(intent);
            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });

    }
}