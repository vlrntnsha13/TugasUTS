package com.example.tugasuts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView Splassh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Splassh = (TextView) findViewById(R.id.sikrs);

        SharedPreferences prefs = MainActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null );
        if (statusLogin != null){
            if (statusLogin.equals("admin")){
                Intent intent = new Intent(MainActivity.this,
                        HalamanAdminActivity.class);
                startActivity(intent);
            }
            else if (statusLogin.equals("mhs")){
                Intent intent = new Intent(MainActivity.this,
                        HalamanDosenActivity.class);
                startActivity(intent);
            }
        }
        else{
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), HalamanLoginActivity.class));
                    finish();
                }
            }, 2000L); //2000 L = 2 detik
        }
    }
}


