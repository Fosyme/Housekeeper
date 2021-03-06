package GUI.controller;

import Core.User;
import Core.UserLogin;
import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

public class SetController {
    private UserLogin userLogin;
    private Stage parent;

    @FXML
    private AnchorPane paneSet;

    @FXML
    private RadioMenuItem defaultRadioMenulten;

    @FXML
    private ToggleGroup theme;

    @FXML
    private RadioMenuItem PalegrayRadioMenultem;

    @FXML
    private RadioMenuItem LemonyellowRadioMenultem;

    @FXML
    private Button AboutsoftwarButton;

    public void setParent(Stage parent) {
        this.parent = parent;
    }

    @FXML
    Scene AboutsoftwarButtonEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/softInfo.fxml"));
            AnchorPane page = loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("关于软件");
            mainFrameStage.setResizable(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
//            scene.getStylesheets().add((getStyleValue()));
            SoftInfoController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @FXML
    Scene UserinfoEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/userinfo.fxml"));
            AnchorPane page = loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("用户界面");
            mainFrameStage.setResizable(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            scene.getStylesheets().add((getStyleValue()));

            UserinfoController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @FXML
    void SwitchAccountEvent(ActionEvent event) {
        try {
            ((Stage) paneSet.getScene().getWindow()).close();
            parent.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/signIn.fxml"));
            Pane page = loader.load();
            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("登录");
            mainFrameStage.setResizable(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            scene.getStylesheets().add((getStyleValue()));
            SignInController controller = loader.getController();
            controller.initialization();
            userLogin.writeConfig(false, false);
            mainFrameStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ExitEvent(ActionEvent event) {
        userLogin.signOut();
        System.exit(0);
    }

    @FXML
    void LemonyellowRadioMenultemEvent(ActionEvent event) throws IOException {
        File file = new File("src\\GUI\\resources\\styles.properties");
        if (!file.exists()) {
            file.createNewFile();
        }
        Properties properties = new Properties();
        properties.setProperty("Lemonyellow", "GUI/resources/Lemonyellow.css");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        properties.store(fileOutputStream, "柠檬黄");
        JOptionPane.showMessageDialog(null, "切换柠檬黄主题成功", "信息", JOptionPane.PLAIN_MESSAGE);
        fileOutputStream.close();
    }

    @FXML
    void PalegrayRadioMenultemEvent(ActionEvent event) throws IOException {
        File file = new File("src\\GUI\\resources\\styles.properties");
        if (!file.exists()) {
            file.createNewFile();
        }
        Properties properties = new Properties();
        properties.setProperty("Palegray", "GUI/resources/Palegray.css");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        properties.store(fileOutputStream, "苍白灰");
        JOptionPane.showMessageDialog(null, "切换苍白灰主题成功", "信息", JOptionPane.PLAIN_MESSAGE);
        fileOutputStream.close();

    }

    @FXML
    void defaultRadioMenultenEvent(ActionEvent event) throws IOException {
        File file = new File("src\\GUI\\resources\\styles.properties");
        if (!file.exists()) {
            file.createNewFile();
        }
        Properties properties = new Properties();
        properties.setProperty("default", "GUI/resources/default.css");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        properties.store(fileOutputStream, "默认");
        JOptionPane.showMessageDialog(null, "切换默认主题成功", "信息", JOptionPane.PLAIN_MESSAGE);
        fileOutputStream.close();
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

    public void initialization(User user) {
        userLogin = new UserLogin(user);
        initThemeRadioMenuItem();
    }

    private void initThemeRadioMenuItem() {
        String key = "";
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(new File("src\\GUI\\resources\\styles.properties"));
            for (String s : properties.stringPropertyNames()) {
                key = s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 判断properties文件key的值
        if (key.equals("Palegray")) {
            PalegrayRadioMenultem.setSelected(true);
        } else if (key.equals("Lemonyellow")) {
            LemonyellowRadioMenultem.setSelected(true);
        } else {
            defaultRadioMenulten.setSelected(true);
        }
    }
}
