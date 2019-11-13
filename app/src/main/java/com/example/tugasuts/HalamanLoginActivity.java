package com.example.tugasuts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HalamanLoginActivity extends AppCompatActivity {

    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);

        email = findViewById(R.id.email);

        SharedPreferences prefs = HalamanLoginActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(myBtnLoginClick);
    }

    private View.OnClickListener myBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs = HalamanLoginActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
            String statusLogin = prefs.getString("isLogin",null );
            SharedPreferences.Editor edit = prefs.edit();

            if (email.getText().toString().contains("@staff.ukdw.ac.id")) {
                    edit.putString("isLogin", "admin");
                    edit.commit();
                    Intent intent = new Intent(HalamanLoginActivity.this,
                            HalamanAdminActivity.class);
                    startActivity(intent);
                }
                else if (email.getText().toString().contains("@si.ukdw.ac.id")) {
                    edit.putString("isLogin", "mhs");
                    edit.commit();
                    Intent intent = new Intent(HalamanLoginActivity.this,
                            HalamanDosenActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(HalamanLoginActivity.this, "Email salah !!",
                        Toast.LENGTH_SHORT).show();
                }
        }
    };
}
