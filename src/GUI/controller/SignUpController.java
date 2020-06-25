package GUI.controller;

import Core.model.User;
import Core.mutual.Login;
import GUI.Controller;
import GUI.OpenFormAfterThis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class SignUpController implements Controller {
    private Login login;

    @FXML
    private AnchorPane paneSignUp;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ToggleGroup sex;
    @FXML
    private RadioButton man;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton secret;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField answerTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField PhoneTextField;
    @FXML
    private TextField AddressTextField;
    @FXML
    private ComboBox<String> comBoxQuestion;

    @FXML
    void signUpButtonEvent(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("注册警告");

        String userNameRegex = "^[a-zA-Z][a-z0-9A-Z_]{2,15}$";
        String passwordIllegalRegex = "[ _`~$^&*()=|{}':;,\\\\\\[\\].<>/?！￥…（）—【】‘；：”“’。，、？]+|\\n|\\r|\\t";
        String encryptedAnswerRegex = "^[\\u4e00-\\u9fa5a-zA-Z0-9]+$";
        String ageRegex = "^\\d{1,2}$";
        String phoneRegex = "^1\\d{10}$";

        String userName = nameTextField.getText().toLowerCase();            //用户名
        String userPassword = passwordTextField.getText();                  //用户密码
        String userEncryptedQuestion =
                comBoxQuestion.getSelectionModel().getSelectedItem();       //用户密保问题
        String userEncryptedAnswer = answerTextField.getText();             //用户密保答案
        String userSex = (String) sex.getSelectedToggle().getUserData();    //用户性别
        String userAge = ageTextField.getText();                            //用户年龄
        String userPhone = PhoneTextField.getText();                        //电话号码
        String userAddress = AddressTextField.getText();                    //地址
        String userHeadThumb = "";                                          //头像

        if (!userName.matches(userNameRegex)) {
            alert.setHeaderText("用户名不合法！");
            alert.setContentText("只能是以字母开头，可以包含字母，数字和下划线(_)，且长度为3-16位");
            alert.showAndWait();
            return;
        }
        if (userPassword.matches(passwordIllegalRegex)) {
            alert.setHeaderText("用户密码不合法！");
            alert.setContentText("可以包含字母，数字，下划线(_)和以下字符(!@#%+-)");
            alert.showAndWait();
            return;
        }
        if (!userEncryptedAnswer.matches(encryptedAnswerRegex)) {
            alert.setHeaderText("密保答案不合法！");
            alert.setContentText("可以包含字母，数字和中文");
            alert.showAndWait();
            return;
        }
        if (!userAge.matches(ageRegex)) {
            alert.setHeaderText("年龄不合法！");
            alert.setContentText("只能填写0-99");
            alert.showAndWait();
            return;
        }
        if (!userPhone.matches(phoneRegex)) {
            alert.setHeaderText("手机号不合法！");
            alert.setContentText("只能填写1开头的11位电话号码");
            alert.showAndWait();
            return;
        }
        String[] userMsg = new String[]{
                userName, userPassword,
                userEncryptedQuestion, userEncryptedAnswer,
                userSex, userAge, userPhone, userAddress, userHeadThumb
        };
        if (!login.signUp(userMsg)) {
            alert.setHeaderText(null);
            alert.setContentText("用户名已存在");
            alert.showAndWait();
        } else {
            alert.setHeaderText(null);
            alert.setContentText("账号注册成功");
            alert.showAndWait();
            ((Stage) paneSignUp.getScene().getWindow()).close();
            OpenFormAfterThis.signIn(userName, userPassword);
        }
    }

    @Override
    public void initialize(User user) {
        login = new Login();
        List<String> list = new ArrayList<>();
        list.add("你第个宠物的名字是什么?");
        list.add("你出生城市的名称是什么?");
        list.add("你孩童时期的呢称是什么?");
        list.add("你父母相遇的城市的名称是什么?");
        list.add("你年纪最大的表亲的名字是什么?");
        list.add("你的母校名称是什么?");
        ObservableList<String> observableList = FXCollections.observableList(list);
        comBoxQuestion.setItems(observableList);
        comBoxQuestion.getSelectionModel().selectFirst();
        man.setUserData("男");
        female.setUserData("女");
        secret.setUserData("保密");
    }
}
