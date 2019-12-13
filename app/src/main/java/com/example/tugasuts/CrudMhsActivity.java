package com.example.tugasuts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tugasuts.Network.DefaultResult;
import com.example.tugasuts.Network.GetDataService;
import com.example.tugasuts.Network.RetrofitClientInstance;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CrudMhsActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    GetDataService service;

    EditText nama, nim, alamat, email, fotoMhs;
    Boolean isUpdate = false;
    String idMhs;

    private Uri uri;
    private ImageView imgMhs;
    String stringImg;
    private static final int GALLERY_REQUEST_CODE = 58;
    private static final int FILE_ACCESS_REQUEST_CODE = 58;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_mhs);
        this.setTitle("SI KRS - Hai Admin");

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, FILE_ACCESS_REQUEST_CODE);
        }

        nama = (EditText)findViewById(R.id.namaMhs);
        nim = (EditText)findViewById(R.id.nim);
        alamat = (EditText)findViewById(R.id.alamatMhs);
        email = (EditText)findViewById(R.id.emailMhs);
        fotoMhs = findViewById(R.id.namaFoto);
        imgMhs = findViewById(R.id.imgMhs);

        final Button browseButton = findViewById(R.id.browseFoto);
        browseButton.setOnClickListener(browseButtonListener);

        checkUpdate();
        Button simpanButton = (Button)findViewById(R.id.btnSimpan);
        if (isUpdate){
            simpanButton.setText("Update");
        }
        simpanButton.setOnClickListener(simpanButtonListener);
    }



    private View.OnClickListener simpanButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Boolean isValid = true;

            if (nama.getText().toString().matches("")){
                nama.setError("Silahkan isi nama");
                isValid = false;
            }
            if (nim.getText().toString().matches("")){
                nim.setError("Silahkan isi nim");
                isValid = false;
            }
            if (alamat.getText().toString().matches("")){
                alamat.setError("Silahkan isi alamat");
                isValid = false;
            }
            if (email.getText().toString().matches("")){
                email.setError("Silahkan isi email");
                isValid = false;
            }

            if (!isUpdate){
                if (isValid){
                    AlertDialog.Builder builder = new AlertDialog.Builder(CrudMhsActivity.this);
                    builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(CrudMhsActivity.this, "Tidak jadi disimpan",
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
                } else {
                    Toast.makeText(CrudMhsActivity.this, "Silahkan isi Data Dosen", Toast.LENGTH_SHORT).show();
                }
            }else if (isUpdate) {
                if (isValid){
                    AlertDialog.Builder builder = new AlertDialog.Builder(CrudMhsActivity.this);
                    builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(CrudMhsActivity.this, "Tidak jadi diupdate",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    updateDataDosen();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Toast.makeText(CrudMhsActivity.this, "Silahkan isi Data Dosen", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public void addData(){
        //progressDialog.show(this,null,"Tunggu",true,false);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading, please wait..");
        progressDialog.show();

        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<DefaultResult> call = service.insert_mhs(nama.getText().toString(), nim.getText().toString(),
                alamat.getText().toString(),
                email.getText().toString(),
                stringImg, "72170143");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();

                Toast.makeText(CrudMhsActivity.this, "Data berhasil disimpan",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CrudMhsActivity.this,DataMhsActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CrudMhsActivity.this, "Connection Timed Out, Please Try Again", Toast.LENGTH_SHORT);
            }
        });
    }

    private void updateDataDosen() {
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        progressDialog = ProgressDialog.show(CrudMhsActivity.this, null,
                "Loading ...", true, false);

        Call<DefaultResult> call = service.update_mhs(idMhs,
                nama.getText().toString(),
                nim.getText().toString(),
                alamat.getText().toString(),
                email.getText().toString(),
                stringImg, "72170143");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CrudMhsActivity.this, "Data berhasil diupdate",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CrudMhsActivity.this,DataMhsActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CrudMhsActivity.this, "Connection Timed Out, Please Try Again", Toast.LENGTH_SHORT);
            }
        });
    }


    void checkUpdate(){
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return;
        }

        //get data via this key
        isUpdate = extras.getBoolean("is_update");
        idMhs = extras.getString("idMhs");
        nama.setText(extras.getString("nama"));
        nim.setText(extras.getString("nim"));
        alamat.setText(extras.getString("alamat"));
        email.setText(extras.getString("email"));
        fotoMhs.setText(extras.getString("foto"));
    }

    private View.OnClickListener browseButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            String[] mimeTypes = {"image/jpeg"}; //UNTUK MEMILIH FOTO SAJA
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            startActivityForResult(intent,GALLERY_REQUEST_CODE);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case FILE_ACCESS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED){
                    //Permission Granted
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    imgMhs.setImageURI(selectedImage);

                    //pakai string bitmap
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    //GET THE CURSOR
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn,
                            null, null, null);
                    //move to first row
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imgDecodableString = cursor.getString(columnIndex);
//                    foto.setText(imgDecodableString);
                    cursor.close();

                    Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();

                    stringImg = Base64.encodeToString(b, Base64.DEFAULT);
                    break;
            }
        }
    }
}
