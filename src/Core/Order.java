package Core;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Order {
    private final String orderID;   //账单ID
    private String bookID;          //所属账本ID
    private String orderName;       //账单名
    private double orderPrice;      //账单金额
    private String orderWay;        //账单支付方式
    private String orderMod;        //账单收支模式
    private String orderTime;       //账单时间(时间戳)
    private String orderDate;       //账单时间
    private String orderCate;       //账单分类
    private String orderDesc;       //账单详细描述
    private byte[] orderImageSrc;   //账单图片

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Order(String orderID) {
        this.orderID = orderID;
    }

    //整体设置order类的实例域
    public void setOrder(@NotNull String[] orderMsg) {
        bookID = orderMsg[0];
        orderName = orderMsg[1];
        orderPrice = Double.parseDouble(orderMsg[2]);
        orderWay = orderMsg[3];
        orderMod = orderMsg[4];
        setOrderTime(orderMsg[5]);
        orderCate = orderMsg[6];
        orderDesc = orderMsg[7];
        orderImageSrc = null;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookID() {
        return bookID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderWay() {
        return orderWay;
    }

    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }

    public String getOrderMod() {
        return orderMod;
    }

    public void setOrderMod(String orderMod) {
        this.orderMod = orderMod;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
        setOrderDate(dateFormat.format(Long.parseLong(orderTime) * 1000));
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderCate() {
        return orderCate;
    }

    public void setOrderCate(String orderCate) {
        this.orderCate = orderCate;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public byte[] getOrderImageSrc() {
        return orderImageSrc;
    }

    public void setOrderImageSrc(byte[] orderImageSrc) {
        this.orderImageSrc = orderImageSrc;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", bookID='" + bookID + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderWay='" + orderWay + '\'' +
                ", orderMod='" + orderMod + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderCate='" + orderCate + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                ", orderImageSrc=" + Arrays.toString(orderImageSrc) +
                '}';
    }
}
