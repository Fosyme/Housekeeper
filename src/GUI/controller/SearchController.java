package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class SearchController<TableData> {

    @FXML
    private TextField chackTexField;

    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<TableData, String> check_idColumn;

    @FXML
    private TableColumn<TableData, String> check_typeColumn;

    @FXML
    private TableColumn<TableData, String> check_moneyColumn;

    @FXML
    private TableColumn<TableData, String> check_classificationColumn;

    @FXML
    private TableColumn<TableData, String> check_memoColumn;

    @FXML
    private TableColumn<TableData, String> check_dateColumn;

    @FXML
    void searchButtonEvent(ActionEvent event) {

    }

}
