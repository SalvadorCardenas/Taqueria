package com.example.taqueria.Base;


import com.example.taqueria.Categoria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoriaDAO {
    ObservableList<Categoria> listCategoria= FXCollections.<Categoria>observableArrayList();
    Connection conn=null;
    public CategoriaDAO(Connection conn) {
        this.conn = conn;
    }

    public ObservableList getAll() {
        listCategoria.clear();
        try{
            if(conn!=null){
                String query = "select * from categoria";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    listCategoria.add(new Categoria(rs.getInt("id_categoria"),rs.getString("nombre")));
                }
            }else {
                System.out.println("No se establecio conexcion");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCategoria;
    }

    public boolean create(String categoria) {
        try{
            if (conn!= null){
                String query = "insert into categoria values(null, '"+categoria+"')";    //QUERY DE INSERTAR
                Statement st = conn.createStatement();
                return st.executeUpdate(query)==1;   // ANOTACION
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean update(int i, String nombre){
        try{
            if (conn!= null){
                String query = "update categoria set nombre='"+nombre+"' where id_categoria="+i+"";       // cambiar por el nombre que ponga el usuario
                Statement st = conn.createStatement();
                return st.executeUpdate(query)==1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean drop(int categoria) {
        try{
            if (conn!= null){
                String query = "delete from categoria where id_categoria="+categoria+"";
                Statement st = conn.createStatement();
                return st.executeUpdate(query)==1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }  // Delete categoria
}