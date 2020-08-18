package com.example.fernando.miscontactos_final.modelo;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fernando.miscontactos_final.R;

import java.util.ArrayList;

public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder> {

    ArrayList<Contacto> contactos;
    Activity activity;

    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity activity){
        this.contactos = contactos;
        this.activity = activity;
    }

    @NonNull
    @Override
    //Va darle vida a la layout Cardview que antes de esto esta aislado
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //infla el layout y lo pasa al viewholder para obtener los view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto,parent,false);
        return new ContactoViewHolder(v);// aqui se le pasa el view a la funcion que hara el enlace con las variables
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder contactoViewHolder, int position) {
        //aqui setearemos cada valor de la lista contactos con cada view
        final Contacto contacto = contactos.get(position);
        contactoViewHolder.imgFoto.setImageResource(contacto.getFoto());
        contactoViewHolder.tvNombreCV.setText(contacto.getNombre());
        contactoViewHolder.tvTelefonoCV.setText(contacto.getTelefono());

        contactoViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, contacto.getNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity,DetalleContacto.class);
                intent.putExtra("nombre", contacto.getNombre());
                intent.putExtra("telefono",contacto.getTelefono());
                intent.putExtra("email",contacto.getEmail());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() { //cantidad de Elementos que contiene mi lista
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        //Creamos las variables globales para conectar nuestro nuestro cardview
        //a nuestro Recyclerview
        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvTelefonoCV;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV = (TextView) itemView.findViewById(R.id.tvTelefonoCV);

        }
    }
}
