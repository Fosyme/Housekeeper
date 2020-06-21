package Core;

import Dao.BookOperation;
import Dao.OrderOperation;
import Dao.UserOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 用户类, 管理用户的所有的信息
 * @author Fosyme(向世杰)
 * */
public class User {
    private final String userID;    //用户ID
    private String userName;        //用户名
    private String userRegTime;     //用户注册时间
    private String userLastTime;    //用户上次登录时间
    private String userSex;         //用户性别
    private int userAge;            //用户年龄
    private String userPhone;       //用户手机号
    private String userAddress;     //用户地址
    private byte[] userHeadThumb;   //用户头像

    //账本信息
    private final ArrayList<Book> books;
    //账单信息
    private final ArrayList<ArrayList<Order>> orders;

    {
        userName = null;
        userRegTime = null;
        userLastTime = null;
        userSex = null;
        userAge = 0;
        userPhone = null;
        userAddress = null;
        userHeadThumb = null;
        books = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public User(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRegTime() {
        return userRegTime;
    }

    public void setUserRegTime(String userRegTime) {
        this.userRegTime = userRegTime;
    }

    public String getUserLastTime() {
        return userLastTime;
    }

    public void setUserLastTime(String userLastTime) {
        this.userLastTime = userLastTime;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public byte[] getUserHeadThumb() {
        return userHeadThumb;
    }

    public void setUserHeadThumb(byte[] userHeadThumb) {
        this.userHeadThumb = userHeadThumb;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<ArrayList<Order>> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", userRegTime='" + userRegTime + '\'' +
                ", userLastTime='" + userLastTime + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAge=" + userAge +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userHeadThumb=" + Arrays.toString(userHeadThumb) +
                ", books=" + books +
                ", orders=" + orders +
                '}';
    }
}
