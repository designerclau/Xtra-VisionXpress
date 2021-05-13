
package xtra.visionxpress;

/**
 * CRUD
 * @author Claudinea de Almeida
 */



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


public class MovieDAO {
    
    
    private Connection con = null;
    
   
    
    public boolean Save(Movie movie){

        con = ConnectionDB.getConnection();
            
        boolean returning=false;
        
        String sql="INSERT INTO movies (movietitle,description,cast,genre,available,picture) values (?,?,?,?,?)";
        PreparedStatement stmt = null;
        
        try {
                             
                      
            stmt=con.prepareStatement(sql);
            stmt.setString(1, movie.getMovietitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getCast());
            stmt.setString(4, movie.getGenre());
            stmt.setBoolean(5, movie.isAvailable());
            stmt.setString(6, movie.getPicture());
           
            
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
    
    
    public List<Movie> read(){
        con = ConnectionDB.getConnection();
     
        String sql="Select * from movies";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList();
        try {
            stmt=con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Movie movie = new Movie();
                
                movie.setMovieid(rs.getInt("movieid"));
                movie.setMovietitle(rs.getString("movietitle"));
                movie.setDescription(rs.getString("description"));
                movie.setCast(rs.getString("cast"));
                movie.setGenre(rs.getString("genre"));
                movie.setAvailable(rs.getBoolean("available"));
                movie.setPicture(rs.getString("picture"));
                
      
                
                movies.add(movie);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  movies;
        
        
    }
    
    
    
    public boolean update(Movie movie){

          
        con = ConnectionDB.getConnection();
     
        String sql="UPDATE movies SET movietitle = ?, description = ?, cast =?, genre =?,availabel? "
                  +"WHERE movieid=?";
        PreparedStatement stmt = null; 
        
        try {
            
            
      
            
            stmt=con.prepareStatement(sql);
            stmt.setString(1, movie.getMovietitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getCast());
            stmt.setString(4, movie.getGenre());
            stmt.setBoolean(5, movie.isAvailable());
            stmt.setInt(6, movie.getMovieid());
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
    
    
    public List<Movie> search(int id){
        con = ConnectionDB.getConnection();
     
        String sql="Select * from movies WHERE movieid=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList();
        Movie movie1 = new Movie();
        try {
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()){
               
                
                movie1.setMovieid(rs.getInt("movieid"));
                movie1.setMovietitle(rs.getString("movietitle"));
                movie1.setDescription(rs.getString("description"));
                movie1.setCast(rs.getString("cast"));
                movie1.setGenre(rs.getString("genre"));
                movie1.setPicture(rs.getString("picture"));
           

      
                
                movies.add(movie1);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  movies;
        
        
    }
    
     public boolean delete(Movie movie){

        con = ConnectionDB.getConnection();
     
        String sql="DELETE FROM movies WHERE movieid=?";
        PreparedStatement stmt = null; 
        
        try {
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, movie.getMovieid());
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

