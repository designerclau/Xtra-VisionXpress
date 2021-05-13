
package xtra.visionxpress;

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

public class UserDAO {
  
    
    private Connection con = null;
    
   
    
    public boolean Save(User user){

        con = ConnectionDB.getConnection();
            
        boolean returning=false;
        
        String sql="INSERT INTO user (cardnumber,newuser,name,email,phone) values (?,?,?,?,?)";
        PreparedStatement stmt = null;
        
        try {
                             
                      
            stmt=con.prepareStatement(sql);
            stmt.setString(1, user.getCardnumber());
            stmt.setBoolean(2, user.isNewUser());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhone());
           
            
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
    
    
    public List<User> read(){
        con = ConnectionDB.getConnection();
     
        String sql="Select * from user";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList();
        try {
            stmt=con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                User user = new User();
                
                user.setCardnumber(rs.getString("cardnumber"));
                user.setNewUser(rs.getBoolean("newuser"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
               
                
                
      
                
                users.add(user);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  users;
        
        
    }
    
    
    
    public boolean update(User user){

          
        con = ConnectionDB.getConnection();
     
        String sql="UPDATE user SET newuser = ?, name =?, email =?,phone? "
                  +"WHERE cardnumber=?";
        PreparedStatement stmt = null; 
        
        try {
            
            
      
            
            stmt=con.prepareStatement(sql);
            stmt.setBoolean(1, user.isNewUser());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(6, user.getCardnumber());
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
    
    
    public List<User> search(String id){
        con = ConnectionDB.getConnection();
     
        String sql="Select * from user WHERE cardnumber=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList();
        User user1 = new User();
        try {
            stmt=con.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()){
               
                
                user1.setCardnumber(rs.getString("cardnumber"));
                user1.setNewUser(rs.getBoolean("newuser"));
                user1.setName(rs.getString("name"));
                user1.setEmail(rs.getString("email"));
                user1.setPhone(rs.getString("phone"));
           

      
                
                users.add(user1);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  users;
        
        
    }
    
     public boolean delete(User user){

        con = ConnectionDB.getConnection();
     
        String sql="DELETE FROM user WHERE cardnumber=?";
        PreparedStatement stmt = null; 
        
        try {
            stmt=con.prepareStatement(sql);
            stmt.setString(1, user.getCardnumber());
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

  

