package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddOrderController {

    @FXML
    private TextField moneyTextField;

    @FXML
    private ComboBox<?> classificationComboBox;

    @FXML
    private DatePicker datePickerTextField;

    @FXML
    private Button addButton;

    @FXML
    private TextArea memoTextArea;

    @FXML
    private RadioButton outputRadioButton;

    @FXML
    private ToggleGroup RadioButton;

    @FXML
    private RadioButton inputRadioButton;

    @FXML
    private ComboBox<?> accountbookComboBox;

    @FXML
    void accountbookComboBoxEvent(ActionEvent event) {

    }

    @FXML
    void addButtonEvent(ActionEvent event) {

    }

    @FXML
    void classificationComboBoxEvetn(ActionEvent event) {

    }

    @FXML
    void inputRadioButtonEvent(ActionEvent event) {

    }

    @FXML
    void outputRadioButtonEvent(ActionEvent event) {

    }


    public void setDialogStage(Stage mainFrameStage) {
    }
}
