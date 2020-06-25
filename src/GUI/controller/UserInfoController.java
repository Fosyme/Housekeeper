package GUI.controller;

import GUI.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController extends Controller {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
