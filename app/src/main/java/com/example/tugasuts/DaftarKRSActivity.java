package com.example.tugasuts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tugasuts.Adapter.KrsAdapter;
import com.example.tugasuts.Adapter.MhsAdapter;
import com.example.tugasuts.Model.Krs;
import com.example.tugasuts.Model.Mahasiswa;

import java.util.ArrayList;

public class DaftarKRSActivity extends AppCompatActivity {
    private RecyclerView recKrs;
    private KrsAdapter krsAdapter;
    private ArrayList<Krs> krsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_krs);


        addData();

        recKrs = findViewById(R.id.recKrs);
        krsAdapter = new KrsAdapter(krsArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DaftarKRSActivity.this);
        recKrs.setLayoutManager(layoutManager);
        recKrs.setAdapter(krsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.create){
            Intent intent = new Intent(DaftarKRSActivity.this,CrudKrsActivity.class);
            startActivity(intent);
        }if (item.getItemId() == R.id.update){
            Intent intent = new Intent(DaftarKRSActivity.this,CrudKrsActivity.class);
            startActivity(intent);

        }if (item.getItemId() == R.id.delete){
            Intent intent = new Intent(DaftarKRSActivity.this,CrudKrsActivity.class);
            startActivity(intent);

        }
        return true;
    }

    private void addData(){
        krsArrayList = new ArrayList<>();
        krsArrayList.add(new Krs("SI123", "Algoritma dan Pemrograman", "Senin",
                "1", "3", "Katon", "30"));
        krsArrayList.add(new Krs("SI123", "Algoritma dan Pemrograman", "Senin",
                "1", "3", "Katon", "30"));
        krsArrayList.add(new Krs("SI123", "Algoritma dan Pemrograman", "Senin",
                "1", "3", "Katon", "30"));
        krsArrayList.add(new Krs("SI123", "Algoritma dan Pemrograman", "Senin",
                "1", "3", "Katon", "30"));
        krsArrayList.add(new Krs("SI123", "Algoritma dan Pemrograman", "Senin",
                "1", "3", "Katon", "30"));
        krsArrayList.add(new Krs("SI123", "Algoritma dan Pemrograman", "Senin",
                "1", "3", "Katon", "30"));
    }
}
