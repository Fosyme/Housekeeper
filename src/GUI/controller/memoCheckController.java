package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class memoCheckController<TableData> {

    @FXML
    private Button memo_checkButton;

    @FXML
    private TableColumn<TableData, String> memo_classificationColumn;

    @FXML
    private TableColumn<TableData, String> memo_moneyColumn;

    @FXML
    private TableColumn<TableData, String> memo_dateColumn;

    @FXML
    private TextField memo_memoTextField;

    @FXML
    private TableColumn<TableData, String> memo_idColumn;

    @FXML
    private TableColumn<TableData, String> memo_typeColumn;

    @FXML
    private TableColumn<TableData, String> memo_memoColumn;

    @FXML
    void memo_checkButtonEvent(ActionEvent event) {

    }


}
