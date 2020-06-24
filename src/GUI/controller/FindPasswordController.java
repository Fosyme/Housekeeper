package GUI.controller;

import Core.mutual.Login;
import GUI.OpenFormAfterThis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FindPasswordController {
    private Login login;
    private String userName;

    @FXML
    public AnchorPane paneFindPwd;

    @FXML
    public Label lblQuestion;

    @FXML
    private TextField NewPasswordTexField;

    @FXML
    private TextField AnswerTextField;


    public void initialization() {
        login = new Login();
    }

    @FXML
    void ConfirmButtonEvent(ActionEvent event) {
        String encryptedAnswer = AnswerTextField.getText();
        String newPassword = NewPasswordTexField.getText();
        boolean b = login.recoverPassword(userName, encryptedAnswer, newPassword);
        if (b) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(null);
            alert.setHeaderText("密码修改成功！");
            alert.setContentText("请记住你的密码是：" + newPassword);
            alert.showAndWait();
            ((Stage) paneFindPwd.getScene().getWindow()).close();
            OpenFormAfterThis.signIn(userName, newPassword);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "密保答案错误！");
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void setContent(String userName, String question) {
        this.userName = userName;
        lblQuestion.setText(question);
    }
}
