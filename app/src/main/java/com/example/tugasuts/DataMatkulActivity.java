package com.example.tugasuts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tugasuts.Adapter.DosenAdapter;
import com.example.tugasuts.Adapter.MatkulAdapter;
import com.example.tugasuts.Model.Dosen;
import com.example.tugasuts.Model.Krs;
import com.example.tugasuts.Model.Matkul;

import java.util.ArrayList;

public class DataMatkulActivity extends AppCompatActivity {

    private RecyclerView recmatkul;
    private MatkulAdapter matkulAdapter;
    private ArrayList<Matkul> matkulArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_matkul);

        addData();

        recmatkul = findViewById(R.id.recmatkul);
        matkulAdapter = new MatkulAdapter(matkulArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataMatkulActivity.this);
        recmatkul.setLayoutManager(layoutManager);
        recmatkul.setAdapter(matkulAdapter);


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
            Intent intent = new Intent(DataMatkulActivity.this,CrudMatkulActivity.class);
            startActivity(intent);
        }if (item.getItemId() == R.id.update){
            Intent intent = new Intent(DataMatkulActivity.this,CrudMatkulActivity.class);
            startActivity(intent);

        }if (item.getItemId() == R.id.delete){
            Intent intent = new Intent(DataMatkulActivity.this,CrudMatkulActivity.class);
            startActivity(intent);

        }
        return true;
    }

    private void addData(){
        matkulArrayList = new ArrayList<>();
        matkulArrayList.add(new Matkul("SI123", "Algoritma dan Pemrograman", "Senin",
                "1", "3"));
        matkulArrayList.add(new Matkul("SI123", "Algoritma dan Pemrograman", "Senin",
                "1", "3"));
    }

}
