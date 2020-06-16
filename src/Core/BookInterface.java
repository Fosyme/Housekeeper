package Core;

import Dao.BookOperation;

public class BookInterface {
    private User user;

    public BookInterface(User user) {
        this.user = user;
    }

    /**
     * @param bookMsg 用户id、账本名、账本、描述、添加时间、最后修改时间
     * */
    public void addBook(String[] bookMsg) {
//        BookOperation.addBook();
    }

    public void deleteBook() {

    }

    public void alterBook() {

    }
}
