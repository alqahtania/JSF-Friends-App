/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.*;

/**
 *
 * @author Abdullah Alqahtani
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
    private userAccount loginAccount;
    private String loginID;
    private String password;
    

    public userAccount getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(userAccount loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    public String login(){
        //load the jdbc driver, registered in web application 
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return("errors/internalError.xhtml");
        }
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        
        try{
            
            conn = DriverManager.getConnection(DB_URL, "root", "******");
            
            st = conn.createStatement();
            
            rs = st.executeQuery("Select * from useraccount where loginID = '" + loginID + "'");
            
            if(rs.next()){
                //The loginid was found in db
                if(password.equals(rs.getString(3))){
                    //The password is correct
                    // get the current time
                    String dateTime = DateAndTime.DateTime();
                    //Initialize the userAccount object
                    loginAccount = new userAccount(rs.getInt(1), capitalizeFirst(loginID), rs.getString(3), capitalizeFirst(rs.getString(4)), rs.getInt(5), 
                            capitalizeFirst(rs.getString(6)), capitalizeFirst(rs.getString(7)), capitalizeFirst(rs.getString(8)), capitalizeFirst(rs.getString(9)), rs.getInt(12), rs.getString(11));
                    int t = st.executeUpdate("Update useraccount set lastonline = '" + dateTime + "' WHERE loginid = '" + loginID + "'");
                    return "welcome.xhtml?faces-redirect=true";
                    
                }
                else{
                    //Password is incorrect
                    return "errors/loginFailed.xhtml";
                }
            }
            else{
                //Login ID is incorrect
                return "errors/loginFailed.xhtml";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
        }
        finally{
            try{
                conn.close();
                st.close();
                rs.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static String capitalizeFirst(String word){
        word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
        return word;
    }
    
    
}
