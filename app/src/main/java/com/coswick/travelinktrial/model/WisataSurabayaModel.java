package com.coswick.travelinktrial.model;

public class WisataSurabayaModel {

    private int id;
    private String title;
    private String kategori;
    private String desc;
    private String harga;
    private String image;


    public WisataSurabayaModel(int id, String title, String kategori, String desc, String harga, String image) {
        this.id = id;
        this.title = title;
        this.kategori = kategori;
        this.desc = desc;
        this.harga = harga;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
