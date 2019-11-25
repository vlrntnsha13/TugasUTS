package com.example.tugasuts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugasuts.Network.DefaultResult;
import com.example.tugasuts.Network.GetDataService;
import com.example.tugasuts.Network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrudDosenActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    GetDataService service;

    EditText nama, nidn,alamat, email, gelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);
        this.setTitle("SI KRS - Hai Admin");

        Button simpan2Button = (Button)findViewById(R.id.btnSave);
        simpan2Button.setOnClickListener(simpan2ButtonListener);

    }

    private View.OnClickListener simpan2ButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CrudDosenActivity.this);
            builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(CrudDosenActivity.this, "Tidak jadi disimpan",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            addData();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    public void addData(){
        nama = (EditText)findViewById(R.id.namaDosen);
        nidn = (EditText)findViewById(R.id.NIDN);
        alamat = (EditText)findViewById(R.id.alamatDosen);
        email = (EditText) findViewById(R.id.emailDosen);
        gelar = (EditText) findViewById(R.id.gelarDosen);

        //progressDialog.show(this,null,"Tunggu",true,false);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading, please wait..");
        progressDialog.show();

        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DefaultResult> call = service.insert_dosen(nama.getText().toString(), nidn.getText().toString(),
                alamat.getText().toString(),
                email.getText().toString(), gelar.getText().toString(),
                "https://source.unsplash.com/random", "72170143");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();

                Toast.makeText(CrudDosenActivity.this, "Data berhasil disimpan",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CrudDosenActivity.this,DataDosenActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CrudDosenActivity.this, "Connection Timed Out, Please Try Again", Toast.LENGTH_SHORT);
            }
        });
    }



}
