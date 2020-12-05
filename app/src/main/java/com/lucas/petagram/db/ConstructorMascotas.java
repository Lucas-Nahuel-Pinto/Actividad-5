package com.lucas.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import com.lucas.petagram.R;
import com.lucas.petagram.pojo.Mascota;

public class ConstructorMascotas {

    private Context context;
    private static final int LIKE = 1;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){

        // creo arraylist
        ArrayList<Mascota> mascotasCargadas = new ArrayList<Mascota>();
        // instancia de base de datos
        BaseDatos db = new BaseDatos(context);
        // obtengo las mascotas cargadas
        mascotasCargadas = db.obtenerTodosLosContactos();
        // si la base de datos esta vacia se deben cargar - SOLO SUCEDE EN PRIMERA EJECUCION
        if( mascotasCargadas.size() == 0) {
            insertarOchoMascotas(db);
            mascotasCargadas = db.obtenerTodosLosContactos();
        }
        return mascotasCargadas;
    }

    public void insertarOchoMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NAME, "Juancito");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_PHOTO, R.drawable.puppy1);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NAME, "Pedrito");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_PHOTO, R.drawable.puppy2);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NAME, "Ramon");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_PHOTO, R.drawable.puppy3);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NAME, "Rodolfo");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_PHOTO, R.drawable.puppy4);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NAME, "Bruno");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_PHOTO, R.drawable.puppy3);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NAME, "Tito");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_PHOTO, R.drawable.puppy3);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NAME, "Indio");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_PHOTO, R.drawable.puppy4);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NAME, "Gringo");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_PHOTO, R.drawable.puppy3);
        db.insertarMascota(contentValues);


    }

    public ArrayList<Mascota> obtenerDatosPerfil(){

        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.perfil_perro, "Oso", 2));
        mascotas.add(new Mascota(R.drawable.perfil_perro, "Milton", 5));
        mascotas.add(new Mascota(R.drawable.perfil_perro, "Blacky", 4));
        mascotas.add(new Mascota(R.drawable.perfil_perro, "Lucky", 1));
        mascotas.add(new Mascota(R.drawable.perfil_perro, "Tom", 6));
        mascotas.add(new Mascota(R.drawable.perfil_perro, "Milton", 5));
        mascotas.add(new Mascota(R.drawable.perfil_perro, "Blacky", 4));
        mascotas.add(new Mascota(R.drawable.perfil_perro, "Lucky", 1));
        mascotas.add(new Mascota(R.drawable.perfil_perro, "Tom", 6));

        return mascotas;
    }

    public void darLikeMascota(Mascota mascota)
    {
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_PETS_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_PETS_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota)
    {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }
}
