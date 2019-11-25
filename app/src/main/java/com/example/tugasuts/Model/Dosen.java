package com.example.tugasuts.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dosen {

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("nidn")
    @Expose
    private String nidn;

    @SerializedName("gelar")
    @Expose
    private String gelar;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    public Dosen(String foto, String id, String nama, String nidn, String gelar, String email, String alamat) {
        this.setFoto(foto);
        this.setId(id);
        this.nama = nama;
        this.setNidn(nidn);
        this.setGelar(gelar);
        this.setEmail(email);
        this.setAlamat(alamat);
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;

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


    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
