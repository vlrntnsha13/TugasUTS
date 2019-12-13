package com.example.tugasuts.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {

    @SerializedName("idMhs")
    @Expose
    private String id;

    @SerializedName("foto")
    @Expose
    private String mhsImage;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("nim")
    @Expose
    private String nim;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    public Mahasiswa(String id, String mhsImage, String nama, String nim, String email, String alamat) {
        this.setId(id);
        this.setMhsImage(mhsImage);
        this.nama = nama;
        this.nim = nim;
        this.setEmail(email);
        this.setAlamat(alamat);
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;

    }
    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getMhsImage() {
        return mhsImage;
    }

    public void setMhsImage(String mhsImage) {
        this.mhsImage = mhsImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
