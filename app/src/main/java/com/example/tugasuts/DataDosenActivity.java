package com.example.tugasuts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tugasuts.Adapter.DosenAdapter;
import com.example.tugasuts.Adapter.MhsAdapter;
import com.example.tugasuts.Model.Dosen;
import com.example.tugasuts.Model.Mahasiswa;

import java.util.ArrayList;

public class DataDosenActivity extends AppCompatActivity {
    private RecyclerView recDosen;
    private DosenAdapter dsnAdapter;
    private ArrayList<Dosen> dsnArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_dosen);

        addData();

        recDosen = findViewById(R.id.recDosen);
        dsnAdapter = new DosenAdapter(dsnArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataDosenActivity.this);
        recDosen.setLayoutManager(layoutManager);
        recDosen.setAdapter(dsnAdapter);
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
            setContentView(R.layout.activity_crud_dosen);
        }if (item.getItemId() == R.id.update){
            setContentView(R.layout.activity_crud_dosen);

        }if (item.getItemId() == R.id.delete){
            setContentView(R.layout.activity_crud_dosen);

        }
        return true;
    }

    private void addData(){
        dsnArrayList = new ArrayList<>();
        dsnArrayList.add(new Dosen(R.drawable.datadiri, "Argo Wibowo", "112233",
                "S.Kom M.T", "ar@gmail.com","Godean"));
        dsnArrayList.add(new Dosen(R.drawable.datadiri,"Yetli Oslan", "112131",
                "S.Kom", "ye@gmail.com", "Bantul"));
    }

}
