package Core;

import Dao.BookOperation;
import Dao.OrderOperation;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

public class OrderInterface {
    private final User user;

    public OrderInterface(User user) {
        this.user = user;
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
        return BookOperation.deleteBook(deletingOrderID);
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
        String[] newOrderInfo = new String[8];
        alteringOrderID =
                user.getOrders()
                        .get(bookIndex)
                        .get(orderIndex)
                        .getOrderID();
        System.arraycopy(newOrderMsg, 0, newOrderInfo, 0, 7);
        newOrderInfo[7] = null;
        return OrderOperation.changeOrderInfo(alteringOrderID, newOrderInfo);
    }
}
