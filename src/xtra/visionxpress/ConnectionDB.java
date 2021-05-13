/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtra.visionxpress;

/**
 *
 * @author Claudinea de Almeida
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDB {

//
//    public static final String DRIVER="com.mysql.jdbc.Driver";
//    //public static final String DRIVER="com.mysql.cj.jdbc.Driver";
//    public static final String URL="jdbc:mysql://localhost:3306/xtravisio";
//    public static final String USER="root";
//    public static final String PASS="Cct2021*";
    
  //  public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String URL="jdbc:mysql://127.0.0.1:3306/xtravisio?serverTimeZone=UTC";
    public static final String USER="root";
    public static final String PASS="Cct2021*";
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Connection error", ex);
        }
    }
    
    public static void CloseConnection(Connection con){
        if (con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.print("Error: "+ ex);
            }
            
        }
    }
    
    public static void CloseConnection(Connection con, PreparedStatement stm){
        if (stm != null){
            try {
                stm.close();
            } catch (SQLException ex) {
                 System.err.print("Error: "+ ex);
            }
            
        }
        
        CloseConnection(con);
    }
    
    public static void CloseConnection(Connection con, PreparedStatement stm, ResultSet res){
        if (res != null){
            try {
                res.close();
            } catch (SQLException ex) {
                 System.err.print("Error: "+ ex);
            }
            
        }
        
        CloseConnection(con,stm);
    }
    
    
}


