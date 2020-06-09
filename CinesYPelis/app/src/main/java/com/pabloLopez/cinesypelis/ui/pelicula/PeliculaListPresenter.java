package com.pabloLopez.cinesypelis.ui.pelicula;

import com.pabloLopez.cinesypelis.base.BaseListContract;
import com.pabloLopez.cinesypelis.data.model.Pelicula;
import com.pabloLopez.cinesypelis.data.repositorio.PeliculaRepositories;

public class PeliculaListPresenter implements PeliculaListContract.Presenter {

    PeliculaListContract.View view;

    public PeliculaListPresenter(PeliculaListContract.View view)
    {
        this.view=view;
    }

    //region Implementados por la interfaz PeliculaListContract.Presenter
    @Override
    public void cargarDatos() {
        if(PeliculaRepositories.getInstance().getList().isEmpty())
            view.mensaje("No hay datos.");
        else
            view.hayDatos(PeliculaRepositories.getInstance().getList());
    }

    @Override
    public boolean insertar(Pelicula objeto) {
        return PeliculaRepositories.getInstance().insert(objeto);
    }

    @Override
    public boolean actualizar(Pelicula objeto) {
        if(PeliculaRepositories.getInstance().update(objeto)){
            view.mensaje("Pelicula " + objeto.getNombre() + " ha sido actualizada");
            return true;
        }
        else
        {
            view.mesajeError("Pelicula " + objeto.getNombre() + " no ha sido eliminada");
            return false;
        }
    }

    @Override
    public boolean eliminar(Pelicula objeto) {
        if(PeliculaRepositories.getInstance().delete(objeto)) {
            view.mensaje("Pelicula " + objeto.getNombre() + " ha sido eliminada");
            return true;
        }
        else
        {
            view.mesajeError("No se puedo eliminar la Pelicula " + objeto.getNombre());
            return false;
        }
    }

    //endregion
}
