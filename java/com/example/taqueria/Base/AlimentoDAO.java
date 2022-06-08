package com.example.taqueria.Base;

import com.example.taqueria.Alimento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlimentoDAO {
    Connection conn=null;
    ObservableList<Alimento> listAlimentos = FXCollections.observableArrayList();    //LISTA DE ALIMENTOS
    ObservableList<Alimento> listOrden = FXCollections.observableArrayList();       //lISTA DE ORDENES
    public AlimentoDAO(Connection conn) {
        this.conn = conn;
    }

    public ObservableList mostrar(int i) {
        listAlimentos.clear();          //VACIAR LA LISTA
        try{
            if(conn!=null){
                String query = "select * from alimento WHERE id_Categoria='"+i+"'";        // TODOS LOS ALIMENTOS QUE SEAN DE TAL CATEGORIA
                Statement st = conn.createStatement();              // CREAR STATEMENT
                ResultSet rs = st.executeQuery(query);        // EJECUTAR LA LINEA DE SQL
                while (rs.next()) {
                    listAlimentos.add(new Alimento(rs.getInt("id_Alimento"),rs.getString("nombre"),rs.getString("precio"),rs.getInt("id_Categoria"),rs.getString("imagen")));
                }
            }else {
                System.out.println("No se establecio conexcion");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listAlimentos;   // REGRESA LA TABLA
    }

    public ObservableList Insert(int id_alimento, int id_cliente) {
        try{
            if(conn!=null){
                String sql = "SELECT * FROM alimento WHERE id_alimento='"+id_alimento+"'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    listOrden.add(new Alimento(rs.getInt("id_alimento"),rs.getString("nombre"),rs.getString("precio"),rs.getInt("id_Categoria"),rs.getString("imagen")));
                }
                String query = "INSERT INTO orden(id_alimento,id_cliente) VALUES('"+id_alimento+"','"+id_cliente+"')";    // INSERTAR NUEVA ORDEN EN BD
                Statement stmt=conn.createStatement();
                stmt.execute(query);
            }else {
                System.out.println("No se establecio conexcion");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOrden;
    }

    public int pagar(int id_cliente) {
        int pagar=0;
        try{
            if(conn!=null){
                String sql = "SELECT * FROM orden WHERE id_cliente='"+id_cliente+"'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    String query = "select * from alimento WHERE id_alimento='"+rs.getInt("id_alimento")+"'";
                    Statement st2 = conn.createStatement();
                    ResultSet rs2 = st2.executeQuery(query);
                    while (rs2.next()){
                        pagar+=Integer.parseInt(rs2.getString("precio"));
                    }
                }
            }else {
                System.out.println("No se establecio conexcion");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pagar;
    }    //REGRESA EL TOTAL A PAGAR

    public void borrar(){                // BORRAR LO ULTIMO QUE SE HA PEDIDO
        String query = "delete from orden order by id_orden desc limit 1";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            listOrden.remove(listOrden.size()-1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean create(String nombre, String precio, int categoria, String imagen){
        try{
            if (conn!= null){
                String query = "insert into alimento values(null,'"+nombre+"','"+precio+"','"+categoria+"','"+imagen+"')";
                Statement st = conn.createStatement();
                return st.executeUpdate(query)==1;
            }
        } catch (SQLException e) {
            System.out.println("No se establecio conexcion");
        }
        return false;
    }

    public boolean update(int id, String nombre, String precio, String imagen){
        try{
            if (conn!= null){
                String query = "update alimento set nombre='"+nombre+"',precio='"+precio+"',imagen='"+imagen+"' where id_alimento='"+id+"'";
                Statement st = conn.createStatement();
                return st.executeUpdate(query)==1;
            }
        } catch (SQLException e) {
            System.out.println("No se establecio conexcion");
        }
        return false;
    }

    public boolean drop(int id_alimento) {
        try{
            if (conn!= null){
                String query = "delete from alimento where id_alimento="+id_alimento+"";
                Statement st = conn.createStatement();
                return st.executeUpdate(query)==1;
            }
        } catch (SQLException e) {
            System.out.println("No se establecio conexcion");
        }
        return false;
    }
}
