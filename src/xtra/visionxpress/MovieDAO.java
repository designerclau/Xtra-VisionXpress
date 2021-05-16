
package xtra.visionxpress;

/**
 * CRUD
 * @author Claudinea de Almeida
 */



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MovieDAO {
    
    
    private Connection con = null;
    
   
    /**
     * Method to save the movie into the database
     */
    public boolean Save(Movie movie){

        //connecting to the database
        con = ConnectionDB.getConnection();
            
        boolean returning=false;
        
        //sql query
        String sql="INSERT INTO movies (movietitle,description,cast,genre,available,picture) values (?,?,?,?,?)";
        PreparedStatement stmt = null;
        
        try {
                             
            //prepare statement to execute the sql in the MySQL           
            stmt=con.prepareStatement(sql);
            stmt.setString(1, movie.getMovietitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getCast());
            stmt.setString(4, movie.getGenre());
            stmt.setBoolean(5, movie.isAvailable());
            stmt.setString(6, movie.getPicture());
           
            //execute the sql statement
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Saved sucessfuly");
            returning= true;
        } catch (SQLException ex) {
            //in case of error the following message will be showing to the user
            JOptionPane.showMessageDialog(null, "Error. It was not saved sucessfuly"+ex);
            returning= false;
        } finally{
            ConnectionDB.CloseConnection(con, stmt);
        }
        
        return returning;
    }
    
    /**
     * Method to list all the movie registered into the database
     */
    public List<Movie> read(){
        //connecting to the database
        con = ConnectionDB.getConnection();
       
        //sql query
        String sql="Select * from movies";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList();
        try {
            //prepare the statement do execute the sql
            stmt=con.prepareStatement(sql);
            
            //execute the statement and it will return registers as a result
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
                
      
                //add to the list the movies found
                movies.add(movie);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  movies;
        
        
    }
    
    
    /**
     * Method to update the movie into the database
     */
    public boolean update(Movie movie){

         //connecting to the database 
        con = ConnectionDB.getConnection();
        
        //sql query
        String sql="UPDATE movies SET movietitle = ?, description = ?, cast =?, genre =?,availabel? "
                  +"WHERE movieid=?";
        PreparedStatement stmt = null; 
        
        try {
      
            //prepare the statement do execute the sql query
            stmt=con.prepareStatement(sql);
            stmt.setString(1, movie.getMovietitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getCast());
            stmt.setString(4, movie.getGenre());
            stmt.setBoolean(5, movie.isAvailable());
            stmt.setInt(6, movie.getMovieid());
            
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
     * Method to search the movies from the database by id
     */
    public List<Movie> search(int id){
        
        //connecting to the database
        con = ConnectionDB.getConnection();
     
        //String with sql query
        String sql="Select * from movies WHERE movieid=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList();
        Movie movie1 = new Movie();
        try {
            //preparing the statement to execute the sql query
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            //executing the sql query
            rs = stmt.executeQuery();
            
            while (rs.next()){
               
                //setting data from the database into the movie instance
                movie1.setMovieid(rs.getInt("movieid"));
                movie1.setMovietitle(rs.getString("movietitle"));
                movie1.setDescription(rs.getString("description"));
                movie1.setCast(rs.getString("cast"));
                movie1.setGenre(rs.getString("genre"));
                movie1.setPicture(rs.getString("picture"));
    
                // adding the movies to the list
                movies.add(movie1);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error. It was not found sucessfuly"+ex);
        } finally{
            ConnectionDB.CloseConnection(con, stmt, rs);
        }
        
         return  movies;
        
        
    }
    
    /**
     * Method to delete the movie from the database by id
     */
     public boolean delete(Movie movie){

         //database connection
        con = ConnectionDB.getConnection();
        
        //String with sql query
        String sql="DELETE FROM movies WHERE movieid=?";
        PreparedStatement stmt = null; 
        
        try {
            
            //preparing the statement to execute the sql query
            stmt=con.prepareStatement(sql);
            stmt.setInt(1, movie.getMovieid());
            
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

