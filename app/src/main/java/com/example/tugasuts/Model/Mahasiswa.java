package com.example.tugasuts.Model;

public class Mahasiswa {
    public int mhsImage;
    private String nama;
    private String nim;
    private String email;
    private String alamat;

    public Mahasiswa(int mhsImage, String nama, String nim, String email, String alamat) {
        this.mhsImage = mhsImage;
        this.nama = nama;
        this.nim = nim;
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
}
