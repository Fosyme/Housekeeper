package GUI.controller;

import Core.model.Order;
import GUI.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScreenOrder extends Controller {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
