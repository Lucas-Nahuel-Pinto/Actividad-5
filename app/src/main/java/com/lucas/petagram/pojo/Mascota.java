package com.lucas.petagram.pojo;

import java.io.Serializable;

public class Mascota implements Serializable{



    private String nombre;
    private int likes;
    private int foto;
    private int id;

    public Mascota(int foto, String nombre, int likes) {
        this.nombre = nombre;
        this.likes = likes;
        this.foto = foto;
    }

    public Mascota() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
