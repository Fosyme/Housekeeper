package Core;

import Dao.AccessOperation;
import com.sun.javafx.binding.StringFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private final String userID;                        //用户ID
    private String userName;                            //用户名
    private String userRegTime;                         //用户注册时间
    private String userLastTime;                        //用户上次登录时间
    private String userSex;                             //用户性别
    private int userAge;                                //用户年龄
    private String userPhone;                           //用户手机号
    private String userAddress;                         //用户地址
    private byte[] userHeadThumb;                       //用户头像
    private HashMap<String, ArrayList<String>> booksID; //账本ID
    private ArrayList<String>[] ordersID;               //账单ID


    {
        userName = null;
        userRegTime = null;
        userLastTime = null;
        userSex = null;
        userAge = 0;
        userPhone = null;
        userAddress = null;
        userHeadThumb = null;
        booksID = null;
        booksID = new HashMap<>();
        ordersID = null;
    }

    public User(String userID) {
        this.userID = userID;
        initialization();
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserRegTime() {
        return userRegTime;
    }

    public String getUserLastTime() {
        return userLastTime;
    }

    public String getUserSex() {
        return userSex;
    }

    public int getUserAge() {
        return userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public byte[] getUserHeadThumb() {
        return userHeadThumb;
    }

    public HashMap<String, ArrayList<String>> getBooksID() {
        return booksID;
    }

    public ArrayList<String>[] getOrdersID() {
        return ordersID;
    }

    //初始化方法, 用户登录时将用户所有数据存放到类中
     private void initialization() {
         ResultSet userRS;
         ResultSet bookRS;
         ResultSet[] ordersRS;

         try {
             userRS = AccessOperation.query("user", userID);
             if (userRS.next()) {
                 userName = userRS.getString("user_name");
                 userRegTime = userRS.getString("user_reg_time");
                 userLastTime = userRS.getString("user_last_time");
                 userSex = userRS.getString("user_sex");
                 userAge = userRS.getInt("user_age");
                 userPhone = userRS.getString("user_phone");
                 userAddress = userRS.getString("user_address");
                 userHeadThumb = userRS.getBytes("user_head_thumb");
             }
             bookRS = AccessOperation.query("book", userID);
             bookRS.last();
             int rsLength = bookRS.getRow();
             ordersID = new ArrayList[rsLength];
             ordersRS = new ResultSet[rsLength];
             bookRS.beforeFirst();
             for (int i = 0; bookRS.next(); i++) {
                 booksID.put(bookRS.getString("book_id"), ordersID[i]);
                 ordersRS[i] = AccessOperation.query("order", bookRS.getString("book_id"));
                 ordersID[i] = new ArrayList<>();
                 while (ordersRS[i].next()) {
                     //TODO 这个查询的方法有问题，导致只能从一个表中查询索引ID的结果，始终为一项，不符合要求
                     String temp = ordersRS[i].getString("order_name");
                     ordersID[i].add(temp);
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }
}
