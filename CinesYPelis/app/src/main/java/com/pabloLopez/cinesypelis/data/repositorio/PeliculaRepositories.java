package com.pabloLopez.cinesypelis.data.repositorio;

import com.pabloLopez.cinesypelis.data.dao.CinesYPelisDatabase;
import com.pabloLopez.cinesypelis.data.dao.DaoContractBase;
import com.pabloLopez.cinesypelis.data.model.Pelicula;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PeliculaRepositories {
    //creamos un objeto del contrato que realizara consultas sobre la tabla de peliculas
    private DaoContractBase.PeliculaDaoContract dao;

    //Patron singleton que devuelve una unica instancia de la clase repositorie
    static PeliculaRepositories INSTANCE;
    public  static  PeliculaRepositories getInstance(){
        if(INSTANCE==null)
            INSTANCE=new PeliculaRepositories();
        return INSTANCE;
    }

    public PeliculaRepositories(){
        //Inicializamos el objeto
        dao= CinesYPelisDatabase.getDatabase().daoPelicula();//Optenemos la tabla de la BD
    }

    //Obtenemos la lista de peliculas de la tabla de la BD
    public List<Pelicula> getList(){
        try {
            return CinesYPelisDatabase.databaseWriteExecutor.submit(() -> dao.getAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }
    //region Sentencias de manipulacion de datos
    public boolean insert(final Pelicula pelicula){
        CinesYPelisDatabase.databaseWriteExecutor.execute(()->dao.insertDao(pelicula));
        return  true;
    }

    public boolean delete(final Pelicula pelicula){
        CinesYPelisDatabase.databaseWriteExecutor.execute(()->dao.deletetDao(pelicula));
        return  true;
    }

    public boolean update(final Pelicula pelicula){
        CinesYPelisDatabase.databaseWriteExecutor.execute(()->dao.updateDao(pelicula));
        return  true;
    }
    //endregion

}
