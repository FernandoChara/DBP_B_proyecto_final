package com.example.fernando.miscontactos_final.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.fernando.miscontactos_final.modelo.Contacto;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;
    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_CONTACTS + "(" +
                ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE + " TEXT PRIMARY KEY," +
                ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO + " TEXT, " +
                ConstantesBaseDatos.TABLE_CONTACTS_EMAIL + " TEXT, " +
                ConstantesBaseDatos.TABLE_CONTACTS_FOTO + " INTEGER" +
                ")";
        db.execSQL(queryCrearTablaContacto);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_CONTACTS);
        onCreate(db);
    }

    public ArrayList<Contacto> obtenerTodosLosContactos() {
        ArrayList<Contacto> contactos = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Contacto contactoActual = new Contacto();
            contactoActual.setNombre(registros.getString(0));
            contactoActual.setTelefono(registros.getString(1));
            contactoActual.setEmail(registros.getString(2));
            contactoActual.setFoto(registros.getInt(3));

            contactos.add(contactoActual);

        }

        db.close();

        return contactos;
    }

    public void insertarContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_CONTACTS,null, contentValues);
        db.close();
    }

}
