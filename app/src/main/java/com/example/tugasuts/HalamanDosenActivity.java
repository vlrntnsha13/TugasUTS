package com.example.tugasuts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HalamanDosenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_dosen);
        this.setTitle( "SI KRS-Hai [Nama Dosen]");
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
            AlertDialog.Builder builder = new AlertDialog.Builder(HalamanDosenActivity.this);
            builder.setMessage("Apakah anda yakin untuk logout ?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(HalamanDosenActivity.this, "Tidak jadi logout",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences prefs = HalamanDosenActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
                            String statusLogin = prefs.getString("isLogin",null );
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("isLogin", null);
                            edit.commit();
                            Intent intent = new Intent(HalamanDosenActivity.this,HalamanLoginActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return true;
    }


}
