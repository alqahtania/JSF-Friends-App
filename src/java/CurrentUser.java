/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah Alqahtani
 */
public abstract class CurrentUser {
    private int userID;
    private String loginID;
    private String password;
    private String gender;
    private int age;
    private String city;
    private String interest1;
    private String interest2;
    private String interest3;
    private int views;
    private String lastOnline;

    public CurrentUser() {
    }

    public CurrentUser(int userID, String loginID, String password, String gender, int age, String city, String interest1, String interest2, String interest3, int views, String lastOnline) {
        this.userID = userID;
        this.loginID = loginID;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.interest1 = interest1;
        this.interest2 = interest2;
        this.interest3 = interest3;
        this.views = views;
        this.lastOnline = lastOnline;
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
    
    

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInterest1() {
        return interest1;
    }

    public void setInterest1(String interest1) {
        this.interest1 = interest1;
    }

    public String getInterest2() {
        return interest2;
    }

    public void setInterest2(String interest2) {
        this.interest2 = interest2;
    }

    public String getInterest3() {
        return interest3;
    }

    public void setInterest3(String interest3) {
        this.interest3 = interest3;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(String lastOnline) {
        this.lastOnline = lastOnline;
    }
}
