package com.lucas.petagram.presentador;

import android.content.Context;

import java.util.ArrayList;

import com.lucas.petagram.Contacto;
import com.lucas.petagram.db.ConstructorMascotas;
import com.lucas.petagram.fragment.IRecyclerViewFragmentView;
import com.lucas.petagram.pojo.Mascota;

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter{

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerContactosBaseDatos();
    }

    @Override
    public void obtenerContactosBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void mostrarContactosRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptadorMascota(mascotas));
        iRecyclerViewFragmentView.generarLinearLayout();
    }
}
