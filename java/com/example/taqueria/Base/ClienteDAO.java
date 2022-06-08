package com.example.taqueria.Base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAO {
    Connection conn=null;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void crear() {
        int id=0;
        try{
            if(conn!=null){
                String sql = "INSERT INTO cliente VALUE ()";
                Statement stmt=conn.createStatement();
                stmt.execute(sql);
            }else {
                System.out.println("No se establecio conexcion");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int obtenerid() {
        int id=0;
        try{
            if(conn!=null){
                String sql = "select * from cliente";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    id=rs.getInt("id_cliente");
                }
            }else {
                System.out.println("No se establecio conexcion");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
