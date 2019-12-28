package com.coswick.travelinktrial.db_crud_ormite;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.util.List;

public abstract class GenericDAO<T> extends Helper<T> {

    protected Dao<T, Integer> dao;
    private Class<T> type;

    public GenericDAO(Context context, Class<T> type) {
        super(context);
        this.type = type;
        setDao();
    }

    protected void setDao(){
        try{
            dao = DaoManager.createDao(getConnectionSource(), type);
        }catch (Exception e){
            Log.e(GenericDAO.class.getName(), e.getMessage());
        }
    }

    public boolean insert(T obj) {
        boolean status = false;
        try{
            dao.create(obj);
            status = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public boolean update(T obj) {
        boolean status = false;
        try{
            dao.update(obj);
            status = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public void delete(T obj) {
        try{
            dao.delete(obj);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public T getById(int id) {
        try{
            T obj = dao.queryForId(id);
            return obj;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<T> getAll() {
        try{
            List<T> result = dao.queryForAll();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<T> findByName(final String name){
        try{
            List<T> result = dao.queryBuilder().where().like("name", "%"+name+"%").query();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
