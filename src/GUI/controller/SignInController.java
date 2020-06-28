package GUI.controller;

import Core.model.User;
import Core.mutual.Login;
import GUI.Controller;
import GUI.Main;
import GUI.OpenFormAfterThis;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class SignInController implements Controller {
    private User user;
    private int passwordType;

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
    @Override
    public void initialize(User user) {
        this.user = user;
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
        passwordTextField.textProperty().addListener((observableValue, s, t1) ->
                passwordType = Login.plain);
    }

    //自动键入用户上次输入的用户名和密码
    public void smartFill(String userName, String userPassword) {
        nameTextField.setText(userName);
        passwordTextField.setText(userPassword);
    }

    //显示用户上次设置的记住密码选项
    public void setRemember(boolean remember) {
        this.rememberPassword.setSelected(remember);
        if (remember) {
            passwordType = Login.cipher;
        }
    }

    //登录事件
    @FXML
    void signInButtonEvent(ActionEvent event) {
        Login login = new Login();
        String name = nameTextField.getText();
        String password = passwordTextField.getText();
        //登录
        if (name == null || password == null || name.isBlank() || password.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("请输入用户名和密码！");
            alert.show();
            return;
        }
        user = login.signIn(nameTextField.getText(), passwordTextField.getText(), passwordType);
        if (user != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("欢迎");
            alert.setHeaderText(null);
            alert.setContentText(user.getUserName() + "，欢迎回来！");
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("fxml/main.fxml"));
                Parent page = loader.load();
                Stage stage = new Stage();
                stage.setTitle("HouseKeeper");
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(page);
                stage.setScene(scene);
                stage.getIcons().add(new Image(new FileInputStream("src/GUI/resources/记账.png")));
                MainController controller = loader.getController();
                controller.initialize(user);
                ((Stage) paneSignIn.getScene().getWindow()).close();
                login.writeConfig(user.getUserName(), user.getUserPassword(),
                        autoSignIn.isSelected(), rememberPassword.isSelected());
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("用户名和密码有误！");
            alert.show();
        }
    }

    @FXML
    void signUpButtonEvent(ActionEvent event) {
        //注册
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/sign_up.fxml"));
            Parent page = loader.load();
            Stage stage = new Stage();
            stage.setOnCloseRequest(windowEvent -> {
                ((Stage) paneSignIn.getScene().getWindow()).close();
                OpenFormAfterThis.signIn("", "");
            });
            stage.setTitle("注册");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            SignUpController controller = loader.getController();
            controller.initialize(null);
            ((Stage) paneSignIn.getScene().getWindow()).close();
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void findPasswordEvent(ActionEvent event) {
        Login login = new Login();
        //找回密码
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("找回密码");
        textInputDialog.setHeaderText(null);
        textInputDialog.setContentText("请输入你的用户名");
        Optional<String> rs = textInputDialog.showAndWait();
        rs.ifPresent(userName -> {
            String encryptedQuestion = login.queryUserEncrypted(userName);
            if (encryptedQuestion != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("fxml/find_password.fxml"));
                    Parent page = loader.load();
                    Stage stage = new Stage();
                    stage.setOnCloseRequest(windowEvent -> {
                        ((Stage) paneSignIn.getScene().getWindow()).close();
                        OpenFormAfterThis.signIn(userName, "");
                    });
                    stage.setTitle("找回密码");
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    Scene scene = new Scene(page);
                    stage.setScene(scene);
                    FindPasswordController controller = loader.getController();
                    controller.initialize(null);
                    controller.setContent(userName, encryptedQuestion);
                    ((Stage) paneSignIn.getScene().getWindow()).close();
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("输入的用户名有误，或该用户无密保！");
                alert.showAndWait();
                findPasswordEvent(event);
            }
        });
    }
}
