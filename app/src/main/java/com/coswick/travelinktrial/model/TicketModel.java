package com.coswick.travelinktrial.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "cliente" )
public class TicketModel implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nama_wisata;

    @DatabaseField
    private int harga;

    @DatabaseField
    private String jumlah_ticket;

    @DatabaseField
    private String kategori_wisata;

    @DatabaseField
    private String tanggal;

    @DatabaseField
    private String nama_pemesan;

    @DatabaseField
    private String nik;

    @DatabaseField
    private String comentar;

    public TicketModel() {
    }

    public TicketModel(int id, String nama_wisata, int harga, String jumlah_ticket, String kategori_wisata, String tanggal, String nama_pemesan, String nik, String comentar) {
        this.id = id;
        this.nama_wisata = nama_wisata;
        this.harga = harga;
        this.jumlah_ticket = jumlah_ticket;
        this.kategori_wisata = kategori_wisata;
        this.tanggal = tanggal;
        this.nama_pemesan = nama_pemesan;
        this.nik = nik;
        this.comentar = comentar;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getJumlah_ticket() {
        return jumlah_ticket;
    }

    public void setJumlah_ticket(String jumlah_ticket) {
        this.jumlah_ticket = jumlah_ticket;
    }

    public String getKategori_wisata() {
        return kategori_wisata;
    }

    public void setKategori_wisata(String kategori_wisata) {
        this.kategori_wisata = kategori_wisata;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNama_pemesan() {
        return nama_pemesan;
    }

    public void setNama_pemesan(String nama_pemesan) {
        this.nama_pemesan = nama_pemesan;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getComentar() {
        return comentar;
    }

    public void setComentar(String comentar) {
        this.comentar = comentar;
    }
}
