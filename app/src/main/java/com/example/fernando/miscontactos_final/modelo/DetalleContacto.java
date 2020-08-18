package com.example.fernando.miscontactos_final.modelo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.fernando.miscontactos_final.R;
import com.example.fernando.miscontactos_final.controlador.MainActivity;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvNombre, tvTelefono, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        //Recibo los parametros que la otra activity envia con Intent con la clase Bundle
        Bundle parametros = getIntent().getExtras();

        //Aqui recupero cada elemento enviado y lo guardo en una variable
        String nombre = parametros.getString(getResources().getString(R.string.pnombre)); //nombre
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono)); //telefono
        String email = parametros.getString(getResources().getString(R.string.pemail)); //email

        //Realizo un enlace de variables del mismo tipo(con un cast) a la vista xml
        tvNombre = (TextView)findViewById(R.id.tvNombre);
        tvTelefono = (TextView)findViewById(R.id.tvTelefono);
        tvEmail = (TextView)findViewById(R.id.tvEmail);

        //Asignos a cada texview enlazada el dato recibido de la otra Activity
        tvNombre.setText((nombre));
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
    }

    public void llamar(View v){
        String telefono = tvTelefono.getText().toString(); //capturo el telefono en una variable local
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telefono))); // Lanzo una accion externa (Intent implicito)
        // al cual envio un numero accesible a travez de la clase Uri con quien ralizamos un parse
    }

    public void enviarMail(View v){
        String email = tvEmail.getText().toString(); //capturo el email en una variable local
        Intent emailIntent = new Intent(Intent.ACTION_SEND); //Intent impĺicto que llama a la funcion Enviar correo
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL,email);//captura el email para ponerlo como destinatario
        emailIntent.setType("message/rfc822"); //Acceso a las aplicaciones de correo
        startActivity(Intent.createChooser(emailIntent, "Email"));

    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DetalleContacto.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode,event);
    }
}