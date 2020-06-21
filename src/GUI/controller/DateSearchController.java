package GUI.controller;

import Core.Order;
import Core.OrderInterface;
import Core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.text.ParseException;

public class DateSearchController {
    private OrderInterface orderInterface;

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
        try {
            String startTime = String.valueOf(Order.dateFormat.parse(startDate).getTime() / 1000);
            String endTime = String.valueOf(Order.dateFormat.parse(endDate).getTime() / 1000);
            orderInterface.queryPeriodOrder(startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialization(User user) {
        orderInterface = new OrderInterface(user);
    }
}
