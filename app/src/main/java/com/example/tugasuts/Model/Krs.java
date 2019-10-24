package com.example.tugasuts.Model;

public class Krs {
    private String kode;
    private String matkul;
    private String hari;
    private String sesi;
    private String jmlsks;
    private String dosen;
    private String jmlmhs;

    public Krs(String kode, String matkul, String hari, String sesi, String jmlsks, String dosen, String jmlmhs) {
        this.setKode(kode);
        this.setMatkul(matkul);
        this.setHari(hari);
        this.setSesi(sesi);
        this.setJmlsks(jmlsks);
        this.setDosen(dosen);
        this.setJmlmhs(jmlmhs);
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

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getJmlmhs() {
        return jmlmhs;
    }

    public void setJmlmhs(String jmlmhs) {
        this.jmlmhs = jmlmhs;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
