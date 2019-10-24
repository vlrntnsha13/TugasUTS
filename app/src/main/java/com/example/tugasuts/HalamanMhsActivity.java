package com.example.tugasuts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HalamanMhsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_mhs);
        this.setTitle( "SI KRS-Hai Valeriana Tanesha");


    }

    public void DataDiri(View view) {
        Intent intent = new Intent(HalamanMhsActivity.this,CrudMhsActivity.class);
        startActivity(intent);
    }

    public void DaftarKRS(View view) {
        Intent intent = new Intent(HalamanMhsActivity.this,DaftarKRSActivity.class);
        startActivity(intent);
    }
    public void LihatKelas(View view) {
        Intent intent = new Intent(HalamanMhsActivity.this,DaftarKRSActivity.class);
        startActivity(intent);
    }
}
