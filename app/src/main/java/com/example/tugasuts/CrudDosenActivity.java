package com.example.tugasuts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CrudDosenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);

        Button simpan2Button = (Button)findViewById(R.id.btnSimpan2);
        simpan2Button.setOnClickListener(simpan2ButtonListener);

    }

    private View.OnClickListener simpan2ButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CrudDosenActivity.this);
            builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudDosenActivity.this, "Tidak jadi disimpan",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudDosenActivity.this, "Data berhasil disimpan",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CrudDosenActivity.this,HalamanAdminActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };


}
