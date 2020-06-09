package com.pabloLopez.cinesypelis.base;

import com.pabloLopez.cinesypelis.data.model.Pelicula;

import java.util.List;

public class BaseListContract {
    public interface View<T>{
        void hayDatos(List<T>list);
        void mensaje(String msg);
        void mesajeError(String msg);
    }
    public interface Presenter<T>{
        void cargarDatos();
        boolean insertar(T objeto);
        boolean actualizar(T objeto);
        boolean eliminar(T objeto);
    }
}
