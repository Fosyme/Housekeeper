package Core;

import Dao.BookOperation;

import java.util.ArrayList;

public class BookInterface {
    private final User user;

    public BookInterface(User user) {
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
        return BookOperation.checkBookExist(user.getUserID(), bookName);
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
        String bookID = BookOperation.addBook(bookMsg);
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
        if (BookOperation.deleteBook(bookID)) {
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
        if (BookOperation.changeBookInfo(bookID, newBookMsg)) {
            Book book = user.getBooks().get(bookIndex);
            book.setBookName(newBookName);
            book.setBookDesc(newBookDesc);
            return true;
        }
        return false;
    }
}
