package com.example.codeaplha;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView link_regist;
    private EditText nama,password;
    private Button btn_login;
    private static String URL_LOGIN = "http://192.168.1.7/user/login.php";
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        nama = findViewById(R.id.namalogin);
        password = findViewById(R.id.passwordlogin);
        btn_login = findViewById(R.id.btnlogin);
        link_regist = findViewById(R.id.edregister);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mNama =nama.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (!mNama.isEmpty()||!mPass.isEmpty()){
                    Login(mNama,mPass);
                }else {
                    nama.setError("Coba Lagi");
                    password.setError("Ulang");
                }
            }
        });


        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Regertrasi.class));
            }
        });
    }

    private void Login(String nama, String password) {



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if(success.equals("1")){
                                for(int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nama = object.getString("nama").trim();
                                    String telpon = object.getString("telpon").trim();

                                    sessionManager.createSession(nama,telpon);

                                    Intent intent = new Intent(MainActivity.this,Home.class);
                                    intent.putExtra("nama",nama);
                                    intent.putExtra("telpon",telpon);
                                    startActivity(intent);

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                            btn_login.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Error" +e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama",nama);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}