package com.example.tugasuts.Model;

public class Dosen {
    public int mhsImage;
    private String nama;
    private String nidn;
    private String gelar;
    private String email;
    private String alamat;

    public Dosen(int mhsImage, String nama, String nidn, String gelar, String email, String alamat) {
        this.mhsImage = mhsImage;
        this.nama = nama;
        this.setNidn(nidn);
        this.setGelar(gelar);
        this.setEmail(email);
        this.setAlamat(alamat);
    }

    public int getMhsImage() {
        return mhsImage;
    }

    public void setMhsImage(int mhsImage) {
        this.mhsImage = mhsImage;
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
}
