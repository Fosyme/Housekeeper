package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UserinfoController {
    private Stage dialogStage;

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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void UserChangeEvent(ActionEvent event) {
        //更改按钮
    }

    @FXML
    void UserCloseEvent(ActionEvent event) {
        dialogStage.close();
    }
}
