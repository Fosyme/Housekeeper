package GUI.controller;

import Core.UserLogin;
import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class SignInController {
    private UserLogin userLogin;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private CheckBox rememberPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button logupButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private CheckBox autLogin;

    @FXML
    private Button findpassword;

    public void initialization() {
        userLogin = new UserLogin();
    }

    @FXML
    Scene logupButtonEvent(ActionEvent event) {
        //登录
        if (nameTextField.getText().equals("") ||  passwordTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "请按照文本框内容提示正确填写内容", "警告", JOptionPane.ERROR_MESSAGE);
        } else {
             userLogin.signIn(nameTextField.getText(), passwordTextField.getText());
            if (nameTextField.getText()!= null && passwordTextField.getText() != null) {
                int result = JOptionPane.showConfirmDialog(null, "恭喜登录成功", "信息", JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Main.class.getResource("fxml/main.fxml"));
                        VBox page = (VBox) loader.load();
                        Stage mainFrameStage = new Stage();
                        mainFrameStage.setTitle("HouseKeeper");
                        mainFrameStage.setResizable(true);
                        mainFrameStage.setAlwaysOnTop(false);
                        mainFrameStage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(page);
                        mainFrameStage.setScene(scene);
                        MainController controller = loader.getController();
                        controller.setDialogStage(mainFrameStage);
                        mainFrameStage.showAndWait();
                        return scene;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    loginStage.close();

                }else {
                    JOptionPane.showMessageDialog(null,"用户名或者密码错误","信息",JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        return null;
    }




    @FXML
    Scene loginButtonEvent(ActionEvent event) {
        //注册
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/signUp.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("注册");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            SignUpController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setDialogStage(Stage mainFrameStage) {
    }


    @FXML
    Scene findpasswordEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/findPassword.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("找回密码");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            FindPasswordController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    private Stage loginStage;
    public Stage getLoginStage() {
        return loginStage;
    }
    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
}





}
