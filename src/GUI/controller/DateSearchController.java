package GUI.controller;

import Core.model.Order;
import GUI.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DateSearchController extends Controller {
    @FXML
    private TableView<Order> tabDateSearch;
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
    @FXML
    private DatePicker starDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Button dateCheckButton;

    @FXML
    void dateCheckButtonEvent(ActionEvent event) {
        String startDate = String.valueOf(starDatePicker.getValue());
        String endDate = String.valueOf(endDatePicker.getValue());
        try {
            long startTime = Order.dateFormat.parse(startDate).getTime() / 1000;
            long endTime = Order.dateFormat.parse(endDate).getTime() / 1000;
            ArrayList<Order> orders = info.queryOrder(startTime, endTime);
            ObservableList<Order> list = FXCollections.observableList(orders);
            tabDateSearch.setItems(list);
            colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            colOrderName.setCellValueFactory(new PropertyValueFactory<>("orderName"));
            colOrderMod.setCellValueFactory(new PropertyValueFactory<>("orderMod"));
            colOrderWay.setCellValueFactory(new PropertyValueFactory<>("orderWay"));
            colOrderPrice.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));
            colOrderCate.setCellValueFactory(new PropertyValueFactory<>("OrderCate"));
            colOrderDesc.setCellValueFactory(new PropertyValueFactory<>("OrderDesc"));
            colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
