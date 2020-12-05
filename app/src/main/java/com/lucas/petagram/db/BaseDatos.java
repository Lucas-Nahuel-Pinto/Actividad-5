package com.lucas.petagram.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.lucas.petagram.pojo.Mascota;


public class BaseDatos extends SQLiteOpenHelper{

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creamos estructura
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_PETS + "(" +
                                        ConstantesBaseDatos.TABLE_PETS_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        ConstantesBaseDatos.TABLE_PETS_NAME       + " TEXT, "     +
                                        ConstantesBaseDatos.TABLE_PETS_PHOTO    + " INTEGER"    +
                                        ")";
        String queryCrearTablaLikesMascota = " CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_PETS + "(" +
                                        ConstantesBaseDatos.TABLE_LIKES_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        ConstantesBaseDatos.TABLE_LIKES_PETS_ID_MASCOTA + " INTEGER, " +
                                        ConstantesBaseDatos.TABLE_LIKES_PETS_LIKES + " INTEGER, " +
                                        "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_PETS_ID_MASCOTA + ") " +
                                        "REFERENCES " + ConstantesBaseDatos.TABLE_PETS + "(" + ConstantesBaseDatos.TABLE_PETS_ID + ")" +
                                        ")";
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES_PETS);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodosLosContactos(){
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while( registros.moveToNext() )
        {
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0  ));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_PETS, null, contentValues );
        db.close();

    }

    public void insertarLikeMascota(ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_PETS, null , contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota)
    {
        int likes=0;
        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_PETS_LIKES+")" +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_PETS +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_PETS_ID_MASCOTA + "=" +
                mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while( registros.moveToNext())
        {
            likes = registros.getInt(0);
        }
        db.close();

        return likes;
    }
}
