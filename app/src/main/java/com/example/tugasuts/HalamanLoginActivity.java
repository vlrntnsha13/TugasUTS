package com.example.tugasuts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class HalamanLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);

        Button signin = (Button)findViewById(R.id.signin);
        signin.setOnClickListener(signinButtonListener);
    }

    private View.OnClickListener signinButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(HalamanLoginActivity.this,HalamanAdminActivity.class);
            startActivity(intent);
        }
    };
}
