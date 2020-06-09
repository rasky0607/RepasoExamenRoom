package com.pabloLopez.cinesypelis.ui.pelicula;

import com.pabloLopez.cinesypelis.data.model.Pelicula;
import com.pabloLopez.cinesypelis.data.repositorio.PeliculaRepositories;

public class PeliculasAddOeditPresenter implements PeliculaAddOeditContract.Presenter{
    PeliculaAddOeditContract.View view;

    public PeliculasAddOeditPresenter(PeliculaAddOeditContract.View view){
        this.view=view;
    }

    @Override
    public void anadir(Pelicula objeto) {
        if(PeliculaRepositories.getInstance().insert(objeto))
            view.mensaje("Registro de pelicula "+objeto.getNombre()+" insertado");
        else
            view.mensajeError("No se puedo insertar el registro de la pelicula "+objeto.getNombre());
    }

    @Override
    public void modificar(Pelicula objeto) {
        if(PeliculaRepositories.getInstance().update(objeto))
            view.mensaje("Registro de pelicula "+objeto.getNombre()+" actualizado");
        else
            view.mensajeError("No se puedo actualizar el registro de la pelicula "+objeto.getNombre());
    }

    @Override
    public boolean validar() {
       return view.esValido();
    }
}
