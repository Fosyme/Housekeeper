package Core.mutual;

import Core.model.Order;
import Dao.BookOperation;
import Dao.OrderOperation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class Info {
    /**
     * 判断账本是否存在
     *
     * @param userID 用户ID
     * @param bookName 账本名
     * @return 账本是否存在
     */
    public boolean isBookExist(String userID, String bookName) {
        //判断同名账本是否存在
        return BookOperation.isExist(userID, bookName);
    }

    /**
     * 添加新的账本
     *
     * @param bookName 账本名
     * @param bookDesc 账本描述
     * @return 账本是否添加成功
     */
    public boolean addBook(String userID, String bookName, String bookDesc) {
        if (bookName.isEmpty()) {
            return false;
        }
        //使用数组将所有信息传递到数据库端
        String[] bookMsg = new String[4];  //对应数据库4列
        bookMsg[0] = userID;
        bookMsg[1] = bookName;
        bookMsg[2] = bookDesc;
        bookMsg[3] = String.valueOf(System.currentTimeMillis() / 1000);
        //添加账本并获取账本ID
        String bookID = BookOperation.add(bookMsg);
        return bookID != null;
    }

    /**
     * 删除账本数据
     *
     * @param bookID 欲删除账本ID
     * @return 账本删除是否成功
     */
    public boolean deleteBook(String bookID) {
        return BookOperation.delete(bookID);
    }

    /**
     * 修改账本信息
     *
     * @param bookID   账本ID
     * @param newBookName 账本新的名称
     * @param newBookDesc 账本新的描述
     * @return 是否修改成功
     */
    public boolean alterBook(String bookID, String newBookName, String newBookDesc) {
        if (newBookName.isEmpty()) {
            return false;
        }
        String[] newBookMsg = new String[3];
        newBookMsg[0] = newBookName;
        newBookMsg[1] = newBookDesc;
        newBookMsg[2] = String.valueOf(System.currentTimeMillis() / 1000);
        return BookOperation.changeInfo(bookID, newBookMsg);
    }

    /**
     * 添加新的账单
     *
     * @param bookID 欲添加账本ID
     * @param orderMsg  账单信息
     *                  账单名, 金额, 方式(现金, 支付宝, 微信, 银行卡, 信用卡, 其他),
     *                  收支模式(收入, 支出), 时间, 分类, 描述
     * @return 账单是否添加成功
     */
    public boolean addOrder(String bookID, String[] orderMsg) {
        String[] orderInfo = new String[9];
        orderInfo[0] = bookID;
        System.arraycopy(orderMsg, 0, orderInfo, 1, 7);
        try {
            orderInfo[5] = String.valueOf(Data.dateFormat.parse(orderMsg[4]).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orderInfo[8] = "";
        return OrderOperation.add(orderInfo);
    }

    /**
     * 删除账单
     *
     * @param orderID 欲删除账单ID
     * @return 账单是否删除成功
     */
    public boolean deleteOrder(String orderID) {
        return OrderOperation.delete(orderID);
    }

    /**
     * 修改账单
     *
     * @param orderID  欲修改账单ID
     * @param newOrderMsg 账单信息
     *                    账单名, 金额, 方式(现金, 支付宝, 微信, 银行卡, 信用卡, 其他),
     *                    收支模式(收入, 支出), 时间, 分类, 描述
     * @return 账单是否修改成功
     */
    public boolean alterOrder(String orderID, String[] newOrderMsg) {
        String[] newOrderInfo = new String[8];
        System.arraycopy(newOrderMsg, 0, newOrderInfo, 0, 7);
        try {
            newOrderInfo[4] = String.valueOf(Data.dateFormat.parse(newOrderMsg[4]).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newOrderInfo[7] = null;
        return OrderOperation.changeInfo(orderID, newOrderInfo);
    }

    /**
     * 模糊查询账单
     *
     * @param userID  用户ID
     * @param keyword 关键字
     * @return 搜索账单集
     */
    public ArrayList<Order> queryOrder(String userID, String keyword) {
        Data data = new Data();
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet bookRS = BookOperation.queryInfo(userID);
        String bookID;
        ResultSet ordersRS;
        try {
            if (bookRS != null) {
                while (bookRS.next()) {
                    bookID = bookRS.getString("book_id");
                    ordersRS = OrderOperation.fuzzyQueryInfo(bookID, keyword);
                    data.fillOrder(orders, ordersRS);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    /**
     * 时间段查询账单
     *
     * @param userID    用户ID
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @return 搜索账单集
     */
    public ArrayList<Order> queryOrder(String userID, long startTime, long endTime) {
        Data data = new Data();
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet bookRS = BookOperation.queryInfo(userID);
        String bookID;
        ResultSet ordersRS;
        try {
            if (bookRS != null) {
                while (bookRS.next()) {
                    bookID = bookRS.getString("book_id");
                    ordersRS = OrderOperation.queryTimePeriod(bookID, startTime, endTime);
                    data.fillOrder(orders, ordersRS);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
