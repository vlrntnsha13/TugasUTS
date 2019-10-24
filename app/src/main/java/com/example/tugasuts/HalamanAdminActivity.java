package com.example.tugasuts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HalamanAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_admin);
        this.setTitle( "SI KRS-Hai [Nama Admin]");
    }

    public void DataDiri(View view) {
        Intent intent = new Intent(HalamanAdminActivity.this,CrudDosenActivity.class);
        startActivity(intent);
    }

    public void DaftarDosen(View view) {
        Intent intent = new Intent(HalamanAdminActivity.this,DataDosenActivity.class);
        startActivity(intent);
    }
    public void DaftarMatkul(View view) {
        Intent intent = new Intent(HalamanAdminActivity.this,DataMatkulActivity.class);
        startActivity(intent);
    }

    public void KelolaKrs(View view) {
        Intent intent = new Intent(HalamanAdminActivity.this,DaftarKRSActivity.class);
        startActivity(intent);
    }

    public void DaftarMhs(View view) {
        Intent intent = new Intent(HalamanAdminActivity.this,DataMhsActivity.class);
        startActivity(intent);
    }
}
