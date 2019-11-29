package com.coswick.travelinktrial.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.coswick.travelinktrial.model.ClientModel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


/**
 * Created by Lucas Albuquerque on 09/03/2018.
 */

public class Helper<T> extends OrmLiteSqliteOpenHelper {

    private static final String DATASABE = "orm.db";
    private static final int VERSION = 1;

    public Helper(Context context) {
        super(context, DATASABE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, ClientModel.class);
        }catch (java.sql.SQLException e){
            Log.e(Helper.class.getName(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // implements...
    }

    @Override
    public void close(){
        super.close();
    }
}
