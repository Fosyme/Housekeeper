package GUI.controller;

import Core.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DateSearchController {
    @FXML
    private TableView<Order> tabDateSearch;
    @FXML
    private TableColumn<Order,String> colBookName;
    @FXML
    private TableColumn<Order,String> colOrderName;
    @FXML
    private TableColumn<Order,String> colOrderMod;
    @FXML
    private TableColumn<Order,String> colOrderWay;
    @FXML
    private TableColumn<Order,String> colOrderPrice;
    @FXML
    private TableColumn<Order,String> colOrderCate;
    @FXML
    private TableColumn<Order,String> colOrderDesc;
    @FXML
    private TableColumn<Order,String> colOrderDate;
    @FXML
    private DatePicker starDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Button dateCheckButton;

    @FXML
    void dateCheckButtonEvent(ActionEvent event) {
        String startDate=String.valueOf(starDatePicker.getValue());
        String endDate=String.valueOf(endDatePicker.getValue());
    }
}
