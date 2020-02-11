package com.coswick.travelinktrial.BelajarOOP;

public class BelajarOOP {
    String merek = "ahahahahah";
    String warna = "merah";
    int harga= 1000000;
    final int tahun = 2016;

    int jarak = 90;
    int kecepatan = 60;
    int waktu = 3600;

    void jalan(){                                          // Method Prosedure
        System.out.println("laju mobil dengan kecepatan 10km/jam");
        System.out.println("warna mobil adalah "+ warna);
        System.out.println("harga mobil "+harga);
    }

    int jarakTempuh(){                                   //methode function
        int itungjarak = kecepatan * waktu;
        return itungjarak;
    }

    int waktutempuh(){
        int itungWaktu = jarak / kecepatan;
        return itungWaktu;
    }

    int normalBadan(int tinggi, int berat){               //methode function dengan 2 parameter
        int beratNormal = tinggi / berat;
        return beratNormal;
    }

    void umur(String berat, String panjang){
        System.out.println("berat adalah"+berat);
        System.out.println("panjang adalah "+panjang);
    }


    String nama;
    void cariNama(String nama, String umur){                // method prosedure dengan 2 paramete dan fungsi this
        this.nama = nama;
        this.nama = umur;
    }

    BelajarOOP(String nama, String umur){                    // Constructor dengan 2 parameter dan dio ovrloading
        System.out.println("constructor ke 1");
    }

    BelajarOOP(String nama, String umur, String panjang){
        System.out.println("constructor ke 2");
    }


    // 11. Belajar Static
    public class Users{
        public String nama;
        public String nik;
        public int umur;

        public void tanyaNama(){
            System.out.println("Nama : " );
        }

        public int umur(){
            int hasilUmur = 20;
            return hasilUmur;
        }
    }



}
