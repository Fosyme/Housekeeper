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
 * @author Fosyme
 * @version 1.0.0
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
    private ArrayList<Book> books;
    //账单信息
    private ArrayList<ArrayList<Order>> orders;

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
        initialization();
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

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<ArrayList<Order>> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<ArrayList<Order>> orders) {
        this.orders = orders;
    }

    //初始化方法, 用户登录时将用户所有数据存放到类中
    private void initialization() {
        ResultSet userRS;   //用户信息结果集
        ResultSet bookRS;   //用户账本结果集
        ResultSet ordersRS; //一个账本的账单结果集

        try {
            //将用户数据从数据库中提取到用户类中
            userRS = UserOperation.queryUserMsg(userID);
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
            //在数据库中搜索用户账本信息, 将一个账本信息保存到账本类中, 将用户的所有账本信息保存到ArrayList中
            bookRS = BookOperation.queryBookMsg(userID);
            while (bookRS.next()) {
                Book book = new Book(bookRS.getInt("book_id"));
                book.setBookName(bookRS.getString("book_name"));
                book.setBookDesc(bookRS.getString("book_desc"));
                book.setBookAddTime(bookRS.getString("book_add_time"));
                book.setBookLastTime(bookRS.getString("book_last_time"));
                books.add(book);
                //在数据库中搜索一个账本的所有账单信息, 将一个账单信息保存到账单类中, 将一个账本的所有账单保存到ArrayList中
                ordersRS = OrderOperation.queryOrderMsg(bookRS.getString("book_id"));
                ArrayList<Order> arrayList = new ArrayList<>();
                while (ordersRS.next()) {
                    Order order = new Order(ordersRS.getInt("order_id"));
                    order.setBookID(ordersRS.getInt("book_id"));
                    order.setOrderName(ordersRS.getString("order_name"));
                    order.setOrderPrice(ordersRS.getDouble("order_price"));
                    order.setOrderWay(ordersRS.getString("order_way"));
                    order.setOrderMod(ordersRS.getString("order_mod"));
                    order.setOrderTime(ordersRS.getString("order_time"));
                    order.setOrderCate(ordersRS.getString("order_cate"));
                    order.setOrderDesc(ordersRS.getString("order_desc"));
                    order.setOrderImageSrc(ordersRS.getBytes("order_image_src"));
                    arrayList.add(order);
                }
                orders.add(arrayList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
