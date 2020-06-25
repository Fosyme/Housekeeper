package GUI.controller;

import Core.model.User;
import Core.mutual.Login;
import GUI.Controller;
import GUI.OpenFormAfterThis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FindPasswordController implements Controller {
    private Login login;
    private String userName;

    @FXML
    private AnchorPane paneFindPwd;
    @FXML
    private Label lblQuestion;
    @FXML
    private TextField NewPasswordTexField;
    @FXML
    private TextField AnswerTextField;

    @Override
    public void initialize(User user) {
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
            alert.setContentText("请记住你的新密码：" + newPassword);
            alert.showAndWait();
            ((Stage) paneFindPwd.getScene().getWindow()).close();
            OpenFormAfterThis.signIn(userName, newPassword);
        } else {
            //TODO Test
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
