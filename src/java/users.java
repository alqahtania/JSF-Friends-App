/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah Alqahtani
 */
public class users {
    private int userID;
    private String loginID;
    private String gender;
    private int age;
    private String city;
    private String interest1;
    private String interest2;
    private String interest3;
    private String lastOnline;
    private int views;
    //Used in the friend requests received/sent methods for status
    private String friendStatus;

    public users(int userID, String loginID, String gender, int age, String city, String interest1, String interest2, String interest3, String lastOnline, int views, String friendStatus) {
        this.userID = userID;
        this.loginID = loginID.substring(0, 1).toUpperCase() + loginID.substring(1);
        this.gender = gender.substring(0, 1).toUpperCase() + gender.substring(1);
        this.age = age;
        this.city = city.substring(0, 1).toUpperCase() + city.substring(1);
        this.interest1 = interest1.substring(0, 1).toUpperCase() + interest1.substring(1);
        this.interest2 = interest2.substring(0, 1).toUpperCase() + interest2.substring(1);
        this.interest3 = interest3.substring(0, 1).toUpperCase() + interest3.substring(1);
        this.lastOnline = lastOnline;
        this.views = views;
        this.friendStatus = friendStatus.substring(0, 1).toUpperCase() + friendStatus.substring(1).toLowerCase();
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

    public String getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(String lastOnline) {
        this.lastOnline = lastOnline;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(String friendStatus) {
        this.friendStatus = friendStatus;
    }
    
}
