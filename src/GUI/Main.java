package GUI;

import GUI.controller.MainController;
import GUI.controller.SignInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        //TODO 程序开始时读取配置文件两个值，和用户名与密码
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/main.fxml"));
        Parent root = fxmlLoader.load();
//        SignInController signIn = fxmlLoader.getController();
//        signIn.initialization();
//        MainController main= fxmlLoader.getController();
//        main.initialization();
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
