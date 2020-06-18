package Core;

import org.jetbrains.annotations.NotNull;

public class Book {
    private final String bookID;       //账本ID
//    private String userID;          //所属用户ID, 可舍去
    private String bookName;        //账本名
    private String bookDesc;        //账本描述
    private String bookAddTime;     //账本添加时间

    public Book(String bookID) {
        this.bookID = bookID;
    }

    //整体设置book类的实例域
    public void setBook(@NotNull String[] bookMsg) {
            bookName = bookMsg[1];
            bookDesc = bookMsg[2];
            bookAddTime = bookMsg[3];
    }

    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getBookAddTime() {
        return bookAddTime;
    }

    public void setBookAddTime(String bookAddTime) {
        this.bookAddTime = bookAddTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID='" + bookID + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookDesc='" + bookDesc + '\'' +
                ", bookAddTime='" + bookAddTime + '\'' +
                '}';
    }
}
