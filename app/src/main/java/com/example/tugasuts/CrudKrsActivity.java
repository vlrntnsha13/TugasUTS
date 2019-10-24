package com.example.tugasuts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    }
}
