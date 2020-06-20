package GUI;

import Core.UserLogin;
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
    @Override
    public void start(Stage primaryStage) throws Exception{
        UserLogin login = new UserLogin();
        String userName = null;
        String userPassword = null;
        boolean rememberPassword = false;
        //程序开始时读取配置文件两个值，和已保存用户名与密码
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
                    if (login.signIn(userName, userPassword)) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("fxml/main.fxml"));
                        Parent root = loader.load();
                        primaryStage.setTitle("HouseKeeper");
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        ((MainController) loader.getController()).initialization(login.getUser());
                        primaryStage.show();
                        return;
                    }
                }
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxml/signIn.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("用户登录");
            primaryStage.setResizable(false);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            SignInController controller = loader.getController();
            controller.afterSignUp(userName, userPassword);
            controller.setRemember(rememberPassword);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
