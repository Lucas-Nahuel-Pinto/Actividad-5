package com.lucas.petagram.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.lucas.petagram.CustomItemClickListener;
import com.lucas.petagram.db.ConstructorMascotas;
import com.lucas.petagram.pojo.Mascota;
import com.lucas.petagram.R;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;
    private CustomItemClickListener clickListener;
    private Context context;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Context context) {

        this.mascotas = mascotas;
        this.context = context;
    }

    public void setClickListener(CustomItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas, parent, false);

        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder mascotaViewHolder, int position) {   // obtiene objeto para reciclaje
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());
        ConstructorMascotas constructorMascotas = new ConstructorMascotas(context);

        mascotaViewHolder.tvLikes.setText(Integer.toString(constructorMascotas.obtenerLikesMascota(mascotas.get(position))));

    }

    @Override
    public int getItemCount() {         // cantidad de elemento de la lista
        return mascotas.size();
    }


    public class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvNombre;
        private ImageView imgHueso1;
        private TextView tvLikes;

        public MascotaViewHolder(final View itemView) {
            super(itemView);

            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            imgHueso1 = (ImageView) itemView.findViewById(R.id.imgHueso1);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);
            imgHueso1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.likeClicked(view, getAdapterPosition());
                    }
                }
            });

        }


    }

}