package com.pabloLopez.cinesypelis.base;
/*Contrato base creado para las ediciones y iserciones nuevas en los repositorios*/
public class BaseAddOeditContract {
    public interface View<T>{
        T getObjeto();//Obtiene el objeto creado en la ventana de añadir
        boolean esValido();//comprueba que los datos introducidos son validos antes de insertar (los campos)
        void mensaje(String msg);
        void mensajeError(String msg);
    }

    public interface Presenter<T>{
        void anadir(T objeto);
        void modificar(T objeto);
        boolean validar();//Indica a la vista si los datos a introducir son validos o no
    }
}
