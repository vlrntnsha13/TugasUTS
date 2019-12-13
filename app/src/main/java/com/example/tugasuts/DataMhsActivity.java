package com.example.tugasuts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tugasuts.Adapter.MhsAdapter;
import com.example.tugasuts.Model.Mahasiswa;
import com.example.tugasuts.Network.DefaultResult;
import com.example.tugasuts.Network.GetDataService;
import com.example.tugasuts.Network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataMhsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMhs;
    private MhsAdapter mhsAdapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mhs);
        this.setTitle( "SI KRS-Hai [Nama Mhs] // DAFTAR MAHASISWA");

        //addData();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading, please wait..");
        progressDialog.show();

        recyclerViewMhs = findViewById(R.id.recvmhs);
        mhsAdapter = new MhsAdapter(mahasiswaArrayList);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(GetDataService.class);
        Call<ArrayList<Mahasiswa>> call = service.getMhsAll("72170143");
        call.enqueue(new Callback<ArrayList<Mahasiswa>>() {
            @Override
            public void onResponse(Call<ArrayList<Mahasiswa>> call, Response<ArrayList<Mahasiswa>> response) {
                progressDialog. dismiss();

                mahasiswaArrayList = response.body();
                mhsAdapter = new MhsAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataMhsActivity.this);
                recyclerViewMhs.setLayoutManager(layoutManager);
                recyclerViewMhs.setAdapter(mhsAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Mahasiswa>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DataMhsActivity.this, "Login failed, please try again", Toast.LENGTH_SHORT);
            }
        });

        registerForContextMenu(recyclerViewMhs);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Mahasiswa mahasiswa = mahasiswaArrayList.get(item.getGroupId());
        if (item.getTitle().equals("Ubah Data Dosen")) {
            Intent intent = new Intent(DataMhsActivity.this, CrudMhsActivity.class);
            intent.putExtra("idMhs", mahasiswa.getId());
            intent.putExtra("nama", mahasiswa.getNama());
            intent.putExtra("nim", mahasiswa.getNim());
            intent.putExtra("alamat", mahasiswa.getAlamat());
            intent.putExtra("email", mahasiswa.getEmail());
            intent.putExtra("foto", mahasiswa.getMhsImage());
            intent.putExtra("is_update", true);
            startActivity(intent);
        }else if(item.getTitle() == "Hapus Data Dosen"){
            progressDialog = new ProgressDialog(DataMhsActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_mhs(
                    mahasiswa.getId(),
                    "72170143" );
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();

                    Toast.makeText(DataMhsActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DataMhsActivity.this, DataMhsActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(DataMhsActivity.this, "Connection Timed Out, Please Try Again", Toast.LENGTH_SHORT);
                }
            });
        }
        return super.onContextItemSelected(item);
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
            Intent intent = new Intent(DataMhsActivity.this,CrudMhsActivity.class);
            startActivity(intent);
        }if (item.getItemId() == R.id.update){
            Intent intent = new Intent(DataMhsActivity.this,CrudMhsActivity.class);
            startActivity(intent);

        }if (item.getItemId() == R.id.delete){
            Intent intent = new Intent(DataMhsActivity.this,CrudMhsActivity.class);
            startActivity(intent);

        }
        return true;
    }


    /* private void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa(R.drawable.datadiri, "Valeriana Tanesha", "72170143",
                "v@gmail.com", "Jalan Mangga"));
        mahasiswaArrayList.add(new Mahasiswa(R.drawable.datadiri,"Grace Hutabarat", "72170171",
                "g@gmail.com", "Jalan Rambutan"));
    } */
}
