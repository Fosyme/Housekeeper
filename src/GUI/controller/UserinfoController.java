package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class UserinfoController {
    private Stage dialogStage;
    public Stage getDialogStage() {
        return dialogStage;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private Label UserName;

    @FXML
    private RadioButton Man;

    @FXML
    private ToggleGroup sex;

    @FXML
    private RadioButton Woman;

    @FXML
    private TextField UserAge;

    @FXML
    private TextField UserNUmber;

    @FXML
    private TextField UserAddress;

    @FXML
    private PasswordField UserPassword;

    @FXML
    private Button UserChange;

    @FXML
    private Button UserClose;

    @FXML
    void UserChangeEvent(ActionEvent event) {
        //更改按钮
    }

    @FXML
    void UserCloseEvent(ActionEvent event) {
        dialogStage.close();
    }


}
