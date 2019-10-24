package com.example.tugasuts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tugasuts.Adapter.MhsAdapter;
import com.example.tugasuts.Model.Mahasiswa;

import java.util.ArrayList;

public class DataMhsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMhs;
    private MhsAdapter mhsAdapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mhs);
        this.setTitle( "SI KRS-Hai [Nama Mhs]");

        addData();

        recyclerViewMhs = findViewById(R.id.recvmhs);
        mhsAdapter = new MhsAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataMhsActivity.this);
        recyclerViewMhs.setLayoutManager(layoutManager);
        recyclerViewMhs.setAdapter(mhsAdapter);

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
            setContentView(R.layout.activity_crud_mhs);
        }if (item.getItemId() == R.id.update){
            setContentView(R.layout.activity_crud_mhs);

        }if (item.getItemId() == R.id.delete){
            setContentView(R.layout.activity_crud_mhs);

        }
        return true;
    }


    private void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa(R.drawable.datadiri, "Valeriana Tanesha", "72170143",
                "v@gmail.com", "Jalan Mangga"));
        mahasiswaArrayList.add(new Mahasiswa(R.drawable.datadiri,"Grace Hutabarat", "72170171",
                "g@gmail.com", "Jalan Rambutan"));
    }
}
