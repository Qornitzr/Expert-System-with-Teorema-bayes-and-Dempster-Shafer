package com.example.sistempakarteodemp.entities;

import java.io.Serializable;

public class Riwayat implements Serializable {
    private int id_riwayat;
    private String tanggal;
    private String riwayat_nama_penyakit;
    private String riwayat_nilai;
    private String riwayat_nama_gejala;

    public int getId_riwayat() {
        return id_riwayat;
    }

    public void setId_riwayat(int id_riwayat) {
        this.id_riwayat = id_riwayat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getRiwayat_nama_penyakit() {
        return riwayat_nama_penyakit;
    }

    public void setRiwayat_nama_penyakit(String riwayat_nama_penyakit) {
        this.riwayat_nama_penyakit = riwayat_nama_penyakit;
    }

    public String getRiwayat_nilai() {
        return riwayat_nilai;
    }

    public void setRiwayat_nilai(String riwayat_nilai) {
        this.riwayat_nilai = riwayat_nilai;
    }

    public String getRiwayat_nama_gejala() {
        return riwayat_nama_gejala;
    }

    public void setRiwayat_nama_gejala(String riwayat_nama_gejala) {
        this.riwayat_nama_gejala = riwayat_nama_gejala;
    }
}
