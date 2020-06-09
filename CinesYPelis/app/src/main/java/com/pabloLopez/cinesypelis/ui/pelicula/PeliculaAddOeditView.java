package com.pabloLopez.cinesypelis.ui.pelicula;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.pabloLopez.cinesypelis.MainActivity.MainActivity;
import com.pabloLopez.cinesypelis.R;
import com.pabloLopez.cinesypelis.data.dao.CinesYPeliculasApplication;
import com.pabloLopez.cinesypelis.data.model.Pelicula;

import java.util.Random;

public class PeliculaAddOeditView extends Fragment implements PeliculaAddOeditContract.View {
    public static final String TAG="PeliculaAddOeditView";
    PeliculaAddOeditContract.Presenter presenter;
    TextInputEditText tenombrePelicula;
    TextInputEditText tegeneroPelicula;
    TextInputEditText teduracionPelicula;
    Button btnGuardarPelicula;

    public static PeliculaAddOeditView newInstace(Bundle args){
        PeliculaAddOeditView fragment = new  PeliculaAddOeditView();
        if(args!=null)
            fragment.setArguments(args);//Recogemos el bundle  creando un nuevo objeto con lo que nos llega
        return fragment;
    }

    //Inflamos la vista
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_edit_pelicula,container,false);
        //Enlazamos componentes d ela vista
        tenombrePelicula=view.findViewById(R.id.tenombrePelicula);
        tegeneroPelicula=view.findViewById(R.id.tegeneroPelicula);
        teduracionPelicula=view.findViewById(R.id.teduracionPelicula);
        btnGuardarPelicula=view.findViewById(R.id.btnGuardarPelicula);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Si el argumento es distinto de nuelo es por que e suna edicion y colocamos los datos en los campos.
        if(getArguments()!=null)
        {
            Pelicula p =getArguments().getParcelable(Pelicula.TAG);
            tenombrePelicula.setText(p.getNombre());
            tegeneroPelicula.setText(p.getGenero());
            teduracionPelicula.setText(Double.toString(p.getDuracion()));
        }
        //Guardamos el registro
        btnGuardarPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getArguments()!=null)
                    presenter.modificar(getObjeto());
                else
                    presenter.anadir(getObjeto());

                Notificar();
                volverFragmentAnterior();
            }
        });
    }

    //Notificamos que se añadio o edito una pelicula nueva
    public void Notificar(){
        //Cuando pulse sobre la notificación se debe abrir la vista que gestiona los fragment y que posteriormente redirigirar a esta de editaro añadir
        Intent intent = new Intent(getContext(), MainActivity.class);//MainActivity clase donde esta el OnResum que controla la notificacion
        intent.putExtra("NOTIFICACION", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //Se crea una nueva tarea con esa Activity. Recrea de nuevo
        Bundle bundle = new Bundle();
        bundle.putParcelable(Pelicula.TAG, getObjeto());
        intent.putExtras(bundle);

        //Un PendingIntent tiene un objeto Intent en su interior que define lo que se quiere ejecutar cuando se pulse
        //Se crea el objeto PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //se crea la notificacion que cargara el PendingIntent
        Notification.Builder builder = new Notification.Builder(getContext(), CinesYPeliculasApplication.CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_miadd_foreground)
                .setContentText("Nueva pelicula '"+getObjeto().getNombre()+"'")
                .setContentTitle("CinesYPeliculas")
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        notificationManagerCompat.notify(new Random().nextInt(100), builder.build());
    }
    public void volverFragmentAnterior(){
        getActivity().onBackPressed();
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
        getActivity().setTitle("Añadir/Editar pelicula");
    }

    //region Implementado por la interfaz PeliculaAddOeditContract.View
    @Override
    public void setPresenter(PeliculaAddOeditContract.Presenter presenter) {
        this.presenter=presenter;
    }

    //Recoge los dato sdel objeto pelicula
    @Override
    public Pelicula getObjeto() {
        Pelicula p = new Pelicula();
        p.setNombre(tenombrePelicula.getText().toString());
        p.setGenero(tegeneroPelicula.getText().toString());
        p.setDuracion(Double.parseDouble(teduracionPelicula.getText().toString()));
        return p;
    }

    //Comprobamos los datos que nos han llegado de los componentes de la vista
    @Override
    public boolean esValido() {
        if(tenombrePelicula.getText().toString().isEmpty()) {
            mensajeError("El nombre de la pelicula no puede estar vacio.");
            return false;
        }
        if(tegeneroPelicula.getText().toString().isEmpty()) {
            mensajeError("El genero de la pelicula no puede estar vacio.");
            return false;
        }

        if(teduracionPelicula.getText().toString().isEmpty()) {
            mensajeError("El genero de la durcion de la pelicula no puede estar vacia.");
            return false;
        }

        return true;
    }

    @Override
    public void mensaje(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void mensajeError(String msg) {
        Toast.makeText(getContext(),"ERROR: "+msg,Toast.LENGTH_LONG).show();
    }
    //endregion
}
