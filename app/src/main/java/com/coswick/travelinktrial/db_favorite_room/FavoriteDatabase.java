package com.coswick.travelinktrial.db_favorite_room;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.coswick.travelinktrial.model.FavoriteModel;

@Database(entities={FavoriteModel.class},version = 1)
public abstract class FavoriteDatabase extends RoomDatabase{

    public abstract FavoriteDao favoriteDao();


}

