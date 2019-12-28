package com.coswick.travelinktrial.db_favorite_room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.coswick.travelinktrial.model.FavoriteModel;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    public void addData(FavoriteModel favoriteModel);

    @Query("select * from FavoriteModel")
    public List<FavoriteModel> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM FavoriteModel WHERE id=:id)")
    public int isFavorite(int id);

    @Delete
    public void delete(FavoriteModel favoriteModel);


}
