package GUI.controller;

import Core.UserLogin;
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
import java.io.*;
import java.util.Iterator;
import java.util.Optional;
import java.util.Properties;

public class SignInController {
    private UserLogin userLogin = new UserLogin();

    @FXML
    private Pane paneSignIn;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private CheckBox rememberPassword;

    @FXML
    private Button signUpButton;

    @FXML
    private Button signInButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private CheckBox autoSignIn;

    @FXML
    private Button findPassword;

    public void initialization() {
        userLogin = new UserLogin();
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

    @FXML
    void signInButtonEvent(ActionEvent event) {
        //登录
        if (nameTextField.getText().equals("") || passwordTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "请按照文本框内容提示正确填写内容", "警告", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean b = userLogin.signIn(nameTextField.getText(), passwordTextField.getText());
        if (b) {
            JOptionPane.showConfirmDialog(null, "恭喜登录成功", "信息", JOptionPane.DEFAULT_OPTION);
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
                //加载CSS样式文件
                scene.getStylesheets().add((getStyleValue()));

                MainController controller = loader.getController();
                controller.initialization(userLogin.getUser());
                ((Stage) paneSignIn.getScene().getWindow()).close();
                writeConfig();
                mainFrameStage.showAndWait();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "用户名或者密码错误", "信息", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void writeConfig() {
        try {
            FileOutputStream fos = new FileOutputStream("src/config");
            Properties prop = new Properties();
            prop.put("auto_sign_in", String.valueOf(autoSignIn.isSelected()));
            prop.put("remember_password", String.valueOf(rememberPassword.isSelected()));
            prop.put("user_name", nameTextField.getText());
            if (rememberPassword.isSelected()) {
                prop.put("user_password", passwordTextField.getText());
            }
            prop.store(fos,"Config");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
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
            signUp.setOnCloseRequest(windowEvent ->
                    OpenFormAfterThis.signIn((Stage) paneSignIn.getScene().getWindow()));
            signUp.setTitle("注册");
            signUp.setResizable(true);
            signUp.setAlwaysOnTop(false);
            signUp.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            signUp.setScene(scene);
            //加载CSS样式文件
            scene.getStylesheets().add((getStyleValue()));

            SignUpController controller = loader.getController();
            controller.initialization();
            ((Stage) paneSignIn.getScene().getWindow()).close();
            signUp.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setDialogStage(Stage mainFrameStage) {
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
                String encryptedQuestion = userLogin.queryUserEncrypted(userNameOfFind);
                if (encryptedQuestion != null) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("fxml/findPassword.fxml"));
                    AnchorPane page = loader.load();
                    Stage mainFrameStage = new Stage();
                    mainFrameStage.setOnCloseRequest(windowEvent ->
                            OpenFormAfterThis.signIn((Stage) paneSignIn.getScene().getWindow()));
                    mainFrameStage.setTitle("找回密码");
                    mainFrameStage.setResizable(true);
                    mainFrameStage.setAlwaysOnTop(false);
                    mainFrameStage.initModality(Modality.APPLICATION_MODAL);
                    Scene scene = new Scene(page);
                    mainFrameStage.setScene(scene);
                    //加载CSS样式文件
                    scene.getStylesheets().add((getStyleValue()));

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

    public void afterSignUp(String userName, String userPassword) {
        nameTextField.setText(userName);
        passwordTextField.setText(userPassword);
    }

    public void setRemember(boolean remember) {
        this.rememberPassword.setSelected(remember);
    }

    @FXML
    public String getStyleValue() throws IOException {
        File file = new File("src\\GUI\\resources\\styles.properties");
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);
        Iterator<String> iterator = properties.stringPropertyNames().iterator();
        String Key = "";
        while (iterator.hasNext()) {
            Key = iterator.next();
        }
        return properties.getProperty(Key, "");
    }
}






