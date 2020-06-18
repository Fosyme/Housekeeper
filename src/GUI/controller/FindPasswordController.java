package GUI.controller;

import Core.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class FindPasswordController {
    private UserLogin userLogin;
    private String userName;


    @FXML
    public Label lblQuestion;

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
    public void initialization() {
        userLogin = new UserLogin();
    }

    @FXML
    void ConfirmButtonEvent(ActionEvent event) {

       boolean a=userLogin.recoverPassword(userName,AnswerTextField.getText(),NewPasswordTexField.getText());
       if (a){
           JOptionPane.showMessageDialog(null,"找回密码成功","找回密码",JOptionPane.INFORMATION_MESSAGE);
        }else {
           JOptionPane.showMessageDialog(null,"回答不正确","警告",JOptionPane.ERROR_MESSAGE);
       }
    }

    public void setContent(String userName, String question) {
        this.userName = userName;
        lblQuestion.setText(question);
    }
}
