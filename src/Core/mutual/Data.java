package Core.mutual;

import Core.model.User;
import Core.model.Book;
import Core.model.Order;
import Dao.BookOperation;
import Dao.OrderOperation;
import Dao.UserOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Data {
    //初始化方法, 用户登录时将用户所有数据存放到类中
    public static void initialization(User user) {
        ResultSet userRS;   //用户信息结果集
        ResultSet bookRS;   //用户账本结果集
        ResultSet ordersRS; //一个账本的账单结果集
        try {
            //将用户数据从数据库中提取到用户类中
            userRS = UserOperation.queryInfo(user.getUserID());
            if (userRS != null && userRS.next()) {
                user.setUserName(userRS.getString("user_name"));
                user.setUserPassword(userRS.getString("user_password"));
                user.setUserRegTime(userRS.getString("user_reg_time"));
                user.setUserLastTime(userRS.getString("user_last_time"));
                user.setUserSex(userRS.getString("user_sex"));
                user.setUserAge(userRS.getInt("user_age"));
                user.setUserPhone(userRS.getString("user_phone"));
                user.setUserAddress(userRS.getString("user_address"));
                user.setUserHeadThumb(userRS.getBytes("user_head_thumb"));
            }
            //在数据库中搜索用户账本信息, 将一个账本信息保存到账本类中, 将用户的所有账本信息保存到ArrayList中
            bookRS = BookOperation.queryInfo(user.getUserID());
            if (bookRS != null) {
                while (bookRS.next()) {
                    Book book = new Book(bookRS.getString("book_id"));
                    book.setBookName(bookRS.getString("book_name"));
                    book.setBookDesc(bookRS.getString("book_desc"));
                    book.setBookAddTime(bookRS.getString("book_add_time"));
                    user.getBooks().add(book);
                    //在数据库中搜索一个账本的所有账单信息, 将一个账单信息保存到账单类中, 将一个账本的所有账单保存到ArrayList中
                    ordersRS = OrderOperation.queryInfo(bookRS.getString("book_id"));
                    ArrayList<Order> orders = new ArrayList<>();
                    setOrderModel(orders, ordersRS);
                    user.getOrders().add(orders);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //设置用户所有账单信息
    public static void setOrderModel(ArrayList<Order> orders, ResultSet ordersRS) {
        try {
            while (ordersRS.next()) {
                Order order = new Order(ordersRS.getString("order_id"));
                order.setBookID(ordersRS.getString("book_id"));
                order.setOrderName(ordersRS.getString("order_name"));
                order.setOrderPrice(ordersRS.getDouble("order_price"));
                order.setOrderWay(ordersRS.getString("order_way"));
                order.setOrderMod(ordersRS.getString("order_mod"));
                order.setOrderTime(ordersRS.getString("order_time"));
                order.setOrderCate(ordersRS.getString("order_cate"));
                order.setOrderDesc(ordersRS.getString("order_desc"));
                order.setOrderImageSrc(ordersRS.getBytes("order_image_src"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
