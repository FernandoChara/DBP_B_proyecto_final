package com.example.fernando.miscontactos_final.controlador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fernando.miscontactos_final.modelo.Contacto;
import com.example.fernando.miscontactos_final.modelo.ContactoAdaptador;
import com.example.fernando.miscontactos_final.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //metodo por el cual el activity se une al layaout.xml

        listaContactos = (RecyclerView)findViewById(R.id.rvContactos);

        GridLayoutManager glm = new GridLayoutManager(this,2);
        glm.setOrientation(GridLayoutManager.VERTICAL);

        listaContactos.setLayoutManager(glm);
        inicializarListaContactos();
        inicializarAdaptador();



        /* Luego realizamos el enlace entre el el ListView de la vista y con una adapter introducimos
           los datos al listview de la vista*/
        /*ListView lstContactos = (ListView) findViewById(R.id.lstContactos);
        lstContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nombresContacto));

        /*Creamos un Listener el cual va estar escuchando si alguien da un clic en la pantalla y a travez de indices
        indicara que elemento de la listview fue clickeada y con un elemento de la clase Intent lanzara el
        detalle del contacto*/
        /*lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
                intent.putExtra(getResources().getString(R.string.pnombre),contactos.get(position).getNombre()); //Enviamos parametros a la Activity
                intent.putExtra(getResources().getString(R.string.ptelefono),contactos.get(position).getTelefono());
                intent.putExtra(getResources().getString(R.string.pemail),contactos.get(position).getEmail());
                startActivity(intent);
                finish();
            }
        });*/

    }

    public void inicializarAdaptador(){
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos,this);
        listaContactos.setAdapter(adaptador);
    }

    public void inicializarListaContactos(){
        contactos = new ArrayList<Contacto>();

        contactos.add(new Contacto(R.drawable.icons8_edit_user_100,"Fernando Chara", "123456789", "fchara@gmail.com"));
        contactos.add(new Contacto(R.drawable.icons8_person_facepalming_96,"Nancy Huaman", "123123789", "nhuaman@gmail.com"));
        contactos.add(new Contacto(R.drawable.icons8_person_pointing_100,"Gonzalo Chara", "123753789", "goz@gmail.com"));
        contactos.add(new Contacto(R.drawable.icons8_person_female_96,"Tomasa Chunga", "123456789", "toma@gmail.com"));
        contactos.add(new Contacto(R.drawable.icons8_dog_64,"Petiza Talula", "13698789", "talu@gmail.com"));

    }
}