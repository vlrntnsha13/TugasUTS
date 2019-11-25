package com.example.tugasuts;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

                recDosen = findViewById(R.id.recDosen);
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
            Intent intent = new Intent(DataDosenActivity.this,CrudDosenActivity.class);
            startActivity(intent);
        }if (item.getItemId() == R.id.update){
            Intent intent = new Intent(DataDosenActivity.this,CrudDosenActivity.class);
            startActivity(intent);

        }if (item.getItemId() == R.id.delete){
            Intent intent = new Intent(DataDosenActivity.this,CrudDosenActivity.class);
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
