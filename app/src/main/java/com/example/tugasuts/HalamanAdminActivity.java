package com.example.tugasuts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HalamanAdminActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_admin);
        this.setTitle("SI KRS-Hai [Nama Admin]");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuadmin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HalamanAdminActivity.this);
            builder.setMessage("Apakah anda yakin untuk logout ?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(HalamanAdminActivity.this, "Tidak jadi logout",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences prefs = HalamanAdminActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
                            String statusLogin = prefs.getString("isLogin",null );
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("isLogin", null);
                            edit.commit();
                            Intent intent = new Intent(HalamanAdminActivity.this,HalamanLoginActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return true;
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
