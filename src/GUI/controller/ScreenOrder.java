package GUI.controller;

import Core.model.Order;
import Core.mutual.Info;
import Core.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ScreenOrder {
    Info info;
    @FXML
    private TableView<Order> tabScreen;
    @FXML
    private TableColumn<Order, String> colBookName;
    @FXML
    private TableColumn<Order, String> colOrderName;
    @FXML
    private TableColumn<Order, String> colOrderMod;
    @FXML
    private TableColumn<Order, String> colOrderWay;
    @FXML
    private TableColumn<Order, String> colOrderPrice;
    @FXML
    private TableColumn<Order, String> colOrderCate;
    @FXML
    private TableColumn<Order, String> colOrderDesc;
    @FXML
    private TableColumn<Order, String> colOrderDate;

    public void initialization(User user) {
        info = new Info(user);
    }

    public void setTabScreen(ArrayList<Order> orders) {
        ObservableList<Order> list = FXCollections.observableList(orders);
        tabScreen.setItems(list);
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colOrderName.setCellValueFactory(new PropertyValueFactory<>("orderName"));
        colOrderMod.setCellValueFactory(new PropertyValueFactory<>("orderMod"));
        colOrderWay.setCellValueFactory(new PropertyValueFactory<>("orderWay"));
        colOrderPrice.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));
        colOrderCate.setCellValueFactory(new PropertyValueFactory<>("OrderCate"));
        colOrderDesc.setCellValueFactory(new PropertyValueFactory<>("OrderDesc"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    }
}
