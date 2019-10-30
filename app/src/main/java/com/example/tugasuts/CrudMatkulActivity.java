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

public class CrudMatkulActivity extends AppCompatActivity {


    String[] items1 ={"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
    String[] items2 ={"1", "2", "3", "4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_matkul);

        Spinner spinner = findViewById(R.id.spinnerHari);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(CrudMatkulActivity.this, android.R.layout.simple_spinner_item, items1);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CrudMatkulActivity.this, "Anda memilih = " +items1[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(CrudMatkulActivity.this, "Anda Tidak memilih = ", Toast.LENGTH_SHORT).show();
            }
        });

        Spinner spinner1 = findViewById(R.id.spinnerSesi);

        ArrayAdapter<String> ab = new ArrayAdapter<String>(CrudMatkulActivity.this, android.R.layout.simple_spinner_item, items2);

        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(ab);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CrudMatkulActivity.this, "Anda memilih = " +items2[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(CrudMatkulActivity.this, "Anda Tidak memilih = ", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnSimpan3Button = (Button)findViewById(R.id.btnSimpan3);
        btnSimpan3Button.setOnClickListener(btnSimpan3ButtonListener);
    }

    private View.OnClickListener btnSimpan3ButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CrudMatkulActivity.this);
            builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudMatkulActivity.this, "Tidak jadi disimpan",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudMatkulActivity.this, "Data berhasil disimpan",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CrudMatkulActivity.this,HalamanAdminActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };
}
