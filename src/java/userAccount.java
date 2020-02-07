
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah Alqahtani
 */
public class userAccount extends CurrentUser{
    //User account attributes and functions
    
   
    

    //*********For the search inputs****
    private String genderIn;
    private int ageIn;
    private int age2In;
    private String cityIn;
    private String interestIn;
    //*******For storing search result
    private ArrayList<users> searchResult;
    //*******For viewing a user's profile
    private users user;
    //To get if the accept friend went through
    private boolean isfRAccepted = false;
    //To get if the denied request went through
    private boolean isFRDenied = false;
    //For messages
    private ArrayList<message> messages;
    private String userToMessage;
    private String inputMessage;
    //For updating the logged in user's account
    private int userAgeIn = super.getAge();
    private String userGenderIn = super.getGender();
    private String userCityIn = super.getCity();
    private String userInterest1In = super.getInterest1();
    private String userInterest2In = super.getInterest2();
    private String userInterest3In = super.getInterest3();
    //For resetting password
    private String oldPassword;
    private String newPassword1;
    private String newPassword2;

    public userAccount(int userID, String loginID, String password, String gender, int age, String city, String interest1, String interest2, String interest3, int views, String lastOnline) {
        super(userID, loginID, password, gender, age, city, interest1, interest2, interest3, views, lastOnline);
    }
    

    public String getGenderIn() {
        return genderIn;
    }

    public void setGenderIn(String genderIn) {
        this.genderIn = genderIn;
    }

    public int getAgeIn() {
        return ageIn;
    }

    public void setAgeIn(int ageIn) {
        this.ageIn = ageIn;
    }

    public int getAge2In() {
        return age2In;
    }

    public void setAge2In(int age2In) {
        this.age2In = age2In;
    }

    public String getCityIn() {
        return cityIn;
    }

    public void setCityIn(String cityIn) {
        this.cityIn = cityIn;
    }

    public String getInterestIn() {
        return interestIn;
    }

    public void setInterestIn(String interestIn) {
        this.interestIn = interestIn;
    }

    public ArrayList<users> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(ArrayList<users> searchResult) {
        this.searchResult = searchResult;
    }

    public users getUser() {
        return user;
    }

    public void setUser(users user) {
        this.user = user;
    }

    public boolean isIsfRAccepted() {
        return isfRAccepted;
    }

    public void setIsfRAccepted(boolean isfRAccepted) {
        this.isfRAccepted = isfRAccepted;
    }

    public boolean isIsFRDenied() {
        return isFRDenied;
    }

    public void setIsFRDenied(boolean isFRDenied) {
        this.isFRDenied = isFRDenied;
    }

