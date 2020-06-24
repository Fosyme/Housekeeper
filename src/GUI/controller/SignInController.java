package GUI.controller;

import Core.mutual.Login;
import GUI.Main;
import GUI.OpenFormAfterThis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

public class SignInController {
    private Login login = new Login();

    @FXML
    private Pane paneSignIn;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private CheckBox rememberPassword;

    @FXML
    private TextField nameTextField;

    @FXML
    private CheckBox autoSignIn;

    //登录界面加载初始化
    public void initialization() {
        login = new Login();
        //对记住密码和自动登录选项卡添加监听器, 来实现两个选项卡的智能化
        rememberPassword.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (aBoolean && !t1) {
                autoSignIn.setSelected(false);
            }
        });
        autoSignIn.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!aBoolean && t1) {
                rememberPassword.setSelected(true);
            }
        });
    }

    //自动键入用户上次输入的用户名和密码
    public void smartFill(String userName, String userPassword) {
        nameTextField.setText(userName);
        passwordTextField.setText(userPassword);
    }

    //显示用户上次设置的记住密码选项
    public void setRemember(boolean remember) {
        this.rememberPassword.setSelected(remember);
    }

    @FXML
    void signInButtonEvent(ActionEvent event) {
        //登录
        if (nameTextField.getText().equals("") || passwordTextField.getText().equals("")) {
            //TODO 改写成Alert
            JOptionPane.showMessageDialog(null, "请按照文本框内容提示正确填写内容", "警告", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean b = login.signIn(nameTextField.getText(), passwordTextField.getText());
        if (b) {
            //TODO 改写 xxx，欢迎回来！
            JOptionPane.showConfirmDialog(null, "恭喜登录成功", "信息", JOptionPane.DEFAULT_OPTION);
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("fxml/main.fxml"));
                VBox page = loader.load();
                Stage mainFrameStage = new Stage();
                mainFrameStage.setTitle("HouseKeeper");
                mainFrameStage.setResizable(false);
                mainFrameStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(page);
                mainFrameStage.setScene(scene);
                MainController controller = loader.getController();
                login.getUser().setUserPassword(passwordTextField.getText());
                controller.initialization(login.getUser());
                ((Stage) paneSignIn.getScene().getWindow()).close();
                login.writeConfig(autoSignIn.isSelected(), rememberPassword.isSelected());
                mainFrameStage.showAndWait();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "用户名或者密码错误", "信息", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void signUpButtonEvent(ActionEvent event) {
        //注册
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/signUp.fxml"));
            AnchorPane page = loader.load();
            Stage signUp = new Stage();
            signUp.setOnCloseRequest(windowEvent -> {
                ((Stage) paneSignIn.getScene().getWindow()).close();
                OpenFormAfterThis.signIn("", "");
            });
            signUp.setTitle("注册");
            signUp.setResizable(false);
            signUp.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            signUp.setScene(scene);
            SignUpController controller = loader.getController();
            controller.initialization();
            ((Stage) paneSignIn.getScene().getWindow()).close();
            signUp.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void findPasswordEvent(ActionEvent event) {
        //找回密码
        try {
            TextInputDialog textInputDialog = new TextInputDialog();
            textInputDialog.setTitle("找回密码");
            textInputDialog.setHeaderText(null);
            textInputDialog.setContentText("请输入你的用户名");
            do {
                Optional<String> rs = textInputDialog.showAndWait();
                if (rs.isEmpty()) {
                    break;
                }
                String userNameOfFind = rs.get();
                String encryptedQuestion = login.queryUserEncrypted(userNameOfFind);
                if (encryptedQuestion != null) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("fxml/findPassword.fxml"));
                    AnchorPane page = loader.load();
                    Stage mainFrameStage = new Stage();
                    mainFrameStage.setOnCloseRequest(windowEvent -> {
                        ((Stage) paneSignIn.getScene().getWindow()).close();
                        OpenFormAfterThis.signIn("", "");
                    });
                    mainFrameStage.setTitle("找回密码");
                    mainFrameStage.setResizable(false);
                    mainFrameStage.initModality(Modality.APPLICATION_MODAL);
                    Scene scene = new Scene(page);
                    mainFrameStage.setScene(scene);
                    FindPasswordController controller = loader.getController();
                    controller.initialization();
                    controller.setContent(userNameOfFind, encryptedQuestion);
                    ((Stage) paneSignIn.getScene().getWindow()).close();
                    mainFrameStage.showAndWait();
                    break;
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("输入的用户名有误");
                    alert.showAndWait();
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






