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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.tugasuts.Network.DefaultResult;
import com.example.tugasuts.Network.GetDataService;
import com.example.tugasuts.Network.RetrofitClientInstance;

import com.example.tugasuts.Adapter.DosenAdapter;
import com.example.tugasuts.Model.Dosen;

import java.util.ArrayList;

public class DataDosenActivity extends AppCompatActivity {
    private RecyclerView recDosen;
    private DosenAdapter dsnAdapter;
    private ArrayList<Dosen> dsnArrayList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_dosen);

        recDosen = findViewById(R.id.recDosen);

        //addData();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading, please wait..");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(GetDataService.class);
        Call<ArrayList<Dosen>> call = service.getDosenAll("72170143");
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                progressDialog. dismiss();

                dsnArrayList = response.body();
                dsnAdapter = new DosenAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DataDosenActivity.this);
                recDosen.setLayoutManager(layoutManager);
                recDosen.setAdapter(dsnAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DataDosenActivity.this, "Login failed, please try again", Toast.LENGTH_SHORT);
            }
        });

        registerForContextMenu(recDosen);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Dosen dosen = dsnArrayList.get(item.getGroupId());
        if (item.getTitle() == "Ubah Data Dosen") {
            Intent intent = new Intent(DataDosenActivity.this, CrudDosenActivity.class);
            intent.putExtra("id_dosen", dosen.getId());
            intent.putExtra("nama_dosen", dosen.getNama());
            intent.putExtra("nidn", dosen.getNidn());
            intent.putExtra("alamat", dosen.getAlamat());
            intent.putExtra("email", dosen.getEmail());
            intent.putExtra("gelar", dosen.getGelar());
            intent.putExtra("foto", dosen.getFoto());
            intent.putExtra("is_update", true);
            startActivity(intent);
        }else if(item.getTitle() == "Hapus Data Dosen"){
            progressDialog = new ProgressDialog(DataDosenActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<DefaultResult> call = service.delete_dosen(
                dosen.getId(),
                    "72170143" );
            call.enqueue(new Callback<DefaultResult>() {
                @Override
                public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                    progressDialog.dismiss();

                    Toast.makeText(DataDosenActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DataDosenActivity.this, DataDosenActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<DefaultResult> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(DataDosenActivity.this, "Connection Timed Out, Please Try Again", Toast.LENGTH_SHORT);
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
        if(item.getItemId() == R.id.create) {
            Intent intent = new Intent(DataDosenActivity.this, CrudDosenActivity.class);
            startActivity(intent);
        }
        return true;
    }

    /*private void addData(){
        dsnArrayList = new ArrayList<>();
        dsnArrayList.add(new Dosen(R.drawable.datadiri,"1", "Argo Wibowo", "112233",
                "S.Kom M.T", "ar@gmail.com","Godean"));
        dsnArrayList.add(new Dosen(R.drawable.datadiri,"2", "Yetli Oslan", "112131",
                "S.Kom", "ye@gmail.com", "Bantul"));
    }
    */


}
