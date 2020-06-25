package Core.model;

import Core.mutual.Data;
import Dao.BookOperation;

import java.util.Arrays;

public class Order {
    private String orderID;   //账单ID
    private String bookID;          //所属账本ID
    private String bookName;        //所属账本名(非数据库)
    private String orderName;       //账单名
    private double orderPrice;      //账单金额
    private String orderWay;        //账单支付方式
    private String orderMod;        //账单收支模式
    private String orderTime;       //账单时间(时间戳)
    private String orderDate;       //账单时间
    private String orderCate;       //账单分类
    private String orderDesc;       //账单详细描述
    private byte[] orderImageSrc;   //账单图片

    public Order() {
    }

    public Order(String orderID) {
        this.orderID = orderID;
    }

    //整体设置order类的实例域
    public void setOrder(String[] orderMsg) {
        setBookID(orderMsg[0]);
        setOrderName(orderMsg[1]);
        setOrderPrice(Double.parseDouble(orderMsg[2]));
        setOrderWay(orderMsg[3]);
        setOrderMod(orderMsg[4]);
        setOrderTime(orderMsg[5]);
        setOrderCate(orderMsg[6]);
        setOrderDesc(orderMsg[7]);
        setOrderImageSrc(orderMsg[8].getBytes());
    }

    public String getOrderID() {
        return orderID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
        setBookName(BookOperation.queryNameFromID(bookID));
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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
        setOrderDate(Data.dateFormat.format(Long.parseLong(orderTime) * 1000));
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
        return "setOrder{" +
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
