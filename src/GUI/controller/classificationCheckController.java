package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

public class classificationCheckController<TableData> {

    @FXML
    private ComboBox<?> outputClassificationComboBox;

    @FXML
    private TableColumn<TableData, String> classification_idColumn;

    @FXML
    private TableColumn<TableData, String> classification_typeColumn1;

    @FXML
    private TableColumn<TableData, String> classification_dateColumn1;

    @FXML
    private TableColumn<TableData, String> classification_memoColumn1;

    @FXML
    private TableColumn<TableData, String> classification_typeColumn;

    @FXML
    private TableColumn<TableData, String> classification_classificationColumn;

    @FXML
    private TableColumn<TableData, String> classification_memoColumn;

    @FXML
    private ComboBox<?> inputClassificationComboBox;

    @FXML
    private TableColumn<TableData, String> classification_moneyColumn1;

    @FXML
    private TableColumn<TableData, String> classification_classificationColumn1;

    @FXML
    private TableColumn<TableData, String> classification_moneyColumn;

    @FXML
    private TableColumn<TableData, String> classification_idColumn1;

    @FXML
    private TableColumn<TableData, String> classification_dateColumn;

    @FXML
    void outputClassificationComboBoxEvent(ActionEvent event) {

    }

    @FXML
    void inputClassificationComboBoxEvent(ActionEvent event) {

    }


}
