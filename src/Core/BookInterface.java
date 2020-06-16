package Core;

import Dao.BookOperation;

public class BookInterface {
    private User user;

    public BookInterface(User user) {
        this.user = user;
    }

    /**
     * 添加账本方法
     *
     * @param bookName 账本名
     * @param bookDesc 账本描述
     * */
    public void addBook(String bookName, String bookDesc) {
        String[] bookMsg = new String[5];  //对应数据库5列
        bookMsg[0] = user.getUserID();
        bookMsg[1] = bookName;
        bookMsg[2] = bookDesc;
        bookMsg[3] = String.valueOf(System.currentTimeMillis() / 1000);
        bookMsg[4] = bookMsg[3];
        BookOperation.addBook(bookMsg);
        //TODO 加入到用户类中的账本类中
    }

    public void deleteBook(String index) {

    }

    public void alterBook(String index, String newBookName, String newBookDesc) {

    }
}
