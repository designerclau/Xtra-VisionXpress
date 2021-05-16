
package xtra.visionxpress;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 * CRUD
 * @author Claudinea de Almeida
 */

public class PaymentDAO {
   
   
    private Connection con = null;
    
   
     /**
     * Method to save the payment into the database
     */
    public String Save(Payment payment){

         //connecting to the database
        con = ConnectionDB.getConnection();
            
        String returning="false";
        
        //sql query
        String sql="INSERT INTO payment (paymentid,amount,date,time,userid) values (?,?,?,?,?)";
        PreparedStatement stmt = null;
        
        try {
            
            //prepare statement to execute the sql in the MySQL         
            stmt=con.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
            stmt.setInt(1, payment.getPaymentid());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, payment.getDate());
            stmt.setDate(4, payment.getTime());
            stmt.setString(5, "4037 3456 2345 2132");
           
           
            //execute the sql statement
            stmt.executeUpdate();
            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                final int lastId = rs.getInt(1);
                returning= "true - "+String.valueOf(lastId);
            }
            //JOptionPane.showMessageDialog(null, "Saved sucessfuly");
            
        } catch (SQLException ex) {
            //in case of error the following message will be showing to the user
            JOptionPane.showMessageDialog(null, "Error saving Payment. It was not saved sucessfuly"+ex);
            returning= "false";
        } finally{
            ConnectionDB.CloseConnection(con, stmt);
        }
        
        return returning;
    }
    
    /**
     * Method to list all the payment registered into the database
     */
    public List<Payment> read(){
        
        //connecting to the database
        con = ConnectionDB.getConnection();
     
        String sql="Select * from payment";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Payment> payments = new ArrayList();
        try {
            //prepare the statement do execute the sql 
            stmt=con.prepareStatement(sql);
            
            //execute the statement and it will return registers as a result
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Payment payment = new Payment();
                
                payment.setPaymentid(rs.getInt("paymentid"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setDate(rs.getDate("date"));
                payment.setDate(rs.getDate("time"));
              //  payment.setUser(rs.getString(User user));
          
               //add the payment to the list
                payments.add(payment);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  payments;
        
        
    }
    
    
    /**
     * Method to update the payment into the database
     */
    public boolean update(Payment payment){

        //updating the Payment table on the database  
        con = ConnectionDB.getConnection();
     
        String sql="UPDATE payment SET amount = ?, date =?, time =? "
                  +"WHERE paymentid=?";
        PreparedStatement stmt = null; 
       
        try {
            
            //prepare the statement do execute the sql query
            stmt=con.prepareStatement(sql);
            stmt.setDouble(1, payment.getAmount());
            stmt.setDate(2, payment.getDate());
            stmt.setDate(3, payment.getTime());
           // stmt.setString(4, payment.getUser());
            stmt.setInt(4, payment.getPaymentid());
            
            //executing the statment to update
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
     * Method to search the payment from the database by id
     */
    public List<Payment> search(int id){
        
        //connecting to the database
        con = ConnectionDB.getConnection();
     
        //String with sql query
        String sql="Select * from payment WHERE paymentid=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Payment> payments = new ArrayList();
        Payment payment1 = new Payment();
        try {
            
            //preparing the statement to execute the sql query
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            //executing the sql query
            rs = stmt.executeQuery();
            
            while (rs.next()){
               
                //setting the data into the payment instance
                payment1.setPaymentid(rs.getInt("paymentid"));
                payment1.setAmount(rs.getDouble("amount"));
                payment1.setDate(rs.getDate("date"));
                payment1.setDate(rs.getDate("time"));
            //    payment1.setUser(rs.getInt("userid"));
           
 
                // adding the payment to the list
                payments.add(payment1);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  payments;
        
        
    }
    
    /**
     * Method to delete the payment from the database by id
     */
     public boolean delete(Payment payment){

        //database connection
        con = ConnectionDB.getConnection();
     
        //String with sql query
        String sql="DELETE FROM payment WHERE paymentid=?";
        PreparedStatement stmt = null; 
        
        try {
            //preparing the statement to execute the sql query
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, payment.getPaymentid());
            
            //executing the sql query to delete data
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

  

 

