package GUI.controller;

import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

public class MainPage1Controller<TableData, SimpleTools> {

    @FXML
    private MenuItem importMenuItem;

    @FXML
    private MenuItem exportMenuItem;

    @FXML
    private MenuItem backupMenuItem;

    @FXML
    private MenuItem recoverMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem dateCheckMenuItem;

    @FXML
    private MenuItem classificationCheckMenuItem;

    @FXML
    private MenuItem memoCheckMenuItem;

    @FXML
    private RadioMenuItem defaultRadioMenuItem;

    @FXML
    private ToggleGroup RadioMenuItem;

    @FXML
    private RadioMenuItem blackRadioMenuItem;

    @FXML
    private RadioMenuItem whiteRadioMenuItem;

    @FXML
    private MenuItem addClassificationMenuItem;

    @FXML
    private MenuItem userinfo;

    @FXML
    private MenuItem abutSoftMenuItem;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private Label username;

    @FXML
    private Button statementButton;

    @FXML
    private Button addBuuton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button alterBuutton;

    @FXML
    private Button setButton;

    @FXML
    private ListView<?> accountbookListView;

    @FXML
    private MenuItem accountbook_refreshContextMenu;

    @FXML
    private MenuItem accountbook_addContextMenu;

    @FXML
    private MenuItem accountbook_deleteContextMenu;

    @FXML
    private MenuItem accountbook_alterContextMenu;

    @FXML
    private TextField keywordTextField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<TableData, String> idColumn;

    @FXML
    private TableColumn<TableData, String> typeColumn;

    @FXML
    private TableColumn<TableData, String> moneyColumn;

    @FXML
    private TableColumn<TableData, String> classificationColumn;

    @FXML
    private TableColumn<TableData, String> memoColumn;

    @FXML
    private TableColumn<TableData, String> dateColumn;

    @FXML
    private MenuItem bill_refreshContextMenu;

    @FXML
    private MenuItem bill_addContextMenu;

    @FXML
    private MenuItem bill_deleteContextMenu;

    @FXML
    private MenuItem bill_alterContextMenu;

    @FXML
    private Font x3;

    @FXML
    private Color x4;


    @FXML
    Scene abutSoftMenuItemEvent(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/SoftInformation.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("关于软件");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            scene.getStylesheets().add((getStyleValue()));

            SoftInformationController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    void accountbookListViewEvent(ActionEvent event) {

    }

    @FXML
    void accountbook_addContextMenuEVent(ActionEvent event) {

    }

    @FXML
    void accountbook_alterContextMenuEvent(ActionEvent event) {

    }

    @FXML
    void accountbook_deleteContextMenuEvent(ActionEvent event) {

    }

    @FXML
    void accountbook_refreshContextMenuEvent(ActionEvent event) {

    }

    @FXML
    Scene addBuutonEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/AddAccountPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("添加账单");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            scene.getStylesheets().add((getStyleValue()));

            AddAccountController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @FXML
    void addClassificationMenuItemEvent(ActionEvent event) {

    }

    @FXML
    Scene alterButtonEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/AlterAccount.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("修改账单");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            scene.getStylesheets().add((getStyleValue()));

            AlterAccountController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @FXML
    void backupMenuItemEvent(ActionEvent event) {

    }

    @FXML
    void bill_addContextMenuEvent(ActionEvent event) {

    }

    @FXML
    void bill_alterContextMenuEvent(ActionEvent event) {

    }

    @FXML
    void bill_deleteContextMenuEvent(ActionEvent event) {

    }

    @FXML
    void bill_refreshContextMenuEvent(ActionEvent event) {

    }

    @FXML
    void blackRadioMenuItemEvent(ActionEvent event) throws IOException {
        File file=new File("src\\GUI\\src\\styles.properties");
        if (!file.exists()){
            file.createNewFile();
        }
        Properties properties=new Properties();
        properties.setProperty("black","GUI/src/black.css");
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        properties.store(fileOutputStream,"经典黑");
        JOptionPane.showMessageDialog(null,"切换经典黑主题成功","信息",JOptionPane.PLAIN_MESSAGE);
        fileOutputStream.close();


    }

    @FXML
    Scene classificationCheckMenuItemEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/classificationCheck.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("分类查询");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            scene.getStylesheets().add((getStyleValue()));

            classificationCheckController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @FXML
    Scene dateCheckMenuItemEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/dateCheck.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("日期查询");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            scene.getStylesheets().add((getStyleValue()));

            dateCheckController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    @FXML
    void defaultRadioMenuItemEvent(ActionEvent event) throws IOException {
        File file=new File("src\\GUI\\src\\styles.properties");
        if (!file.exists()){
            file.createNewFile();
        }
        Properties properties=new Properties();
        properties.setProperty("default","");
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        properties.store(fileOutputStream,"默认");
        JOptionPane.showMessageDialog(null,"切换默认主题成功","信息",JOptionPane.PLAIN_MESSAGE);
        fileOutputStream.close();

    }

    @FXML
    void deleteButtonEvent(ActionEvent event) {

    }

    @FXML
    void exitMenuItemEvent(ActionEvent event) {

    }

    @FXML
    void exportMenuItemEvent(ActionEvent event) {

    }

    @FXML
    void helpMenuItemEvent(ActionEvent event) {

    }

    @FXML
    void importMenuItemEvent(ActionEvent event) {

    }

    @FXML
    void keywordTextFieldEvent(ActionEvent event) {

    }

    @FXML
    void memoCheckMenuItemEvent(ActionEvent event) {

    }

    @FXML
    void recoverMenuItemEvent(ActionEvent event) {

    }

    @FXML
    void searchButtonEvent(ActionEvent event) {

    }

    @FXML
    void setButtonEvent(ActionEvent event) {

    }

    @FXML
    Scene statementButtonEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/report.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("报表");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            scene.getStylesheets().add((getStyleValue()));

            reportController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @FXML
    void userinfoEvent(ActionEvent event) {

    }

    @FXML
    void whiteRadioMenuItemEvent(ActionEvent event) throws IOException {
        File file=new File("src\\GUI\\src\\styles.properties");
        if (!file.exists()){
            file.createNewFile();
        }
        Properties properties=new Properties();
        properties.setProperty("white", "GUI/src/white.css");
        FileOutputStream fileOutputStream=new FileOutputStream(file);

        properties.store(fileOutputStream, "优雅白");
        JOptionPane.showMessageDialog(null,"切换优雅白主题成功","信息",JOptionPane.PLAIN_MESSAGE);
        fileOutputStream.close();

    }
    public String getStyleValue() throws IOException {
        File file=new File("src\\GUI\\src\\styles.properties");
        Properties properties=new Properties();
        FileInputStream fileInputStream=new FileInputStream(file);
        properties.load(fileInputStream);
        Iterator<String> iterator=properties.stringPropertyNames().iterator();
        String Key= "";
        while (iterator.hasNext()){
            Key = iterator.next();
        }
        return properties.getProperty(Key,"");
    }

   public void initThemeRadioMenuItem() {
        String key = "";
         try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(new File("src\\GUI\\src\\styles.properties"));
            Iterator<String> iterator = properties.stringPropertyNames().iterator();
             while (iterator.hasNext()) {
               key = iterator.next();
               }
             } catch (IOException e) {
             e.printStackTrace();
         }
// 判断properties文件key的值
           if (key.equals("black")) {
            blackRadioMenuItem.setSelected(true);
           } else if (key.equals("white")) {
           whiteRadioMenuItem.setSelected(true);
              } else {
              defaultRadioMenuItem.setSelected(true);
           }
  }



}
