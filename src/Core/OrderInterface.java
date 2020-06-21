package Core;

import Dao.OrderOperation;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;

public class OrderInterface {
    private final User user;

    public OrderInterface(User user) {
        this.user = user;
    }

    public static void setOrderOfUser(ArrayList<Order> orders, ResultSet ordersRS) {
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

    public User getUser() {
        return user;
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
    public boolean addOrder(int bookIndex, @NotNull String[] orderMsg) {
        if (orderMsg.length != 7) {
            return false;
        }
        String[] orderInfo = new String[9];
        orderInfo[0] = user.getBooks().get(bookIndex).getBookID();
        System.arraycopy(orderMsg, 0, orderInfo, 1, 4);
        try {
            orderInfo[5] = String.valueOf(Order.dateFormat.parse(orderMsg[4]).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.arraycopy(orderMsg, 5, orderInfo, 6, 2);
        orderInfo[8] = null;
        String orderID = OrderOperation.addOrder(orderInfo);
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
        if (OrderOperation.deleteOrder(deletingOrderID)) {
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
    public boolean alterOrder(int bookIndex, int orderIndex, @NotNull String[] newOrderMsg) {
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
        if (OrderOperation.changeOrderInfo(alteringOrderID, newOrderInfo)) {
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
    public ArrayList<Order> fuzzyQueryOrder(String keyword) {
        //搜索出来的账单
        ArrayList<Order> orders = new ArrayList<>();
        user.getBooks().forEach(book -> {
            ResultSet ordersRS = OrderOperation.fuzzyQueryOrderMsg(book.getBookID(), keyword);
            setOrderOfUser(orders, ordersRS);
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
    public ArrayList<Order> queryPeriodOrder(String startTime, String endTime) {
        ArrayList<Order> orders = new ArrayList<>();
        user.getBooks().forEach(book -> {
            ResultSet ordersRS = OrderOperation.queryTimeInterval(book.getBookID(), startTime, endTime);
            setOrderOfUser(orders, ordersRS);
        });
        return orders;
    }
}
