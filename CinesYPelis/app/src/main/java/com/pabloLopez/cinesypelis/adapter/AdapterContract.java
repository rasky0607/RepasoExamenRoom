package com.pabloLopez.cinesypelis.adapter;

import java.util.List;
/*Interfaz base para todos los adapter de forma general, es decir todos los metodos que por general todos tienen*/
public class AdapterContract {
    public interface BaseAdapterContract<T>{
        void miadd(T objeto);
        void miaddAll(List<T> list);
        T eliminar(int posicion);
        T getItemList(int pos);
    }
}
