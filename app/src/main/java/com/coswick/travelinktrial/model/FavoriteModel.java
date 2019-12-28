package com.coswick.travelinktrial.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName="favoritemodel")
public class FavoriteModel {

    @PrimaryKey
    private int id;


    @ColumnInfo(name = "title")
    private String name;


    @ColumnInfo(name = "kat")
    private String kategori;

    @ColumnInfo(name = "desc")
    private String deskripsi;


    @ColumnInfo(name = "harga")
    private String harga;

  @ColumnInfo(name = "img")
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
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
