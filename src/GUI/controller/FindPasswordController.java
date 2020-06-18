package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FindPasswordController {

    @FXML
    private TextField NewPasswordTexField;

    @FXML
    private TextField AnswerTextField;

    @FXML
    private ComboBox<?> FindPasswordComboBox;

    @FXML
    private Button ConfirmButton;

    @FXML
    void FindPasswordComboBoxEvent(ActionEvent event) {

    }

    @FXML
    void ConfirmButtonEvent(ActionEvent event) {

    }


    public void setDialogStage(Stage mainFrameStage) {
    }
}
