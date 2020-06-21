package Core;

import Dao.BookOperation;
import Dao.OrderOperation;
import Dao.UserOperation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 主程序, 用于操作主界面
 *
 * @author Fosyme
 * @version 1.0.0
 */
public class MainInterface {
    private final User user;

    public MainInterface(User user) {
        this.user = user;
        initialization();
    }

    public User getUser() {
        return user;
    }

    //初始化账本列表
    public ObservableList<Book> initializeBookData() {
        List<Book> list = user.getBooks();
        return FXCollections.observableList(list);
    }

    //设置账单列表
    public ObservableList<Order> getOrderOfBook(int index) {
        ArrayList<Order> list = user.getOrders().get(index);
        return FXCollections.observableArrayList(list);
    }

    //刷新账本列表
    public ObservableList<Book> refreshBookData() {
        List<Book> list = user.getBooks();
        return FXCollections.observableList(list);
    }

    //初始化方法, 用户登录时将用户所有数据存放到类中
    private void initialization() {
        ResultSet userRS;   //用户信息结果集
        ResultSet bookRS;   //用户账本结果集
        ResultSet ordersRS; //一个账本的账单结果集

        try {
            //将用户数据从数据库中提取到用户类中
            userRS = UserOperation.queryUserMsg(user.getUserID());
            if (userRS.next()) {
                user.setUserName(userRS.getString("user_name"));
                user.setUserRegTime(userRS.getString("user_reg_time"));
                user.setUserLastTime(userRS.getString("user_last_time"));
                user.setUserSex(userRS.getString("user_sex"));
                user.setUserAge(userRS.getInt("user_age"));
                user.setUserPhone(userRS.getString("user_phone"));
                user.setUserAddress(userRS.getString("user_address"));
                user.setUserHeadThumb(userRS.getBytes("user_head_thumb"));
            }
            //在数据库中搜索用户账本信息, 将一个账本信息保存到账本类中, 将用户的所有账本信息保存到ArrayList中
            bookRS = BookOperation.queryBookMsg(user.getUserID());
            while (bookRS.next()) {
                Book book = new Book(bookRS.getString("book_id"));
                book.setBookName(bookRS.getString("book_name"));
                book.setBookDesc(bookRS.getString("book_desc"));
                book.setBookAddTime(bookRS.getString("book_add_time"));
                user.getBooks().add(book);
                //在数据库中搜索一个账本的所有账单信息, 将一个账单信息保存到账单类中, 将一个账本的所有账单保存到ArrayList中
                ordersRS = OrderOperation.queryOrderMsg(bookRS.getString("book_id"));
                ArrayList<Order> orders = new ArrayList<>();
                OrderInterface.setOrderOfUser(orders, ordersRS);
                user.getOrders().add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
