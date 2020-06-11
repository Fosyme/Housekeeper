package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @Override
    public void init() {
        File file = new File("config.xml");
        ConfigText configText = new ConfigText(file);

        if (file.exists()) {
            System.out.println("文件已存在！");
            configText.readConfig();
        }else {
            System.out.println("文件不存在！");
            if (configText.newConfig()) {
                System.out.println("文件创建成功！");
            } else {
                System.out.println("文件创建失败！");
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
