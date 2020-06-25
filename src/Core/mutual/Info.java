package Core.mutual;

import Core.model.User;
import Core.model.Book;
import Core.model.Order;
import Dao.BookOperation;
import Dao.OrderOperation;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;

public class Info {
    private final User user;

    public Info(User user) {
        this.user = user;
    }

    /**
     * 判断账本是否存在
     *
     * @param bookName 账本名
     * @return 账本是否存在
     */
    public boolean isBookExist(String bookName) {
        //判断同名账本是否存在
        return BookOperation.isExist(user.getUserID(), bookName);
    }

    /**
     * 添加新的账本
     *
     * @param bookName 账本名
     * @param bookDesc 账本描述
     * @return 账本是否添加成功
     */
    public boolean addBook(String bookName, String bookDesc) {
        if (bookName.isEmpty()) {
            return false;
        }
        //使用数组将所有信息传递到数据库端
        String[] bookMsg = new String[4];  //对应数据库4列
        bookMsg[0] = user.getUserID();
        bookMsg[1] = bookName;
        bookMsg[2] = bookDesc;
        bookMsg[3] = String.valueOf(System.currentTimeMillis() / 1000);
        //添加账本并获取账本ID
        String bookID = BookOperation.add(bookMsg);
        if (bookID == null) {
            return false;
        }
        Book book = new Book(bookID);
        book.setBook(bookMsg);
        //添加账本和一个账单集
        user.getBooks().add(book);
        user.getOrders().add(new ArrayList<>());
        return true;
    }

    /**
     * 删除账本数据
     *
     * @param bookIndex 欲删除账本在展示列表中的索引
     * @return 账本删除是否成功
     */
    public boolean deleteBook(int bookIndex) {
        String bookID = user.getBooks().get(bookIndex).getBookID();
        if (BookOperation.delete(bookID)) {
            user.getBooks().remove(bookIndex);
            user.getOrders().remove(bookIndex);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改账本信息
     *
     * @param bookIndex   账本在展示列表中的索引
     * @param newBookName 账本新的名称
     * @param newBookDesc 账本新的描述
     * @return 是否修改成功
     */
    public boolean alterBook(int bookIndex, String newBookName, String newBookDesc) {
        if (newBookName.isEmpty()) {
            return false;
        }
        String[] newBookMsg = new String[3];
        String bookID = user.getBooks().get(bookIndex).getBookID();
        newBookMsg[0] = newBookName;
        newBookMsg[1] = newBookDesc;
        newBookMsg[2] = String.valueOf(System.currentTimeMillis() / 1000);
        if (BookOperation.changeInfo(bookID, newBookMsg)) {
            Book book = user.getBooks().get(bookIndex);
            book.setBookName(newBookName);
            book.setBookDesc(newBookDesc);
            return true;
        }
        return false;
    }

    /**
     * 添加新的账单
     *
     * @param bookIndex 欲添加账单所属账本在展示列表中的索引
     * @param orderMsg  账单信息
     *                  账单名, 金额, 方式(现金, 支付宝, 微信, 银行卡, 信用卡, 其他),
     *                  收支模式(收入, 支出), 时间, 分类, 描述
     * @return 账单是否添加成功
     */
    public boolean addOrder(int bookIndex, String[] orderMsg) {
        String[] orderInfo = new String[9];
        orderInfo[0] = user.getBooks().get(bookIndex).getBookID();
        System.arraycopy(orderMsg, 0, orderInfo, 1, 4);
        try {
            orderInfo[5] = String.valueOf(Order.dateFormat.parse(orderMsg[4]).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.arraycopy(orderMsg, 5, orderInfo, 6, 2);
        orderInfo[8] = "";
        String orderID = OrderOperation.add(orderInfo);
        Order order = new Order(orderID);
        order.setOrder(orderInfo);
        user.getOrders().get(bookIndex).add(order);
        return true;
    }

    /**
     * 删除账单
     *
     * @param bookIndex  欲删除账单所属账本在展示列表中的索引
     * @param orderIndex 欲删除账单在展示列表中的索引
     * @return 账单是否删除成功
     */
    public boolean deleteOrder(int bookIndex, int orderIndex) {
        String deletingOrderID;
        deletingOrderID =
                user.getOrders()
                        .get(bookIndex)
                        .get(orderIndex)
                        .getOrderID();
        if (OrderOperation.delete(deletingOrderID)) {
            user.getOrders().get(bookIndex).remove(orderIndex);
            return true;
        }
        return false;
    }

    /**
     * 修改账单
     *
     * @param bookIndex   欲修改账单所属账本在展示列表中的索引
     * @param orderIndex  欲修改账单在展示列表中的索引
     * @param newOrderMsg 账单信息
     *                    账单名, 金额, 方式(现金, 支付宝, 微信, 银行卡, 信用卡, 其他),
     *                    收支模式(收入, 支出), 时间, 分类, 描述
     * @return 账单是否修改成功
     */
    public boolean alterOrder(int bookIndex, int orderIndex, String[] newOrderMsg) {
        if (newOrderMsg.length != 7) {
            return false;
        }
        String alteringOrderID;
        String[] newOrderInfo = new String[9];
        alteringOrderID =
                user.getOrders()
                        .get(bookIndex)
                        .get(orderIndex)
                        .getOrderID();
        newOrderInfo[0] = user.getBooks().get(bookIndex).getBookID();
        System.arraycopy(newOrderMsg, 0, newOrderInfo, 1, 7);
        try {
            newOrderInfo[5] = String.valueOf(Order.dateFormat.parse(newOrderMsg[4]).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newOrderInfo[8] = null;
        if (OrderOperation.changeInfo(alteringOrderID, newOrderInfo)) {
            user.getOrders().get(bookIndex).get(orderIndex).setOrder(newOrderInfo);
            return true;
        }
        return false;
    }

    /**
     * 模糊查询账单
     *
     * @param keyword 关键字
     * @return 搜索账单集
     */
    public ArrayList<Order> queryOrder(String keyword) {
        //搜索出来的账单
        ArrayList<Order> orders = new ArrayList<>();
        user.getBooks().forEach(book -> {
            ResultSet ordersRS = OrderOperation.fuzzyQueryInfo(book.getBookID(), keyword);
            Data.setOrderModel(orders, ordersRS);
        });
        return orders;
    }

    /**
     * 时间段查询账单
     *
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @return 搜索账单集
     */
    public ArrayList<Order> queryOrder(long startTime, long endTime) {
        ArrayList<Order> orders = new ArrayList<>();
        user.getBooks().forEach(book -> {
            ResultSet ordersRS = OrderOperation.queryTimePeriod(book.getBookID(), startTime, endTime);
            Data.setOrderModel(orders, ordersRS);
        });
        return orders;
    }

}
