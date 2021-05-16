/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtra.visionxpress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CRUD
 * @author Claudinea de Almeida
 */

public class RentDAO {
    

    private Connection con = null;
   
    /**
     * Method to save the rent into the database
     */
    public boolean Save(Rent rent, Movie movie, Payment payment){

        //connecting to the database
        con = ConnectionDB.getConnection();
            
        boolean returning=false;
        
        //sql query
        String sql="INSERT INTO rent (datarent,datareturn,movieid,paymentid,quantity,returned) values (?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        
        try {
                             
           //prepare statement to execute the sql in the MySQL 
            stmt=con.prepareStatement(sql);
            stmt.setDate(1, rent.getDatarent());
            stmt.setString(2, rent.getDatareturn());
            stmt.setInt(3, rent.getMovie().getMovieid());
            stmt.setInt(4, rent.getPayment().getPaymentid());
            stmt.setInt(5, rent.getQuantity());
            stmt.setBoolean(6, false);
            
            //execute the sql statement
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Saved sucessfuly");
            returning= true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error. It was not saved sucessfuly"+ex);
            returning= false;
        } finally{
            ConnectionDB.CloseConnection(con, stmt);
        }
        
        return returning;
    }
    
    /**
     * Method to list all the rent registered into the database
     */
    public List<Rent> read(){
        
        //connecting to the database
        con = ConnectionDB.getConnection();
        
        //sql query
        String sql="Select * from rent";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Rent> rents = new ArrayList();
        try {
            //prepare statement to execute the sql in the MySQL 
            stmt=con.prepareStatement(sql);
            
            //execute the sql statement
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Rent rent = new Rent();
                
                rent.setRentid(rs.getInt("rentid"));
                rent.setDatarent(rs.getDate("datarent"));
                rent.setDatareturn(rs.getString("datareturn"));
                rent.setQuantity(rs.getInt("quantity"));
                rent.setReturned(rs.getBoolean("returned"));
                
                
      
                
                rents.add(rent);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  rents;
        
        
    }
    
    
     /**
     * Method to update the rent into the database
     */
    public boolean update(Rent rent){

        //connecting to the database   
        con = ConnectionDB.getConnection();
        //sql query
        String sql="UPDATE rent SET datarent = ?, datareturn =?, movieid =?,paymentid?,quantity?,returned? "
                  +"WHERE rentid=?";
        PreparedStatement stmt = null; 
        
        try {
            
           //prepare statement to execute the sql in the MySQL 
            stmt=con.prepareStatement(sql);
            stmt.setDate(1, rent.getDatarent());
            stmt.setString(2, rent.getDatareturn());
            stmt.setInt(3, rent.getMovie().getMovieid());
            stmt.setInt(4, rent.getPayment().getPaymentid());
            stmt.setInt(5, rent.getQuantity());
            stmt.setBoolean(6, rent.isReturned());
            stmt.setInt(7, rent.getRentid());
            
            //execute the sql statement
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated sucessfuly");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error. It was not updated sucessfuly"+ex);
            return false;
        } finally{
            ConnectionDB.CloseConnection(con, stmt);
        }
        
        
    }
    
    /**
     * Method to search the rent from the database by id
     */
    public List<Rent> search(int id){
        //connecting to the database
        con = ConnectionDB.getConnection();
     
        //sql query
        String sql="Select * from rent WHERE rentid=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Rent> rents = new ArrayList();
        Rent rent1 = new Rent();
        try {
            //prepare statement to execute the sql in the MySQL 
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            //execute the sql statement
            rs = stmt.executeQuery();
            
            while (rs.next()){
               
                //setting the data into the rent instance
                rent1.setRentid(rs.getInt("rentid"));
                rent1.setDatarent(rs.getDate("datarent"));
                rent1.setDatareturn(rs.getString("datareturn"));
                rent1.setQuantity(rs.getInt("quantity"));
                rent1.setReturned(rs.getBoolean("returned"));
               
                // adding the payment to the list
                rents.add(rent1);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  rents;
        
        
    }
    
    /**
     * Method to delete the rent from the database by id
     */
     public boolean delete(Rent rent){
         
        //connecting to the database
        con = ConnectionDB.getConnection();
     
        //sql query
        String sql="DELETE FROM rent WHERE rentid=?";
        PreparedStatement stmt = null; 
        
        try {
            //prepare statement to execute the sql in the MySQL 
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, rent.getRentid());
            
            //execute the sql statement
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Deleted sucessfuly");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error. It was not deleted sucessfuly"+ex);
            return false;
        } finally{
            ConnectionDB.CloseConnection(con, stmt);
        }
        
        
    }
   
     
}

  


    

