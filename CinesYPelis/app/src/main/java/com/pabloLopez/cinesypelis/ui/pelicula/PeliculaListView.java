package com.pabloLopez.cinesypelis.ui.pelicula;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pabloLopez.cinesypelis.R;
import com.pabloLopez.cinesypelis.adapter.PeliculaListAdapter;
import com.pabloLopez.cinesypelis.data.model.Pelicula;

import java.util.List;

public class PeliculaListView extends Fragment implements PeliculaListContract.View{

    public static final String TAG="PeliculaListView";
    PeliculaListContract.Presenter presenter;
    RecyclerView rvPelicula;
    PeliculaListAdapter peliculaListAdapter;
    FloatingActionButton fbadd;
    manipularPelicula manipularPelicula;

    public static PeliculaListView newInstace(){
        PeliculaListView fragment = new  PeliculaListView();
        return fragment;
    }

    //Inflamos la vista
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_view,container,false);
        rvPelicula=view.findViewById(R.id.mirv);//enlazamos el rv
        fbadd=view.findViewById(R.id.fbadd);
        return view;
    }

    //Justo despues de inflar la vista
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fbadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manipularPelicula.addOeditarPelicula(null);
            }
        });
        inicializarAdapter();
        presenter.cargarDatos();
    }

    public void inicializarAdapter(){
        peliculaListAdapter=new PeliculaListAdapter(new PeliculaListAdapter.manipularDatos() {
            @Override
            public void milongClick(int pos, Pelicula pelicula) //Borra elementos
            {
                //Ventana de confirmacion de borrado de un elemento
                AlertDialog alerta = new AlertDialog.Builder(getContext())
                        .setMessage("¿Estas seguro de eliminar la pelicula '"+pelicula.getNombre()+"'?")
                        .setTitle("Advertencia.")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.eliminar(pelicula);
                                peliculaListAdapter.eliminar(pos);

                                //Deshacer un borrado
                                Snackbar snackbar=Snackbar.make(getActivity().findViewById(R.id.micontenedor),"Deshacer eliminacion de pelicula '"+pelicula.getNombre()+"'.",7000);
                                snackbar.setActionTextColor(Color.WHITE);
                                snackbar.show();
                                snackbar.setAction("Deshacer", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.insertar(pelicula);
                                        peliculaListAdapter.anadirEnPosicion(pos,pelicula);
                                    }
                                });
                            }
                        }).setNegativeButton("No",null).create();
                alerta.show();


            }

            @Override
            public void miClick(Pelicula pelicula) //Edita elementos
            {
                manipularPelicula.addOeditarPelicula(pelicula);
            }
        });

        rvPelicula.setAdapter(peliculaListAdapter);
        rvPelicula.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    //cuando la vista deja de estar en 2º plano o parada
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Listado de peliculas");
    }

    //Justo trans vincularse la vista con la ActivityMain
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        manipularPelicula =(manipularPelicula)getContext();//pasamos el contexto a este listener para darle memoria
    }

    //Justo cuando se desvincula la vista de la ActivityMain
    @Override
    public void onDetach() {
        super.onDetach();
        manipularPelicula =null;

    }

    //region Metodos implementados por la interfaz
    @Override
    public void setPresenter(PeliculaListContract.Presenter presenter) {
        this.presenter=presenter;
    }


    @Override
    public void hayDatos(List list) {
        peliculaListAdapter.miaddAll(list);
        peliculaListAdapter.notifyDataSetChanged();
    }

    @Override
    public void mensaje(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mesajeError(String msg) {
        Toast.makeText(getContext(),"ERROR: "+msg,Toast.LENGTH_SHORT).show();
    }
    //endregion

    //Interfaz para cuando se pusa el boton añadir en esta vista que abra una nuevo fragment la clase MainActivity
    public interface manipularPelicula {
        public void addOeditarPelicula(Pelicula pelicula);
    }
}
