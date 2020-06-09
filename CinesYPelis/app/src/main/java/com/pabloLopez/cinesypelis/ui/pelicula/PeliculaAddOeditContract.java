package com.pabloLopez.cinesypelis.ui.pelicula;

import com.pabloLopez.cinesypelis.base.BaseAddOeditContract;
import com.pabloLopez.cinesypelis.data.model.Pelicula;

public class PeliculaAddOeditContract {
    public interface View extends BaseAddOeditContract.View<Pelicula>{
       void setPresenter(Presenter presenter);
    }
    public interface Presenter extends BaseAddOeditContract.Presenter<Pelicula>{

    }
}
