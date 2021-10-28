package com.marijmokoginta.shopmanagement;

public class Barang {
    private String ID;
    private String namaBarang;
    private String kategori;
    private String hargaSatuan;
    private String jumlah;
    private String createdAt;

    public Barang(String ID, String namaBarang, String kategori, String hargaSatuan, String jumlah, String createdAt) {
        this.ID = ID;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.hargaSatuan = hargaSatuan;
        this.jumlah = jumlah;
        this.createdAt = createdAt;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID){
        this.ID = "B" + ID;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(String hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
