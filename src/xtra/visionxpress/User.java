
package xtra.visionxpress;

/**
 *
 * @author Claudinea de Almeida
 */
public class User extends Person{
    private String cardnumber;
    private boolean newUser;

    public User(String cardnumber, boolean newUser) {
        this.cardnumber = cardnumber;
        this.newUser = newUser;
    }

    public User(String cardnumber, boolean newUser, String name, String email, String phone) {
        super(name, email, phone);
        this.cardnumber = cardnumber;
        this.newUser = newUser;
    }
    
     public User() {
        
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

}

