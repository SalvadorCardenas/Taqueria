package com.example.taqueria;

public class Categoria {
    int id_Categoria;
    String nombre;

    public Categoria(int id_Categoria, String nombre) {
        this.id_Categoria = id_Categoria;
        this.nombre = nombre;
    }

    public Categoria() {
    }

    public int getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
