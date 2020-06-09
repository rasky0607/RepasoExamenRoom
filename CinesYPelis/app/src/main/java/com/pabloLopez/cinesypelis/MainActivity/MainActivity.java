package com.pabloLopez.cinesypelis.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pabloLopez.cinesypelis.R;
import com.pabloLopez.cinesypelis.data.model.Pelicula;
import com.pabloLopez.cinesypelis.ui.pelicula.PeliculaAddOeditView;
import com.pabloLopez.cinesypelis.ui.pelicula.PeliculaListPresenter;
import com.pabloLopez.cinesypelis.ui.pelicula.PeliculaListView;
import com.pabloLopez.cinesypelis.ui.pelicula.PeliculasAddOeditPresenter;

public class MainActivity extends AppCompatActivity implements PeliculaListView.manipularPelicula {

    //Fragment y presenter de dichos fragment que gestiona esta clase
    PeliculaListView fragmentPeliculaListView;
    PeliculaListPresenter peliculaListPresenter;
    PeliculaAddOeditView fragmentPeliculaAddOeditView;
    PeliculasAddOeditPresenter peliculasAddOeditPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
    }

    //Inicializamos la aplicacion mostrando en primer lugar esta vista de listado de peliculas "PeliculaListView"
    public void inicializar(){
        fragmentPeliculaListView=(PeliculaListView)getSupportFragmentManager().findFragmentByTag(PeliculaListView.TAG);
        if(fragmentPeliculaListView==null){
            fragmentPeliculaListView=PeliculaListView.newInstace();
            getSupportFragmentManager().beginTransaction().replace(R.id.micontenedor,fragmentPeliculaListView,PeliculaListView.TAG).addToBackStack(null).commit();
        }
        peliculaListPresenter=new PeliculaListPresenter(fragmentPeliculaListView);
        fragmentPeliculaListView.setPresenter(peliculaListPresenter);
        setTitle("Listado de peliculas");
    }

    /*Abrimos el fragment de Añadir peliculas
     [Si pelicula es igual a NULL es una insercion, si es disitnto de NULL una edicion]*/
    @Override
    public void addOeditarPelicula(Pelicula pelicula) {
        fragmentPeliculaAddOeditView=(PeliculaAddOeditView) getSupportFragmentManager().findFragmentByTag(PeliculaAddOeditView.TAG);
        if(fragmentPeliculaAddOeditView==null){
            Bundle bundle=null;
            if(pelicula!=null)//Si es dintinto de null parseamos el objeto que llega
            {
                bundle = new Bundle();
                bundle.putParcelable(Pelicula.TAG,pelicula);
            }
            fragmentPeliculaAddOeditView=PeliculaAddOeditView.newInstace(bundle);//pasamos el bundle a la vista
            getSupportFragmentManager().beginTransaction().replace(R.id.micontenedor,fragmentPeliculaAddOeditView,fragmentPeliculaAddOeditView.TAG).addToBackStack(null).commit();
        }
        peliculasAddOeditPresenter=new PeliculasAddOeditPresenter(fragmentPeliculaAddOeditView);
        fragmentPeliculaAddOeditView.setPresenter(peliculasAddOeditPresenter);
        setTitle("Añadir/Editar pelicula");
    }

    //Tras volver esta actividad de estar en segundo plano o parada
    /*Usamos este metodo para comprobar si el usuario pulso sobre
     una notificacion y redirigirlo hacia la edicion del elemento que se notifico*/
    @Override
    protected void onResume() {
        super.onResume();
        //Si el FLAG NOTIFICATION = true redirigira a la vista de edicion con los datos del objeto que hay dentro del PendingIntent de la notificacion
        if(getIntent().getBooleanExtra("NOTIFICACION",false))
            addOeditarPelicula(getIntent().getParcelableExtra(Pelicula.TAG));

    }
}
