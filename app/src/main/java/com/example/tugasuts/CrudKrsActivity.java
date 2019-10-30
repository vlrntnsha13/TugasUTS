package com.example.tugasuts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CrudKrsActivity extends AppCompatActivity {

    String[] items={"Argo", "Katon", "Yetli", "Budi", "Wimmie"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_krs);

        Spinner spinner = findViewById(R.id.spinnerDosen);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(CrudKrsActivity.this, android.R.layout.simple_spinner_item, items);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CrudKrsActivity.this, "Anda memilih = " +items[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(CrudKrsActivity.this, "Anda Tidak memilih = ", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnSimpan4Button = (Button)findViewById(R.id.btnSimpan4);
        btnSimpan4Button.setOnClickListener(btnSimpan4ButtonListener);
    }

    private View.OnClickListener btnSimpan4ButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CrudKrsActivity.this);
            builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudKrsActivity.this, "Tidak jadi disimpan",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudKrsActivity.this, "Data berhasil disimpan",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CrudKrsActivity.this,HalamanAdminActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };
}
