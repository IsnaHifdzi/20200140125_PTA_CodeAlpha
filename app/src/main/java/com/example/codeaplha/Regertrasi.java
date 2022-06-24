package com.example.codeaplha;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Regertrasi extends AppCompatActivity {
    private EditText namereg, telponreg, passwordreg, regpassword;
    private Button btn_register;
    private static String URL_REGIST = "http://192.168.1.7/user/regertrasi.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_regertrasi);
        namereg = findViewById(R.id.namaregister);
        telponreg = findViewById(R.id.telponregister);
        passwordreg = findViewById(R.id.password);
        regpassword = findViewById(R.id.c_password);

        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();

            }
        });
    }

    private void Regist(){


        final String nama = this.namereg.getText().toString().trim();
        final String telpon = this.telponreg.getText().toString().trim();
        final String password = this.passwordreg.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(Regertrasi.this, "Register Success !", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Regertrasi.this, "Register Eror" + e.toString(), Toast.LENGTH_SHORT).show();

                            btn_register.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Regertrasi.this, "Register Eror" + error.toString(), Toast.LENGTH_SHORT).show();

                        btn_register.setVisibility(View.VISIBLE);

                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama",nama);
                params.put("telpon",telpon);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}