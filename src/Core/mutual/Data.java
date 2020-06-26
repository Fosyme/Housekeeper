package Core.mutual;

import Core.model.Book;
import Core.model.Order;
import Core.model.User;
import Dao.BookOperation;
import Dao.OrderOperation;
import Dao.UserOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Data {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 加载用户的信息
     *
     * @param userID 用户ID
     * @return 包含完整用户信息的User对象
     */
    User loadUser(String userID) {
        User user = new User(userID);
        ResultSet userRS = UserOperation.queryInfo(userID); //用户信息结果集;
        try {
            //将用户数据从数据库中提取到用户类中
            if (userRS != null && userRS.next()) {
                user.setUserName(userRS.getString("user_name"));
                user.setUserPassword(userRS.getString("user_pwd"));
                user.setUserRegTime(userRS.getString("user_reg_time"));
                user.setUserLastTime(userRS.getString("user_last_time"));
                user.setUserSex(userRS.getString("user_sex"));
                user.setUserAge(userRS.getInt("user_age"));
                user.setUserPhone(userRS.getString("user_phone"));
                user.setUserAddress(userRS.getString("user_address"));
                user.setUserHeadThumb(userRS.getBytes("user_head_thumb"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 加载用户的账本数据
     *
     * @param userID 用户ID
     * @return 包含用户所有的账本数据
     */
    ArrayList<Book> loadBook(String userID) {
        ArrayList<Book> books = new ArrayList<>();
        ResultSet bookRS = BookOperation.queryInfo(userID); //用户账本结果集
        try {
            if (bookRS != null) {
                while (bookRS.next()) {
                    Book book = new Book(bookRS.getString("book_id"));
                    book.setBookName(bookRS.getString("book_name"));
                    book.setBookDesc(bookRS.getString("book_desc"));
                    book.setBookAddTime(bookRS.getString("book_add_time"));
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * 加载账本的账单数据
     *
     * @param bookID 账本ID
     * @return 包含次账本的所有账单数据
     */
    ArrayList<Order> loadOrder(String bookID) {
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet orderRS = OrderOperation.queryInfo(bookID);
        fillOrder(orders, orderRS);
        return orders;
    }

    /**
     * 静态，账单项填充
     *
     * @param orders  目标账单集
     * @param orderRS 欲添加结果集
     */
    void fillOrder(ArrayList<Order> orders, ResultSet orderRS) {
        try {
            if (orderRS != null) {
                while (orderRS.next()) {
                    Order order = new Order(orderRS.getString("order_id"));
                    order.setBookID(orderRS.getString("book_id"));
                    order.setOrderName(orderRS.getString("order_name"));
                    order.setOrderPrice(orderRS.getDouble("order_price"));
                    order.setOrderWay(orderRS.getString("order_way"));
                    order.setOrderMod(orderRS.getString("order_mod"));
                    order.setOrderTime(orderRS.getString("order_time"));
                    order.setOrderCate(orderRS.getString("order_cate"));
                    order.setOrderDesc(orderRS.getString("order_desc"));
                    order.setOrderImageSrc(orderRS.getBytes("order_image_src"));
                    orders.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
