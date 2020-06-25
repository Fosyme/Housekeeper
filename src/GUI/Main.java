package GUI;

import Core.model.User;
import Core.mutual.Login;
import GUI.controller.MainController;
import GUI.controller.SignInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Main extends Application {
    User user;
    Login login;

    @Override
    public void start(Stage primaryStage) throws Exception {
        String userName = "";
        String userPassword = "";
        boolean rememberPassword = false;
        //程序开始时读取配置文件两个值，和已保存用户名与密码(密文)
        File file = new File("src/config");
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(fis);
            userName = prop.getProperty("user_name");
            userPassword = prop.getProperty("user_password");
            rememberPassword = Boolean.parseBoolean(prop.getProperty("remember_password"));
            //当自动登录存在且用户与密码通过验证时，直接进入主页面
            if (Boolean.parseBoolean(prop.getProperty("auto_sign_in"))) {
                //判断配置文件中是否存在这两个键值
                if (userName != null && userPassword != null) {
                    user = login.signIn(userName, userPassword, Login.cipher);
                    if (user != null) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("fxml/main.fxml"));
                        Parent root = loader.load();
                        primaryStage.setTitle("HouseKeeper");
                        primaryStage.setScene(new Scene(root));
                        primaryStage.setOnCloseRequest(windowEvent ->
                                OpenFormAfterThis.exitApp(windowEvent, login));
                        MainController controller = loader.getController();
                        controller.initialize(user);
                        primaryStage.show();
                        return;
                    }
                }
            }
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/signIn.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("用户登录");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(windowEvent ->
                OpenFormAfterThis.exitApp(windowEvent, login));
        SignInController controller = loader.getController();
        controller.initialize(null);
        controller.smartFill(userName, userPassword);
        controller.setRemember(rememberPassword);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
