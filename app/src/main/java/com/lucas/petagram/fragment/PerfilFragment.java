package com.lucas.petagram.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.lucas.petagram.CustomItemClickListener;
import com.lucas.petagram.R;
import com.lucas.petagram.adapter.MascotaAdaptador;
import com.lucas.petagram.adapter.PerfilAdaptador;
import com.lucas.petagram.pojo.Mascota;
import com.lucas.petagram.presentador.IRecyclerViewFragmentPresenter;
import com.lucas.petagram.presentador.PerfilFragmentPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IRecyclerViewFragmentView {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private CustomItemClickListener listener;
    private PerfilAdaptador adaptadorPerfil;
    private IRecyclerViewFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        // traigo a objeto recyclerview
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas2);
        presenter = new PerfilFragmentPresenter(this, getContext());

        return v;
    }



    @Override
    public void generarLinearLayout() {
        listaMascotas.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    @Override
    public MascotaAdaptador crearAdaptadorMascota(ArrayList<Mascota> mascotas) {
        return null;
    }

    @Override
    public PerfilAdaptador crearAdaptadorMascotaPerfil(ArrayList<Mascota> mascotas) {
        adaptadorPerfil = new PerfilAdaptador(mascotas);
        return adaptadorPerfil;
    }


    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        //null
    }

    @Override
    public void inicializarAdaptadorPerfilRV(PerfilAdaptador adaptador) {
        listaMascotas.setAdapter(adaptadorPerfil);
    }
}
