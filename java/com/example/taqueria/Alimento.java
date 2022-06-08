package com.example.taqueria;

public class Alimento {
    int id_alimento;
    String nombre;
    String precio;
    int id_Categoria;
    String imagen;

    public Alimento(int id_Alimento, String nombre, String precio, int id_Categoria, String imagen) {
        this.id_alimento = id_Alimento;
        this.nombre = nombre;
        this.precio = precio;
        this.id_Categoria = id_Categoria;
        this.imagen = imagen;
    }

    public int getId_Alimento() {
        return id_alimento;
    }

    public void setId_Alimento(int id_Alimento) {
        this.id_alimento = id_Alimento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}
