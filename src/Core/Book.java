package Core;

public class Book {
    private final int bookID;       //账本ID
//    private String userID;          //所属用户ID, 可舍去
    private String bookName;        //账本名
    private String bookDesc;        //账本描述
    private String bookAddTime;     //账本添加时间
    private String bookLastTime;    //账本最后修改时间

    public Book(int book_id) {
        this.bookID = book_id;
    }

    public int getBookID() {
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

    public String getBookLastTime() {
        return bookLastTime;
    }

    public void setBookLastTime(String bookLastTime) {
        this.bookLastTime = bookLastTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", bookDesc='" + bookDesc + '\'' +
                ", bookAddTime='" + bookAddTime + '\'' +
                ", bookLastTime='" + bookLastTime + '\'' +
                '}';
    }
}
