package com.example.sistempakarteodemp.entities;

import java.io.Serializable;

public class Gejala implements Serializable {
    private int id_gejala;
    private String nama_gejala;
    private String penyakit_terpilih;


    @Override
    public String toString() {
        return  nama_gejala;
    }

    public int getId_gejala() {
        return id_gejala;
    }

    public void setId_gejala(int id_gejala) {
        this.id_gejala = id_gejala;
    }

    public String getNama_gejala() {
        return nama_gejala;
    }

    public void setNama_gejala(String nama_gejala) {
        this.nama_gejala = nama_gejala;
    }

    public String getPenyakit_terpilih() {
        return penyakit_terpilih;
    }

    public void setPenyakit_terpilih(String penyakit_terpilih) {
        this.penyakit_terpilih = penyakit_terpilih;
    }
}
