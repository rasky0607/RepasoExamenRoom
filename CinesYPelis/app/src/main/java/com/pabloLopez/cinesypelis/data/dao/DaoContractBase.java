package com.pabloLopez.cinesypelis.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pabloLopez.cinesypelis.data.model.Cine;
import com.pabloLopez.cinesypelis.data.model.Pelicula;
import java.util.List;

public class DaoContractBase {
    public  interface ContractDao<T>{
        @Insert
        long insertDao(T objeto);
        @Delete
        void deletetDao(T objeto);
        @Update
        void updateDao(T objeto);
    }

    @Dao
    public interface PeliculaDaoContract extends ContractDao<Pelicula>{
        @Query("SELECT * FROM PELICULA")
        List<Pelicula> getAll();
    }

    @Dao
    public interface CineDaoContract extends ContractDao<Cine>{
        @Query("SELECT * FROM CINE")
        List<Cine> getAll();
    }
}
