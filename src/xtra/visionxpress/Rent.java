
package xtra.visionxpress;

import java.sql.Date;

/**
 * CRUD
 * @author Claudinea de Almeida
 */

public class Rent {
    private int rentid;
    private Date datarent;
    private Date datareturn;
    private Movie movie;
    private Payment payment;
    private int quantity;
    private Boolean returned;

    public Rent() {
    }

    public Rent(int rentid, Date datarent, Date datareturn, Movie movie, Payment payment, int quantity, Boolean returned) {
        this.rentid = rentid;
        this.datarent = datarent;
        this.datareturn = datareturn;
        this.movie = movie;
        this.payment = payment;
        this.quantity = quantity;
        this.returned = returned;
    }

    public int getRentid() {
        return rentid;
    }

    public void setRentid(int rentid) {
        this.rentid = rentid;
    }

    public Date getDatarent() {
        return datarent;
    }

    public void setDatarent(Date datarent) {
        this.datarent = datarent;
    }

    public Date getDatareturn() {
        return datareturn;
    }

    public void setDatareturn(Date datareturn) {
        this.datareturn = datareturn;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean isReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
    
    
    
}
