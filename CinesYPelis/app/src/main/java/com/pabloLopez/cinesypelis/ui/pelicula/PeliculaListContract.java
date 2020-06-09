package com.pabloLopez.cinesypelis.ui.pelicula;

import com.pabloLopez.cinesypelis.base.BaseListContract;
import com.pabloLopez.cinesypelis.data.model.Pelicula;

public class PeliculaListContract {
    public interface View extends BaseListContract.View<Pelicula>{
        void setPresenter(Presenter presenter);
    }
    public interface Presenter extends BaseListContract.Presenter<Pelicula> {

    }

}
