package com.example.sistempakarteodemp.entities;

import java.io.Serializable;

public class Penyakit implements Serializable {
    private int id_penyakit;
    private String nama_penyakit;
    private String deskripsi;
    private String saran;
    @Override
    public String toString() {
        return  nama_penyakit;
    }
    public int getId_penyakit() {
        return id_penyakit;
    }

    public void setId_penyakit(int id_penyakit) {
        this.id_penyakit = id_penyakit;
    }

    public String getNama_penyakit() {
        return nama_penyakit;
    }

    public void setNama_penyakit(String nama_penyakit) {
        this.nama_penyakit = nama_penyakit;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
    }
}