    public ArrayList<message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<message> messages) {
        this.messages = messages;
    }

    public String getUserToMessage() {
        return userToMessage;
    }

    public void setUserToMessage(String userToMessage) {
        this.userToMessage = userToMessage;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public int getUserAgeIn() {
        return userAgeIn;
    }

    public void setUserAgeIn(int userAgeIn) {
        this.userAgeIn = userAgeIn;
    }

    public String getUserGenderIn() {
        return userGenderIn;
    }

    public void setUserGenderIn(String userGenderIn) {
        this.userGenderIn = userGenderIn;
    }

    public String getUserCityIn() {
        return userCityIn;
    }

    public void setUserCityIn(String userCityIn) {
        this.userCityIn = userCityIn;
    }

    public String getUserInterest1In() {
        return userInterest1In;
    }

    public void setUserInterest1In(String userInterest1In) {
        this.userInterest1In = userInterest1In;
    }

    public String getUserInterest2In() {
        return userInterest2In;
    }

    public void setUserInterest2In(String userInterest2In) {
        this.userInterest2In = userInterest2In;
    }

    public String getUserInterest3In() {
        return userInterest3In;
    }

    public void setUserInterest3In(String userInterest3In) {
        this.userInterest3In = userInterest3In;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    
    
    
    
    
    
    
    

    public String search(){
        

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
           return "errors/internalError.xhtml";
        }
        
        searchResult = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        
        try{
            
            conn = DriverManager.getConnection(DB_URL, "root", "******");
            
            st = conn.createStatement();
            
            rs = st.executeQuery("Select * from useraccount where Gender = '" + genderIn + "' AND City = '" + cityIn + 
                    "' And (Age between '" + ageIn + "' AND '" + age2In + "') AND (interest1 = '" + interestIn + 
                    "' OR interest2 = '" + interestIn + "' OR interest3 = '" + interestIn + "') AND loginID != '" + super.getLoginID() + "'");
            
            boolean found = false;
            while(rs.next()){
                found = true;
                searchResult.add(new users(rs.getInt(1), capitalizeFirst(rs.getString(2)), capitalizeFirst(rs.getString(4)), rs.getInt(5), 
                        capitalizeFirst(rs.getString(6)), capitalizeFirst(rs.getString(7)), capitalizeFirst(rs.getString(8)), 
                        capitalizeFirst(rs.getString(9)), rs.getString(11), rs.getInt(12), "NA"));
            }
            
            
            if(found){
                return "searchResult.xhtml";
            }
            else{
                return "noUsersFound.xhtml";
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
    
    
//    //Faster and easier way of viewing profile
//    public String profile(users user){
//        System.out.println("jbloo");
//        this.user = user;
//        System.out.println("jcloo");
//        return "viewProfile.xhtml";
        
//    }
    
    
    public String viewProfile(users user, String componentID){
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
           
            
        }
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           rs = st.executeQuery("Select * from useraccount WHERE loginid = '" + user.getLoginID() + "'");
           boolean found = false;
           if(rs.next()){
               found = true;
               this.user = new users(rs.getInt(1), capitalizeFirst(rs.getString(2)), capitalizeFirst(rs.getString(4)), rs.getInt(5), 
                        capitalizeFirst(rs.getString(6)), capitalizeFirst(rs.getString(7)), capitalizeFirst(rs.getString(8)), 
                        capitalizeFirst(rs.getString(9)), rs.getString(11), rs.getInt(12), "NA");
           }
           if(found){
               int viewsNum = rs.getInt(12) + 1;
               int t = st.executeUpdate("Update useraccount set views = '" + viewsNum + "' WHERE loginid = '" + user.getLoginID() + "'");
           }
           if(found && "submitSearch".equalsIgnoreCase(componentID)){
               return "viewProfile.xhtml";
           }
           //To view the profile of the user who sent the friend request. component is the button id
           else if(found && "submitRequest".equalsIgnoreCase(componentID)){
               isfRAccepted = false;
               isFRDenied = false;
               return "viewRequestProfile.xhtml?faces-redirect=true";
           }
           else if(found && "submitView".equalsIgnoreCase(componentID)){
               return "viewProfileOnly.xhtml?faces-redirect=true";
           }
           else if(found && "submitViewFriend".equalsIgnoreCase(componentID)){
               return "viewFriendProfile.xhtml?faces-redirect=true";
           }
           else{
               return "noUsersFound.xhtml";
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
    
    public String addFriend(users userToAdd){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
        }
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           rs = st.executeQuery("Select * from friendlist where (user_first_id = '" + super.getUserID() + 
                                        "' AND user_sec_id = '" + userToAdd.getUserID() + "') OR (user_first_id = '" + userToAdd.getUserID() + 
                                        "' AND user_sec_id = '" + super.getUserID() + "')");
           if(!rs.next()){
                //They are not already friends so send friend request 
                //Check for the constraint on the table check(first < second)
                if(super.getUserID() < userToAdd.getUserID()){
                    //0 is for false in mysql
                    int r = st.executeUpdate("Insert into friendlist values ('" + super.getUserID() + "', '" + userToAdd.getUserID() + 
                            "', '" + super.getUserID() + "', '" + null + "', '" + 0 + "')");
//                    return "Friend request successfully sent to " + userToAdd.getLoginID() + "! Wait for approval.";
                      return "success";
                    
                }
                else{
                    int r = st.executeUpdate("Insert into friendlist values ('" + userToAdd.getUserID() + "', '" + super.getUserID() + 
                            "', '" + null + "', '" + super.getUserID() + "', '" + 0 + "')");
//                    return "Friend request successfully sent to " + userToAdd.getLoginID() + "! Wait for approval.";
                      return "success";
                }
                
            }
           else if(Integer.parseInt(rs.getString(5)) == 0){
                //There is a pending request already
                //Check to see if the logged user is the one who sent it
                if(tryParse(rs.getString(3)) == super.getUserID() || tryParse(rs.getString(4)) == super.getUserID()){
//                    return "You already sent a request to " + userToAdd.getLoginID() + "! Wait for approval.";
                      return "already sent";
                }
                else{
                    //The current user didn't make the request and need to approve
//                    return "There is a pending request already from " + userToAdd.getLoginID() + "! Go to notifications to accept.";
                      return "already pending";
                }

            }
           else{
//                return "You are already friends with " + userToAdd.getLoginID();
                  return "already friends";
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
    
    public List<users> friendReqReceived(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        searchResult = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        Statement st2 = null;
        Statement st3 = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           st2 = conn.createStatement();
           st3 = conn.createStatement();
           //Query requests made by other users (!= this.ID)
           rs = st.executeQuery("Select * from friendlist where (user_first_id = '" + super.getUserID() + 
                    "' OR user_sec_id = '" + super.getUserID() + "') AND pending_first_sec != '" + super.getUserID() + 
                    "' AND pending_sec_first != '" + super.getUserID() + "' AND friends = '" + 0 + "'");
            
           boolean foundRequests = false;
           boolean foundAccount = false;
           boolean foundAccount2 = false;
           while(rs.next()){
               
                //There are friend requests found loop through them
                foundRequests = true;
                //if the 1st column in friendlist != this.ID then it is the id of the person sent the request 
                if(rs.getInt(1) != super.getUserID()){
                    
                    //Write a query to the user account table to get their info based on the id found in the friendlist table
                    ResultSet rs2 = st2.executeQuery("Select * from useraccount where id = '" + rs.getInt(1) + "'");
                    if(rs2.next()){
                        
                        searchResult.add(new users(rs2.getInt(1), capitalizeFirst(rs2.getString(2)), capitalizeFirst(rs2.getString(4)), rs2.getInt(5), 
                        capitalizeFirst(rs2.getString(6)), capitalizeFirst(rs2.getString(7)), capitalizeFirst(rs2.getString(8)), 
                        capitalizeFirst(rs2.getString(9)), rs2.getString(11), rs2.getInt(12), "Pending"));
                        
                    //To make sure their account exists
                    foundAccount = true;
                    }
                    else if(!rs2.next() && !foundAccount){
                        //They have requested but don't exist anymore in the useraccount table so delete them
                        int n = st3.executeUpdate("Delete from friendlist where id = '" + rs.getInt(1) + "'");
                    }
                }
                else{
                    //Then it is the id in the second column of friendlist who sent the request
                    ResultSet rs2 = st2.executeQuery("Select * from useraccount where id = '" + rs.getInt(2) + "'");
                    if(rs2.next()){
                        searchResult.add(new users(rs2.getInt(1), capitalizeFirst(rs2.getString(2)), capitalizeFirst(rs2.getString(4)), rs2.getInt(5), 
                        capitalizeFirst(rs2.getString(6)), capitalizeFirst(rs2.getString(7)), capitalizeFirst(rs2.getString(8)), 
                        capitalizeFirst(rs2.getString(9)), rs2.getString(11), rs2.getInt(12), "Pending"));
                        foundAccount2 = true;
                    }
                    else if(!rs2.next() && !foundAccount2){
                        //They have requested but don't exist anymore in the useraccount table so delete them
                        int n = st3.executeUpdate("Delete from friendlist where id = '" + rs.getInt(2) + "'");
                    }
                }

            }
           
        }
        catch(Exception e){
            e.printStackTrace();
            
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
        return searchResult;
    }
    
    public String acceptFrReq(users userToAccept){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
        }
        
        Connection conn = null;
        Statement st = null;
        
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           int u = st.executeUpdate("Update friendlist set friends = '" + 1 + 
                                    "' where (user_first_id = '" + super.getUserID() + "' AND user_sec_id = '" + userToAccept.getUserID() + 
                                    "') OR (user_first_id = '" + userToAccept.getUserID() + "' AND user_sec_id = '" + 
                                    super.getUserID() + "')");
           isfRAccepted = true;
           user = null;
           return "viewRequestProfile.xhtml";
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
            
        }
        finally{
            try{
                conn.close();
                st.close();
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
        
    }
    
    public String denyFrReq(users userToDeny){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
        }
        
        Connection conn = null;
        Statement st = null;
        
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           int u = st.executeUpdate("Delete from friendlist where (user_first_id = '" + 
                                        super.getUserID() + "' AND user_sec_id = '" + userToDeny.getUserID() + 
                                        "') OR (user_first_id = '" + userToDeny.getUserID() + "' AND user_sec_id = '" + 
                                        super.getUserID() + "')");
           isFRDenied = true;
           user = null;
           return "viewRequestProfile.xhtml";
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
            
        }
        finally{
            try{
                conn.close();
                st.close();
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }
    
    public List<users> friendReqSent(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        
        searchResult = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        Statement st2 = null;
        Statement st3 = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
            conn = DriverManager.getConnection(DB_URL, "root", "******");
            st = conn.createStatement();
            st2 = conn.createStatement();
            st3 = conn.createStatement();
            
            
            //For the pending requests
            rs = st.executeQuery("Select * from friendlist where (user_first_id = '" + super.getUserID() + 
                            "' OR user_sec_id = '" + super.getUserID() + "') AND (pending_first_sec = '" + Integer.toString(super.getUserID())  + 
                            "' OR pending_sec_first = '" + Integer.toString(super.getUserID()) + "') AND friends = '" + 0 + "'");
           
            boolean foundPending = false;
            boolean foundAccepted = false;
            boolean foundAccountPend = false;
            boolean foundAccountPend2 = false;
            int i = 0;
            

            System.out.println("Haloooo pending " + super.getLoginID());
            while(rs.next()){
                System.out.println("Hbloooo pending " + super.getLoginID());
                foundPending = true;
                if(rs.getInt(1) != super.getUserID()){
                    ResultSet rs2 = st2.executeQuery("Select * from useraccount where id = '" + rs.getString(1) + "'");
                    if(rs2.next()){
                        searchResult.add(new users(rs2.getInt(1), capitalizeFirst(rs2.getString(2)), capitalizeFirst(rs2.getString(4)), rs2.getInt(5), 
                        capitalizeFirst(rs2.getString(6)), capitalizeFirst(rs2.getString(7)), capitalizeFirst(rs2.getString(8)), 
                        capitalizeFirst(rs2.getString(9)), rs2.getString(11), rs2.getInt(12), "Pending"));
                        foundAccountPend = true;
                        
                    }
                    else if(!rs2.next() && !foundAccountPend){
                        //You have requested but they don't don't exist in useraccount anymore so delete the user from friendlist
                        int n = st3.executeUpdate("Delete from friendlist where id = '" + rs.getString(1) + "'");
                    }
                }
                else{
                    ResultSet rs2 = st2.executeQuery("Select * from useraccount where id = '" + rs.getString(2) + "'");
                    if(rs2.next()){
                        searchResult.add(new users(rs2.getInt(1), capitalizeFirst(rs2.getString(2)), capitalizeFirst(rs2.getString(4)), rs2.getInt(5), 
                            capitalizeFirst(rs2.getString(6)), capitalizeFirst(rs2.getString(7)), capitalizeFirst(rs2.getString(8)), 
                            capitalizeFirst(rs2.getString(9)), rs2.getString(11), rs2.getInt(12), "Pending"));
                        foundAccountPend2 = true;
                        
                    }
                    else if(!rs2.next() && !foundAccountPend2){
                        //You have requested but they don't don't exist in useraccount anymore so delete the user from friendlist
                        int n = st3.executeUpdate("Delete from friendlist where id = '" + rs.getString(2) + "'");
                    }
                }
            }
                
            //For the accepted requests
            rs = st.executeQuery("Select * from friendlist where (user_first_id = '" + super.getUserID() + 
                        "' OR user_sec_id = '" + super.getUserID() + "') AND (pending_first_sec = '" + Integer.toString(super.getUserID()) + 
                        "' OR pending_sec_first = '" + Integer.toString(super.getUserID()) + "') AND friends = '" + 1 + "'");
            boolean foundAcctAccepted = false;
            boolean foundAcctAccepted2 = false;
            System.out.println("Hcloooo pending " + super.getLoginID());
            while(rs.next()){
                System.out.println("Hdloooo pending " + super.getLoginID());
                foundAccepted = true;
                if(rs.getInt(1) != super.getUserID()){
                    ResultSet rs2 = st2.executeQuery("Select * from useraccount where id = '" + rs.getString(1) + "'");
                    if(rs2.next()){
                        searchResult.add(new users(rs2.getInt(1), capitalizeFirst(rs2.getString(2)), capitalizeFirst(rs2.getString(4)), rs2.getInt(5), 
                            capitalizeFirst(rs2.getString(6)), capitalizeFirst(rs2.getString(7)), capitalizeFirst(rs2.getString(8)), 
                            capitalizeFirst(rs2.getString(9)), rs2.getString(11), rs2.getInt(12), "Accepted"));
                        foundAcctAccepted = true;

                    }
                    else if(!rs2.next() && !foundAcctAccepted){
                        //You have requested but they don't don't exist in useraccount anymore so delete the user from friendlist
                        int n = st3.executeUpdate("Delete from friendlist where id = '" + rs.getString(1) + "'");
                    }
                }
                else{
                    ResultSet rs2 = st2.executeQuery("Select * from useraccount where id = '" + rs.getString(2) + "'");
                    if(rs2.next()){
                        searchResult.add(new users(rs2.getInt(1), capitalizeFirst(rs2.getString(2)), capitalizeFirst(rs2.getString(4)), rs2.getInt(5), 
                            capitalizeFirst(rs2.getString(6)), capitalizeFirst(rs2.getString(7)), capitalizeFirst(rs2.getString(8)), 
                            capitalizeFirst(rs2.getString(9)), rs2.getString(11), rs2.getInt(12), "Accepted"));
                        foundAcctAccepted2 = true;

                    }
                    else if(!rs2.next() && !foundAcctAccepted2){
                        //You have requested but they don't don't exist in useraccount anymore so delete the user from friendlist
                        int n = st3.executeUpdate("Delete from friendlist where id = '" + rs.getString(2) + "'");
                    }
                }
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        finally{
            try{
                conn.close();
                st.close();
                st2.close();
                st3.close();
                rs.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
        return searchResult;
        
    }
    
    
    //Susmitha 
    public List<users> frndlist(){
       
    
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        
        searchResult = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        Statement st2 = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        
        try{
            
            conn = DriverManager.getConnection(DB_URL, "root", "******");
            
            st = conn.createStatement();
            st2 = conn.createStatement();
            
            rs = st.executeQuery("Select * from friendlist where (user_first_id = '" +super.getUserID()+ "' OR user_sec_id = '" +super.getUserID()+ "') AND friends = '" + 1 + "'");
            boolean found = false;
            boolean foundAccount = false;
            boolean foundAccount2 = false;
            
            while(rs.next()){
                found = true;
                if(rs.getInt(1) != super.getUserID()){
                    ResultSet rs2 = st2.executeQuery("Select * from useraccount where id = '" + rs.getInt(1) + "'");
                    if(rs2.next()){
                      
                searchResult.add(new users(rs2.getInt(1), capitalizeFirst(rs2.getString(2)), capitalizeFirst(rs2.getString(4)), rs2.getInt(5), 
                        capitalizeFirst(rs2.getString(6)), capitalizeFirst(rs2.getString(7)), capitalizeFirst(rs2.getString(8)), 
                        capitalizeFirst(rs2.getString(9)), rs2.getString(11), rs2.getInt(12), "NA"));
                          foundAccount = true;
                        
                    }
                    else if(!rs2.next() && !foundAccount){
                        //They are your friend but don't exist anymore in the useraccount table so delete them
                        int n = st2.executeUpdate("Delete from friendlist where id = '" + rs.getInt(1) + "'");
                    }
                }
                else{
                    ResultSet rs2 = st2.executeQuery("Select * from useraccount where id = '" + rs.getInt(2) + "'");
                    if(rs2.next()){
                        
                searchResult.add(new users(rs2.getInt(1), capitalizeFirst(rs2.getString(2)), capitalizeFirst(rs2.getString(4)), rs2.getInt(5), 
                        capitalizeFirst(rs2.getString(6)), capitalizeFirst(rs2.getString(7)), capitalizeFirst(rs2.getString(8)), 
                        capitalizeFirst(rs2.getString(9)), rs2.getString(11), rs2.getInt(12), "NA"));
                       foundAccount2 = true;
                        
                    }
                    else if(!rs2.next() && !foundAccount2){
                        //They are your friend but don't exist anymore in the useraccount table so delete them
                        int n = st2.executeUpdate("Delete from friendlist where ID = '" + rs.getInt(1) + "'");
                    }
                }
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            
            
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
        
       return searchResult;
   }
    
    public List<users> topFemale(){
         try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
         
        searchResult = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           rs = st.executeQuery("Select * from useraccount WHERE gender = '" + "FEMALE" + "' ORDER BY views desc");
           int i = 1;
           
           
           while(rs.next() && i <= 3){
               searchResult.add(new users(rs.getInt(1), capitalizeFirst(rs.getString(2)), capitalizeFirst(rs.getString(4)), rs.getInt(5), 
                        capitalizeFirst(rs.getString(6)), capitalizeFirst(rs.getString(7)), capitalizeFirst(rs.getString(8)), 
                        capitalizeFirst(rs.getString(9)), rs.getString(11), rs.getInt(12), "NA"));
               
               i++;
           }
        }
        catch(Exception e){
            e.printStackTrace();
            
            
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
        return searchResult;
         
    }
    
    public List<users> topMale(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
         
        searchResult = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           rs = st.executeQuery("Select * from useraccount WHERE gender = '" + "MALE" + "' ORDER BY views desc");
           int i = 1;
           
           
           while(rs.next() && i <= 3){
               searchResult.add(new users(rs.getInt(1), capitalizeFirst(rs.getString(2)), capitalizeFirst(rs.getString(4)), rs.getInt(5), 
                        capitalizeFirst(rs.getString(6)), capitalizeFirst(rs.getString(7)), capitalizeFirst(rs.getString(8)), 
                        capitalizeFirst(rs.getString(9)), rs.getString(11), rs.getInt(12), "NA"));
               
               i++;
           }
        }
        catch(Exception e){
            e.printStackTrace();
            
            
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
        return searchResult;
    }
    
    public List<String> unreadMssg(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        
        ArrayList<String> senders = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
            String notRed = "N";
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           rs = st.executeQuery("Select DISTINCT sender from message WHERE receiver = '" + super.getLoginID() + "' AND seen = '" + notRed + "'");
           while(rs.next()){
               senders.add(capitalizeFirst(rs.getString("sender")));
           }
           
        }
        catch(Exception e){
            e.printStackTrace();
            
            
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
        return senders;
    }
    
    
    public List<String> allWhoMssged(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        
        ArrayList<String> senders = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           rs = st.executeQuery("Select DISTINCT sender from message WHERE receiver = '" + super.getLoginID() + "'");
           while(rs.next()){
               senders.add(capitalizeFirst(rs.getString("sender")));
           }
           
        }
        catch(Exception e){
            e.printStackTrace();
            
            
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
        return senders;
        
    }
    
    public String messagesInfo(String userMessages){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
        }
        
        messages = new ArrayList<>();
        userToMessage = userMessages;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           rs = st.executeQuery("Select * from message where (sender = '" + userMessages + 
                                    "' AND receiver = '" + super.getLoginID() + "') OR (sender = '" + super.getLoginID() + "' AND receiver = '" + 
                                    userMessages + "') ORDER BY 'id'");
           while(rs.next()){
               String seen = "";
               String msgID = "";
               if(rs.getString(2).toUpperCase().equals(super.getLoginID().toUpperCase())){
                   seen = rs.getString(6).toUpperCase();
                   msgID = rs.getString(1);
               }
               String sender = rs.getString(2).toUpperCase().equals(super.getLoginID().toUpperCase())? "You": rs.getString(2);
               messages.add(new message(msgID, capitalizeFirst(sender), capitalizeFirst(rs.getString(3)), rs.getString(4), rs.getString(5), seen));
           }
           int u = st.executeUpdate("Update message set seen = 'Y' WHERE sender = '" + userMessages + 
                   "' AND receiver = '" + super.getLoginID() + "'");
           return "viewMessages.xhtml?faces-redirect=true";
            
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
    
    
    public String sendMessage(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
        }
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           String idNum = "";
           String dateTime = DateAndTime.DateTime();
           rs = st.executeQuery("Select * from nextmessageid");
                                
            int nextMsgId = 0;
            if(rs.next()){
                idNum = "" + rs.getInt(1);
                nextMsgId = rs.getInt(1) + 1;
            }
            conn.setAutoCommit(false);
                            
            String replaceSingleQuotes = inputMessage.replace("'", "''");
            int t = st.executeUpdate("Update nextmessageid set nextid = '" + nextMsgId + "'");
            int r = st.executeUpdate("Insert into message values('" + idNum + "', '" + super.getLoginID().toUpperCase() + "', '" + 
                    userToMessage.toUpperCase() + "', '" + replaceSingleQuotes + "', '" + dateTime + "', 'N')");
            conn.commit();
            conn.setAutoCommit(true);
            messagesInfo(userToMessage);
            inputMessage = "";
            return "viewMessages.xhtml?faces-redirect=true";
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
    
    public String deleteMsg(String msgID){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
        }
        
        Connection conn = null;
        Statement st = null;
        
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           int u = st.executeUpdate("Delete from message WHERE id = '" + msgID + "'");
           
            messagesInfo(userToMessage);
            
            return "viewMessages.xhtml?faces-redirect=true";
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
            
        }
        finally{
            try{
                conn.close();
                st.close();
                
            }
            catch(Exception e){
                e.printStackTrace();
                
            }
        }
    }
    
    public String updateUserInfo(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
        }
        
        Connection conn = null;
        Statement st = null;
        
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        try{
            
           conn = DriverManager.getConnection(DB_URL, "root", "******");
           st = conn.createStatement();
           
           int u = st.executeUpdate("Update useraccount set gender = '" + userGenderIn + "', age = '" + userAgeIn + 
                   "', city = '" + userCityIn.toUpperCase() + "', interest1 = '" + userInterest1In.toUpperCase() + 
                   "', interest2 = '" + userInterest2In.toUpperCase() + 
                   "', interest3 = '" + userInterest3In.toUpperCase() + "' WHERE loginid = '" + super.getLoginID() + "'");
           //Change the logged in values to the new ones
           super.setGender(capitalizeFirst(userGenderIn)) ;
           super.setAge(userAgeIn);
           super.setCity(capitalizeFirst(userCityIn));
           
           super.setInterest1(capitalizeFirst(userInterest1In)); 
           super.setInterest2(capitalizeFirst(userInterest2In));
           super.setInterest3(capitalizeFirst(userInterest3In));
           return "welcome.xhtml?faces-redirect=true";
           
            
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
            
        }
        finally{
            try{
                conn.close();
                st.close();
                
            }
            catch(Exception e){
                e.printStackTrace();
                
            }
        }
    }
    
    public String resetPass(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
        }
        
        boolean oldPass = false;
        boolean newPass = false;
        
        if(oldPassword.equals(super.getPassword())){
            oldPass = true;
        }
        if(newPassword1.equals(newPassword2)){
            newPass = true;
        }
        if(!oldPass){
            return "errors/wrongResetPassword.xhtml";
        }
        else if(!newPass){
            return "errors/wrongResetPassword.xhtml";
        }
        else{
            
            Connection conn = null;
            Statement st = null;

            final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
            try{

               conn = DriverManager.getConnection(DB_URL, "root", "******");
               st = conn.createStatement();
               
               int u = st.executeUpdate("Update useraccount set psw = '" + newPassword1 + "' WHERE loginid = '" + super.getLoginID() + "'");
               super.setPassword(newPassword1);
               oldPassword = "";
               newPassword1 = "";
               newPassword2 = "";
               return "confirmation/confirmationPassReset.xhtml";
               
            }
            catch(Exception e){
            e.printStackTrace();
            return "errors/internalError.xhtml";
            
            }
            finally{
                try{
                    conn.close();
                    st.close();

                }
                catch(Exception e){
                    e.printStackTrace();

                }
            }
        }
        
    }
    
    public String logOut()
    {
        //If true, the logout time has been updated
        boolean logedout = loggedOut();
        if(!logedout){
            return "errors/logoutError.xhtml?faces-redirect=true";
        }
        else{
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "index.xhtml?faces-redirect=true";
        }
        
    }
   
    
    //This method updates the time when the user logs out
    public boolean loggedOut(){
        //load the jdbc driver, registered in web application 
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        Connection conn = null;
        Statement st = null;
        
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/alqahtania2720?useSSL=false";
        
        String dateTime = DateAndTime.DateTime();
        
        try{
                conn = DriverManager.getConnection(DB_URL, "root", "******");
                st = conn.createStatement();

                int t = st.executeUpdate("Update useraccount set loggedoff = '" + dateTime + "' WHERE loginid = '" + super.getLoginID() + "'");
                return true;
            }
            catch(Exception e){
                e.printStackTrace();
                return false;
            }
            finally{
                try{
                    conn.close();
                    st.close();

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
    
    public static Integer tryParse(String input){
        try{
            return Integer.parseInt(input);
        }
        catch(NumberFormatException e){
            return -1;
        }
    }
}
