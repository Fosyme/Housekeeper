package GUI.controller;

import Core.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class DateSearchController {

    @FXML
    private TableView<Order> check_tableView;
    @FXML
    private TableColumn<Order, String>chack_nameColumn;
    @FXML
    private TableColumn<Order, String> check_classificationColumn;

    @FXML
    private DatePicker starDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableColumn<Order, String> check_idColumn;

    @FXML
    private TableColumn<Order, String> check_typeColumn;

    @FXML
    private TableColumn<Order, String> check_memoColumn;

    @FXML
    private Button dateCheckButton;

    @FXML
    private TableColumn<Order, String> check_moneyColumn;

    @FXML
    private TableColumn<Order, String> check_dateColumn;

    @FXML
    void dateCheckButtonEvent(ActionEvent event) {
        String startDate=String.valueOf(starDatePicker.getValue());
        String endDate=String.valueOf(endDatePicker.getValue());
        //通过开始时间和结束时间
        // 返回账单内容

    }


    public void setDialogStage(Stage mainFrameStage) {
    }
}
