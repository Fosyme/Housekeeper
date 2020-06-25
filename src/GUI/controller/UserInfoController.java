package GUI.controller;

import Core.model.User;
import GUI.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserInfoController implements Controller {
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

    @Override
    public void initialize(User user) {

    }
}
