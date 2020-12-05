package com.lucas.petagram.fragment;

import java.util.ArrayList;

import com.lucas.petagram.adapter.MascotaAdaptador;
import com.lucas.petagram.adapter.PerfilAdaptador;
import com.lucas.petagram.pojo.Mascota;

public interface IRecyclerViewFragmentView {
    public void generarLinearLayout();
    public MascotaAdaptador crearAdaptadorMascota(ArrayList<Mascota> mascotas);
    public PerfilAdaptador crearAdaptadorMascotaPerfil(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
    public void inicializarAdaptadorPerfilRV(PerfilAdaptador adaptador);
}
