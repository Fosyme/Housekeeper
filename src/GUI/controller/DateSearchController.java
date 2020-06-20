package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class DateSearchController<TableData> {

    @FXML
    private TableColumn<TableData, String> check_classificationColumn;

    @FXML
    private DatePicker starDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableColumn<TableData, String> check_idColumn;

    @FXML
    private TableColumn<TableData, String> check_typeColumn;

    @FXML
    private TableColumn<TableData, String> check_memoColumn;

    @FXML
    private Button dateCheckButton;

    @FXML
    private TableColumn<TableData, String> check_moneyColumn;

    @FXML
    private TableColumn<TableData, String> check_dateColumn;

    @FXML
    void dateCheckButtonEvent(ActionEvent event) {
        String startDate=String.valueOf(starDatePicker.getValue());
        String endDate=String.valueOf(endDatePicker.getValue());


    }


    public void setDialogStage(Stage mainFrameStage) {
    }
}
