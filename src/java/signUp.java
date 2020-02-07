/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.sql.*;

/**
 *
 * @author Abdullah Alqahtani
 */
@Named(value = "signUp")
@RequestScoped
public class signUp extends CurrentUser{

    /**
     * Creates a new instance of Registration
     */
    
        
    private int idnNumber = 0;
    

    public int getIdnNumber() {
        return idnNumber;
    }

    public void setIdnNumber(int idnNumber) {
        this.idnNumber = idnNumber;
    }

    
        
        
    
    public String register(){
        
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
            
            rs = st.executeQuery("Select * from useraccount where loginid = '" + super.getLoginID() + "'");
            if(rs.next()){
                return ("***Login ID already exists. Try a different one***");
            }
            else{
                rs = st.executeQuery("Select * from nextidnumber");

                int nextIdNumber = 0;
                if(rs.next()){
                    idnNumber = rs.getInt(1);
                    nextIdNumber = rs.getInt(1) + 1;
                }

                conn.setAutoCommit(false);

                int t = st.executeUpdate("Update nextidnumber set nextID = '" + nextIdNumber + "'");
                int r = st.executeUpdate("Insert into useraccount values('" + idnNumber + "', '" + super.getLoginID().toUpperCase()+ 
                        "', '" + super.getPassword() + "', '" + super.getGender().toUpperCase()+ 
                        "' , '" + super.getAge() + "', '" + super.getCity().toUpperCase()+ 
                        "', '" + super.getInterest1().toUpperCase()+ "', '" + super.getInterest2().toUpperCase()+ "', '" + super.getInterest3().toUpperCase()+ 
                        "', '" + null + "', '" + null + "', '" + 0 + "')");

                conn.commit();
                conn.setAutoCommit(true);

                return ("Success");
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return ("errors/internalError.xhtml");
        }
        finally{
            try{
                conn.close();
                st.close();
                rs.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
        
        
    
    
}
