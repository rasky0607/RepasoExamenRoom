package com.pabloLopez.cinesypelis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pabloLopez.cinesypelis.R;
import com.pabloLopez.cinesypelis.data.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class PeliculaListAdapter extends RecyclerView.Adapter<PeliculaListAdapter.ViewHolder> implements AdapterContract.BaseAdapterContract<Pelicula> {

    List<Pelicula>lista;
    manipularDatos manipularDatos;
    public PeliculaListAdapter(manipularDatos manipularDatos){
        lista=new ArrayList<>();
        this.manipularDatos=manipularDatos;
    }

    //Inflamos la vista
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_peliculas, parent, false);
        return new ViewHolder(v);
    }

    //Enlazamo datos con la vista
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvnombrePeli.setText("Nombre: "+lista.get(position).getNombre());
        holder.tvgenero.setText("Genero: "+lista.get(position).getGenero());
        holder.tvduracion.setText("Duracion: "+Double.toString(lista.get(position).getDuracion())+"h");

        //cuando hace pulsacion corta sobre un elemento
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pelicula pelicula=lista.get(holder.getAdapterPosition());
                manipularDatos.miClick(pelicula);
            }
        });

        //cuando hace pulsacion larga sobr eun elemento
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Pelicula pelicula=lista.get(holder.getAdapterPosition());
                manipularDatos.milongClick(holder.getAdapterPosition(),pelicula);
                return true;
            }
        });
    }
    //Devuelve el tamaño de la lista
    @Override
    public int getItemCount() {
        return lista.size();
    }

    //region implementado por la interfaz AdapterContract.BaseAdapterContract<Pelicula>
    @Override
    public void miadd(Pelicula objeto) {
        this.lista.add(objeto);
    }

    @Override
    public void miaddAll(List<Pelicula> list) {
        this.lista.addAll(list);
    }

    //Reinserta un elemento en una posicion determinada del adapter
    public void anadirEnPosicion(int pos,Pelicula pelicula)
    {
        lista.add(pos,pelicula);
       notifyDataSetChanged();//Notificamos los cambios
    }
    @Override
    public Pelicula eliminar(int posicion) {
        Pelicula p=lista.get(posicion);
        lista.remove(posicion);
        notifyItemRemoved(posicion);//notificamos que se elimino un elemento de una posicion
        return p;
    }

    @Override
    public Pelicula getItemList(int pos) {
        Pelicula p = lista.get(pos);
        return p;
    }
    //endregion

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvnombrePeli;
        TextView tvgenero;
        TextView tvduracion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnombrePeli=itemView.findViewById(R.id.tvnombrePeli);//enlazamos componentes de la vista de item_list_peliculas
            tvgenero=itemView.findViewById(R.id.tvgenero);
            tvduracion=itemView.findViewById(R.id.tvduracion);
        }
    }

    //Interfaz de Manipulacion de datos de el reciclerview para detectar el click o el onLongClick
    public interface manipularDatos{
        void milongClick(int pos,Pelicula pelicula);
        void miClick(Pelicula pelicula);
    }
}
