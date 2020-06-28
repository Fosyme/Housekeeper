package Core.mutual;

import Core.model.Book;
import Core.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Display {
    private final Data data = new Data();

    //更新账本信息
    public ObservableList<Book> updateBooks(String userID) {
        List<Book> list = data.loadBook(userID);
        return FXCollections.observableList(list);
    }

    //更新账单信息
    public ObservableList<Order> updateOrders(String bookID) {
        ArrayList<Order> list = data.loadOrder(bookID);
        return FXCollections.observableArrayList(list);
    }
}
