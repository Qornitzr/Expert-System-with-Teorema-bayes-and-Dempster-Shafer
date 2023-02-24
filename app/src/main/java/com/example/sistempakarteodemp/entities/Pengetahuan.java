package com.example.sistempakarteodemp.entities;

import java.io.Serializable;

public class Pengetahuan implements Serializable {
    private int id_pengetahuan;
    private int id_penyakit;
    private String nama_penyakit;
    private int id_gejala;
    private String nama_gejala;
    private double probabilitas_mb;
    private double probabilitas_md;
    private double probabilitas_total;
    private double probabilitas_bayes;

    public int getId_pengetahuan() {
        return id_pengetahuan;
    }

    public void setId_pengetahuan(int id_pengetahuan) {
        this.id_pengetahuan = id_pengetahuan;
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

    public double getProbabilitas_mb() {
        return probabilitas_mb;
    }

    public void setProbabilitas_mb(double probabilitas_mb) {
        this.probabilitas_mb = probabilitas_mb;
    }

    public double getProbabilitas_md() {
        return probabilitas_md;
    }

    public void setProbabilitas_md(double probabilitas_md) {
        this.probabilitas_md = probabilitas_md;
    }

    public double getProbabilitas_total() {
        return probabilitas_total;
    }

    public void setProbabilitas_total(double probabilitas_total) {
        this.probabilitas_total = probabilitas_total;
    }

    public double getProbabilitas_bayes() {
        return probabilitas_bayes;
    }

    public void setProbabilitas_bayes(double probabilitas_bayes) {
        this.probabilitas_bayes = probabilitas_bayes;
    }
}
