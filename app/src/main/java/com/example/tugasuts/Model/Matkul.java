package com.example.tugasuts.Model;

public class Matkul {
    private String kode;
    private String matkul;
    private String hari;
    private String sesi;
    private String jmlsks;

    public Matkul(String kode, String matkul, String hari, String sesi, String jmlsks) {
        this.setKode(kode);
        this.setMatkul(matkul);
        this.setHari(hari);
        this.setSesi(sesi);
        this.setJmlsks(jmlsks);
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getSesi() {
        return sesi;
    }

    public void setSesi(String sesi) {
        this.sesi = sesi;
    }

    public String getJmlsks() {
        return jmlsks;
    }

    public void setJmlsks(String jmlsks) {
        this.jmlsks = jmlsks;
    }
}
