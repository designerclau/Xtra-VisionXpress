
package xtra.visionxpress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * CRUD
 * @author Claudinea de Almeida
 */
public class PaymentDAO {
   
   
    private Connection con = null;
    
   
    
    public boolean Save(Payment payment){

        con = ConnectionDB.getConnection();
            
        boolean returning=false;
        
        String sql="INSERT INTO payment (paymentid,amount,date,time,userid) values (?,?,?,?,?)";
        PreparedStatement stmt = null;
        
        try {
                             
                      
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, payment.getPaymentid());
            stmt.setFloat(2, payment.getAmount());
            stmt.setDate(3, payment.getDate());
            stmt.setDate(4, payment.getTime());
           // stmt.setString(5, payment.getUser());
           
            
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
    
    
    public List<Payment> read(){
        con = ConnectionDB.getConnection();
     
        String sql="Select * from payment";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Payment> payments = new ArrayList();
        try {
            stmt=con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Payment payment = new Payment();
                
                payment.setPaymentid(rs.getInt("paymentid"));
                payment.setAmount(rs.getFloat("amount"));
                payment.setDate(rs.getDate("date"));
                payment.setDate(rs.getDate("time"));
              //  payment.setUser(rs.getString(User user));
               
                
                
      
                
                payments.add(payment);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  payments;
        
        
    }
    
    
    
    public boolean update(Payment payment){

          
        con = ConnectionDB.getConnection();
     
        String sql="UPDATE payment SET amount = ?, date =?, time =?,user? "
                  +"WHERE paymentid=?";
        PreparedStatement stmt = null; 
        
        try {
            
            
      
            
            stmt=con.prepareStatement(sql);
            stmt.setFloat(1, payment.getAmount());
            stmt.setDate(2, payment.getDate());
            stmt.setDate(3, payment.getTime());
          //  stmt.setString(4, payment.getUser());
            stmt.setInt(5, payment.getPaymentid());
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
    
    
    public List<Payment> search(int id){
        con = ConnectionDB.getConnection();
     
        String sql="Select * from payment WHERE paymentid=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Payment> payments = new ArrayList();
        Payment payment1 = new Payment();
        try {
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()){
               
                
                payment1.setPaymentid(rs.getInt("paymentid"));
                payment1.setAmount(rs.getFloat("amount"));
                payment1.setDate(rs.getDate("date"));
                payment1.setDate(rs.getDate("time"));
            //    payment1.setUser(rs.getInt("userid"));
           

      
                
                payments.add(payment1);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  payments;
        
        
    }
    
     public boolean delete(Payment payment){

        con = ConnectionDB.getConnection();
     
        String sql="DELETE FROM payment WHERE paymentid=?";
        PreparedStatement stmt = null; 
        
        try {
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, payment.getPaymentid());
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

  

 

