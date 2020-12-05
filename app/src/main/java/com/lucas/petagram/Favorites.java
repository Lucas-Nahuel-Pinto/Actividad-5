package com.lucas.petagram;

import android.media.MediaActionSound;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;

import com.lucas.petagram.adapter.MascotaAdaptador;
import com.lucas.petagram.db.BaseDatos;
import com.lucas.petagram.db.ConstructorMascotas;
import com.lucas.petagram.pojo.Mascota;
import com.lucas.petagram.presentador.IRecyclerViewFragmentPresenter;


public class Favorites extends AppCompatActivity implements IRecyclerViewFragmentPresenter{

    private ArrayList<Mascota> mascotas;
    private ArrayList<Mascota> cincoMascotasFav;
    private RecyclerView recyclerViewMascotasFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_layout);

        recyclerViewMascotasFav = (RecyclerView) findViewById(R.id.rvMascotasFav);

        android.support.v7.widget.Toolbar miActionBar = (android.support.v7.widget.Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        int n,m=0,masAlto=0,max=0,likes=0;
        BaseDatos db = new BaseDatos(this);
        mascotas = db.obtenerTodosLosContactos();

        cincoMascotasFav = new ArrayList<Mascota>();

        // tenemos la arraylist, ahora debemos seleccionar los 5 mas votados


        for(n=0 ; n<5; n++)
        {
            for(m=0 ; m< mascotas.size() ; m++)
            {
                mascotas.get(m).setLikes(db.obtenerLikesMascota(mascotas.get(m)));
                if(  mascotas.get(m).getLikes() > max)
                {
                    max = mascotas.get(m).getLikes();
                    masAlto = m;
                }
            }
            cincoMascotasFav.add(mascotas.get(masAlto));
            mascotas.remove(masAlto);
            max=0;
        }

        obtenerContactosBaseDatos();
    }



    @Override
    public void obtenerContactosBaseDatos() {
        mostrarContactosRV();
    }

    @Override
    public void mostrarContactosRV() {
        recyclerViewMascotasFav.setAdapter(new MascotaAdaptador(cincoMascotasFav, getBaseContext()));
        LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMascotasFav.setLayoutManager(llm);
    }
}
