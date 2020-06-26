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
    private ToggleGroup sex;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton secrecy;
    @FXML
    private RadioButton female;
    @FXML
    private TextField userAge;
    @FXML
    private TextField userPhone;
    @FXML
    private TextField userAddress;
    @FXML
    private Button userChange;
    @FXML
    private Button userClose;

    @FXML
    void UserChangeEvent(ActionEvent event) {
        //更改按钮
    }

    @Override
    public void initialize(User user) {
        UserName.setText(user.getUserName());
        if (user.getUserSex().equals("男")) {
            male.setSelected(true);
        } else if (user.getUserSex().equals("女")) {
            female.setSelected(true);
        } else {
            secrecy.setSelected(true);
        }
        userAge.setText(String.valueOf(user.getUserAge()));
        userPhone.setText(user.getUserPhone());
        userAddress.setText(user.getUserAddress());
    }
}
