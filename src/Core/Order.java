package Core;

import java.util.Arrays;

public class Order {
    private final int orderID;      //账单ID

    private int bookID;             //所属账本ID
    private String orderName;       //账单名
    private double orderPrice;      //账单金额
    private String orderWay;        //账单支付方式
    private String orderMod;        //账单收支模式
    private String orderTime;       //账单时间
    private String orderCate;       //账单分类
    private String orderDesc;       //账单详细描述
    private byte[] orderImageSrc;   //账单图片

    public Order(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
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
                "orderID=" + orderID +
                ", bookID=" + bookID +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderWay='" + orderWay + '\'' +
                ", orderMod='" + orderMod + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderCate='" + orderCate + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                ", orderImageSrc=" + Arrays.toString(orderImageSrc) +
                '}';
    }
}
