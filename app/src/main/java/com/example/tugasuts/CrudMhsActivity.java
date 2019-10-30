package com.example.tugasuts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CrudMhsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_mhs);

        Button btnSimpanButton = (Button)findViewById(R.id.btnSimpan);
        btnSimpanButton.setOnClickListener(btnSimpanButtonListener);
    }

    private View.OnClickListener btnSimpanButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CrudMhsActivity.this);
            builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudMhsActivity.this, "Tidak jadi disimpan",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudMhsActivity.this, "Data berhasil disimpan",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CrudMhsActivity.this,HalamanAdminActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };
}
