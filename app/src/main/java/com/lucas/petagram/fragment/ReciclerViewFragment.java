package com.lucas.petagram.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import com.lucas.petagram.CustomItemClickListener;
import com.lucas.petagram.R;
import com.lucas.petagram.adapter.MascotaAdaptador;
import com.lucas.petagram.adapter.PerfilAdaptador;
import com.lucas.petagram.db.BaseDatos;
import com.lucas.petagram.db.ConstructorMascotas;
import com.lucas.petagram.pojo.Mascota;
import com.lucas.petagram.presentador.IRecyclerViewFragmentPresenter;
import com.lucas.petagram.presentador.RecyclerViewFragmentPresenter;

public class ReciclerViewFragment extends android.support.v4.app.Fragment implements CustomItemClickListener,IRecyclerViewFragmentView{

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private CustomItemClickListener listener;
    private MascotaAdaptador adaptador;
    private IRecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_reciclerview, container, false);

        // traigo a objeto recyclerview
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        BaseDatos db = new BaseDatos(getContext());
        mascotas = db.obtenerTodosLosContactos();
        return v;
    }


    @Override
    public void likeClicked(View v, int position) {

        // crea una instancia constructorMascotas para dar like y grabarlo
        ConstructorMascotas constructorMascotas = new ConstructorMascotas(getContext());
        constructorMascotas.darLikeMascota(mascotas.get(position));


        Toast.makeText(getContext(), "Diste like a " + mascotas.get(position).getNombre(), Toast.LENGTH_SHORT).show();

        // sumo like y actualizo el valor en adaptador
        mascotas.get(position).setLikes( constructorMascotas.obtenerLikesMascota(mascotas.get(position)));
        adaptador.notifyDataSetChanged();

    }

    @Override
    public void generarLinearLayout() {
        // defino orientacion de recyclerviw
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptadorMascota(ArrayList<Mascota> mascotas) {
        adaptador = new MascotaAdaptador(mascotas, getActivity());
        this.adaptador = adaptador;
        return adaptador;
    }

    @Override
    public PerfilAdaptador crearAdaptadorMascotaPerfil(ArrayList<Mascota> mascotas) {
        return null;
    }


    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
        adaptador.setClickListener(this);
    }

    @Override
    public void inicializarAdaptadorPerfilRV(PerfilAdaptador adaptador) {
        //
    }
}