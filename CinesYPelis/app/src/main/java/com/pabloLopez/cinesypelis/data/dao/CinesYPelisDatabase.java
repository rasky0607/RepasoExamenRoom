package com.pabloLopez.cinesypelis.data.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pabloLopez.cinesypelis.data.model.Cine;
import com.pabloLopez.cinesypelis.data.model.Pelicula;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*Esta calse es la encargada de crear la Base de datos*/
@Database(entities={Pelicula.class, Cine.class},version=2,exportSchema=false)
public abstract class CinesYPelisDatabase extends RoomDatabase {

    //Inicializamos distintas interfaces de DaoContractBase que corresponden a los diferentes repositorios
    public abstract DaoContractBase.PeliculaDaoContract daoPelicula();
    public abstract DaoContractBase.CineDaoContract daoCine();

    private static volatile CinesYPelisDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (CinesYPelisDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CinesYPelisDatabase.class, "cineYpelis")//Nombre de la base de datos
                            .fallbackToDestructiveMigration() //para borrar la base de datos en cada nueva ejecucion
                            .build();
                }
            }
        }
    }

    public static CinesYPelisDatabase getDatabase() {
        return INSTANCE;
    }
}
