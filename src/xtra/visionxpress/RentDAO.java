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
   
    
    public boolean Save(Rent rent){

        con = ConnectionDB.getConnection();
            
        boolean returning=false;
        
        String sql="INSERT INTO rent (rentid,datarent,datareturn,movieid,paymentid,quantity,returned) values (?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        
        try {
                             
                      
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, rent.getRentid());
            stmt.setDate(2, rent.getDatarent());
            stmt.setDate(3, rent.getDatareturn());
            stmt.setInt(4, rent.getMovie().getMovieid());
            stmt.setInt(5, rent.getPayment().getPaymentid());
            stmt.setInt(6, rent.getQuantity());
            stmt.setBoolean(7, rent.isReturned());
            
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
    
    
    public List<Rent> read(){
        con = ConnectionDB.getConnection();
     
        String sql="Select * from rent";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Rent> rents = new ArrayList();
        try {
            stmt=con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Rent rent = new Rent();
                
                rent.setRentid(rs.getInt("rentid"));
                rent.setDatarent(rs.getDate("datarent"));
                rent.setDatareturn(rs.getDate("datareturn"));
              //  rent.setMovie(rs.getObject("movieid"));
              //  rent.setPayment(rs.getObject("paymentid"));
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
    
    
    
    public boolean update(Rent rent){

          
        con = ConnectionDB.getConnection();
     
        String sql="UPDATE rent SET datarent = ?, datareturn =?, movieid =?,paymentid?,quantity?,returned? "
                  +"WHERE rentid=?";
        PreparedStatement stmt = null; 
        
        try {
            
           
            stmt=con.prepareStatement(sql);
            stmt.setDate(1, rent.getDatarent());
            stmt.setDate(2, rent.getDatareturn());
            stmt.setInt(3, rent.getMovie().getMovieid());
            stmt.setInt(4, rent.getPayment().getPaymentid());
            stmt.setInt(5, rent.getQuantity());
            stmt.setBoolean(6, rent.isReturned());
            stmt.setInt(7, rent.getRentid());
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
    
    
    public List<Rent> search(int id){
        con = ConnectionDB.getConnection();
     
        String sql="Select * from rent WHERE rentid=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Rent> rents = new ArrayList();
        Rent rent1 = new Rent();
        try {
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()){
               
                
                rent1.setRentid(rs.getInt("rentid"));
                rent1.setDatarent(rs.getDate("datarent"));
                rent1.setDatareturn(rs.getDate("datareturn"));
              //  rent1.setMovie(rs.getObject("movieid"));
              //  rent1.setPayment(rs.getObject("paymentid"));
                rent1.setQuantity(rs.getInt("quantity"));
                rent1.setReturned(rs.getBoolean("returned"));
           

      
                
                rents.add(rent1);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  rents;
        
        
    }
    
     public boolean delete(Rent rent){

        con = ConnectionDB.getConnection();
     
        String sql="DELETE FROM rent WHERE rentid=?";
        PreparedStatement stmt = null; 
        
        try {
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, rent.getRentid());
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

  


    

