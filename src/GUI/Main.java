package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/MainPage1.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1150, 830));
        primaryStage.show();
    }
//    public String getStyleValue() throws IOException {
//        File file=new File("src\\GUI\\src\\styles.theme");
//        Properties properties=new Properties();
//        FileInputStream fileInputStream=new FileInputStream(file);
//        properties.load(fileInputStream);
//        Iterator<String> iterator=properties.stringPropertyNames().iterator();
//        String Key="";
//        while (iterator.hasNext()){
//            Key=iterator.next();
//        }
//        return properties.getProperty(Key,"");
//
//    }


/*    @Override
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
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}
