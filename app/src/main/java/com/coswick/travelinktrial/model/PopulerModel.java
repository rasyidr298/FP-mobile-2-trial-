package com.coswick.travelinktrial.model;

public class PopulerModel {

    private int image;
    private String title;
    private String desc;
    private String harga;
    private String kategori;

    public PopulerModel(int image, String title, String desc, String harga, String kategori) {
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.harga = harga;
        this.kategori = kategori;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
