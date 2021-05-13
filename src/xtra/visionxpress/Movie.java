package xtra.visionxpress;

/**
 *
 * @author Claudinea de Almeida
 */
public class Movie {
    private int movieid;
    private String movietitle;
    private String description;
    private String cast;
    private String genre;
    private String picture;
    private boolean available;

    public Movie(int movieid, String movietitle, String description, String cast, String genre, boolean available) {
        this.movieid = movieid;
        this.movietitle = movietitle;
        this.description = description;
        this.cast = cast;
        this.genre = genre;
        this.available = available;
    }



    public Movie() {
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int moveid) {
        this.movieid = moveid;
    }

    public String getMovietitle() {
        return movietitle;
    }

    public void setMovietitle(String movetitle) {
        this.movietitle = movetitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
}
