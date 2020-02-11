package com.coswick.travelinktrial.login;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    //import SharedPreferences
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    //init key nama sp
    private final String SP_NAME = "loginsession";
    //init key yang akan disimpan di sp
    public static final String SP_NAMA = "nama" ;
    public static final String SP_EMAIL = "email";
    public static final String SP_SUDAH_LOGIN = "sudah login" ;
    //contructor
    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    } //menyimpan data string
    public void saveSPString(String keySP, String value){
        editor.putString(keySP, value);
        editor.commit();
    } //menyimpan data int
    public void saveSPInt(String keySP, int value){
        editor.putInt(keySP, value);
        editor.commit();
    } //menyimpan data boolean
    public void saveSPBolean(String keySP, boolean value){
        editor.putBoolean(keySP, value);
        editor.commit();
    } //menampilkan value yang disimpan di sp berdasarkan key
    public String getKeyNama() {
        return sp.getString(SP_NAMA,"");
    }

    public String getKeyEmail() {
        return sp.getString(SP_EMAIL,"");
    }
    //menentukan user sudah login atau belum
    public boolean Login(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}

