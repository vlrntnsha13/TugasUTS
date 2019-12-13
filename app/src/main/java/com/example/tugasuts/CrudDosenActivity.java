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
import android.widget.Toast;


import com.example.tugasuts.Network.DefaultResult;
import com.example.tugasuts.Network.GetDataService;
import com.example.tugasuts.Network.RetrofitClientInstance;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CrudDosenActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    GetDataService service;

    EditText nama, nidn,alamat, email, gelar, foto;
    Boolean isUpdate = false;
    String idDosen;

    private Uri uri;
    private ImageView imgDosen;
    String stringImg;
    private static final int GALLERY_REQUEST_CODE = 58;
    private static final int FILE_ACCESS_REQUEST_CODE = 58;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_dosen);
        this.setTitle("SI KRS - Hai Admin");

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, FILE_ACCESS_REQUEST_CODE);
        }

        nama = (EditText)findViewById(R.id.namaDosen);
        nidn = (EditText)findViewById(R.id.NIDN);
        alamat = (EditText)findViewById(R.id.alamatDosen);
        email = (EditText)findViewById(R.id.emailDosen);
        gelar = (EditText)findViewById(R.id.gelarDosen);
        foto = findViewById(R.id.namaFt);
        imgDosen = findViewById(R.id.imgDosen);

        final Button browseButton = findViewById(R.id.browse);
        browseButton.setOnClickListener(browseButtonListener);

        checkUpdate();
        Button simpan2Button = (Button)findViewById(R.id.btnSave);
        if (isUpdate){
            simpan2Button.setText("Update");
        }
        simpan2Button.setOnClickListener(simpan2ButtonListener);

    }

    private View.OnClickListener simpan2ButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Boolean isValid = true;

            if (nama.getText().toString().matches("")){
                nama.setError("Silahkan isi nama dosen");
                isValid = false;
            }
            if (nidn.getText().toString().matches("")){
                nidn.setError("Silahkan isi nidn dosen");
                isValid = false;
            }
            if (alamat.getText().toString().matches("")){
                alamat.setError("Silahkan isi alamat dosen");
                isValid = false;
            }
            if (email.getText().toString().matches("")){
                email.setError("Silahkan isi email dosen");
                isValid = false;
            }
            if (gelar.getText().toString().matches("")){
                gelar.setError("Silahkan isi gelar dosen");
                isValid = false;
            }

            if (!isUpdate){
                if (isValid){
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
                } else {
                    Toast.makeText(CrudDosenActivity.this, "Silahkan isi Data Dosen", Toast.LENGTH_SHORT).show();
                }
            }else if (isUpdate) {
                if (isValid){
                    AlertDialog.Builder builder = new AlertDialog.Builder(CrudDosenActivity.this);
                    builder.setMessage("Apakah anda yakin untuk menyimpan ?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(CrudDosenActivity.this, "Tidak jadi diupdate",
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
                    Toast.makeText(CrudDosenActivity.this, "Silahkan isi Data Dosen", Toast.LENGTH_SHORT).show();
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
        Call<DefaultResult> call = service.insert_dosen(nama.getText().toString(), nidn.getText().toString(),
                alamat.getText().toString(),
                email.getText().toString(),
                gelar.getText().toString(),
                stringImg, "72170143");
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

    private void updateDataDosen() {
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        progressDialog = ProgressDialog.show(CrudDosenActivity.this, null,
                "Loading ...", true, false);

        Call<DefaultResult> call = service.update_dosen(idDosen,
                nama.getText().toString(),
                nidn.getText().toString(),
                alamat.getText().toString(),
                email.getText().toString(),
                gelar.getText().toString(),
                stringImg, "72170143");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                progressDialog.dismiss();
                Toast.makeText(CrudDosenActivity.this, "Data berhasil diupdate",Toast.LENGTH_SHORT).show();
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


    void checkUpdate(){
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            return;
        }

        //get data via this key
        isUpdate = extras.getBoolean("is_update");
        idDosen = extras.getString("id_dosen");
        nama.setText(extras.getString("nama_dosen"));
        nidn.setText(extras.getString("nidn"));
        alamat.setText(extras.getString("alamat"));
        email.setText(extras.getString("email"));
        gelar.setText(extras.getString("gelar"));
        foto.setText(extras.getString("foto"));
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


    //private static final int PICK_IMAGE = 1;
    //private static final int PERMISSION_REQUEST_STORAGE = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    imgDosen.setImageURI(selectedImage);

                    //pakai string bitmap
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    //GET THE CURSOR
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
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


    /* @SuppressLint("ObsoleteSdkInt")
    private String getRealPathFromURI(Uri contentURI) {

        String realPath="";
// SDK < API11
        if (Build.VERSION.SDK_INT < 11) {
            String[] proj = { MediaStore.Images.Media.DATA };
            @SuppressLint("Recycle") Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
            int column_index = 0;
            String result="";
            if (cursor != null) {
                column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                realPath=cursor.getString(column_index);
            }
        }
        // SDK >= 11 && SDK < 19
        else if (Build.VERSION.SDK_INT < 19){
            String[] proj = { MediaStore.Images.Media.DATA };
            CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);
            Cursor cursor = cursorLoader.loadInBackground();
            if(cursor != null){
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                realPath = cursor.getString(column_index);
            }
        }
        // SDK > 19 (Android 4.4)
        else{
            String wholeID = DocumentsContract.getDocumentId(uri);
            // Split at colon, use second item in the array
            String id = wholeID.split(":")[1];
            String[] column = { MediaStore.Images.Media.DATA };
            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";
            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, new String[]{ id }, null);
            int columnIndex = 0;
            if (cursor != null) {
                columnIndex = cursor.getColumnIndex(column[0]);
                if (cursor.moveToFirst()) {
                    realPath = cursor.getString(columnIndex);
                }
                cursor.close();
            }
        }
        return realPath;
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }

    private void pilihfoto() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_STORAGE);

        }else{
            openGallery();
        }
    }

    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
    } */
}
