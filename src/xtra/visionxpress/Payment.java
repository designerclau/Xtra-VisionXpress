
package xtra.visionxpress;

import java.sql.Date;

/**
 *
 * @author Claudinea de Almeida
 */
public class Payment {

    private int paymentid;
    private Float Amount;
    private Date date;
    private Date time;
    private User user;

    public Payment() {
    }

    public Payment(int paymentid, Float Amount, Date date, Date time, User user) {
        this.paymentid = paymentid;
        this.Amount = Amount;
        this.date = date;
        this.time = time;
        this.user = user;
    }

    public int getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

    public Float getAmount() {
        return Amount;
    }

    public void setAmount(Float Amount) {
        this.Amount = Amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    
}


