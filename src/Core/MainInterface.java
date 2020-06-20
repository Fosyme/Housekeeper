package Core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * 主程序, 用于操作主界面
 * @author Fosyme
 * @version 1.0.0
 * */
public class MainInterface {
    private final User user;

    public MainInterface(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    //初始化账本列表
    public ObservableList<Book> initializeBookData() {
        List<Book> list = user.getBooks();
        return FXCollections.observableList(list);
    }

    //设置账单列表
    public ObservableList<Order> getOrderOfBook(int index) {
        ArrayList<Order> list = user.getOrders().get(index);
        return FXCollections.observableArrayList(list);
    }

    //刷新账本列表
    public ObservableList<Book> refreshBookData() {
        List<Book> list = user.getBooks();
        return FXCollections.observableList(list);
    }
}
