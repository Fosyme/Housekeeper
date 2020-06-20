package GUI.controller;

import Core.*;
import GUI.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.Properties;

public class MainController {
    private MainInterface main;
    private BookInterface bookInterface;

    @FXML
    private Button chackButton;

    @FXML
    private Label username;

    @FXML
    private Button statementButton;

    @FXML
    private Button addBuuton;

    @FXML
    private Button alterButton;

    @FXML
    private Button setButton;

    @FXML
    private ListView<String> accountbookListView;

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
    private TableView<Order> tableView;

    @FXML
    private TableColumn<Order, String> nameColumn;

    @FXML
    private TableColumn<Order, String> typeColumn;

    @FXML
    private TableColumn<Order, String> moneyColumn;

    @FXML
    private TableColumn<Order, String> classificationColumn;

    @FXML
    private TableColumn<Order, String> memoColumn;

    @FXML
    private TableColumn<Order, String> dateColumn;

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
        //删除
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("提示");
        alert.setContentText("请问是否删除");
        alert.setHeaderText("提示");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.get()==ButtonType.OK){
            int accountbookIndex=tableView.getSelectionModel().getFocusedIndex();
            bookInterface.deleteBook(accountbookIndex);
        }else {
            ;
        }
        main.refreshBookData();

    }

    @FXML
    void accountbook_refreshContextMenuEvent(ActionEvent event) {
        //刷新
        main.refreshBookData();


    }

    @FXML
    Scene addBuutonEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/addOrder.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("添加账单");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

//            scene.getStylesheets().add((getStyleValue()));
            AddOrderController controller = loader.getController();
            controller.initialization();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.show();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    @FXML
    Scene alterButtonEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/alterOrder.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("修改账单");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

//            scene.getStylesheets().add((getStyleValue()));

            AlterOrderController controller = loader.getController();
            controller.initialization();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

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
    Scene classificationCheckMenuItemEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/cateSearch.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("分类查询");
            mainFrameStage.setResizable(true);
            mainFrameStage.setAlwaysOnTop(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            scene.getStylesheets().add((getStyleValue()));

            CateSearchController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @FXML
    void keywordTextFieldEvent(ActionEvent event) {

    }

    @FXML
    void searchButtonEvent(ActionEvent event) {
        //搜索

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

//            scene.getStylesheets().add((getStyleValue()));
            ReportController controller = loader.getController();
            controller.setDialogStage(mainFrameStage);
            mainFrameStage.showAndWait();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    @FXML
    public String getStyleValue() throws IOException {
        File file = new File("GUI\\resources\\styles.properties");
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
/*    public void initThemeRadioMenuItem() {
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
    }*/

    public void initialization(User user) {
        main = new MainInterface(user);
        username.setText(user.getUserName());
        accountbookListView
                .getSelectionModel()
                .selectedIndexProperty()
                .addListener((observableValue, number, t1) -> {
                    changeSelectedItem(main, (Integer) t1);
                });
        ObservableList<String> list = main.initializeBookData();
        accountbookListView.setItems(list);
    }

    public void changeSelectedItem(MainInterface main, int bookIndex) {
        ObservableList<Order> list = main.getOrderOfBook(bookIndex);
        tableView.setItems(list);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("orderName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("orderMod"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));
        classificationColumn.setCellValueFactory(new PropertyValueFactory<>("orderCate"));;
        memoColumn.setCellValueFactory(new PropertyValueFactory<>("orderDesc"));;
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));;
    }

     @FXML
     Scene chackButtonEvent(ActionEvent actionEvent) {
        //查询
         try {
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(Main.class.getResource("fxml/dateSearch.fxml"));
             AnchorPane page = (AnchorPane) loader.load();

             Stage mainFrameStage = new Stage();
             mainFrameStage.setTitle("日期查询");
             mainFrameStage.setResizable(true);
             mainFrameStage.setAlwaysOnTop(false);
             mainFrameStage.initModality(Modality.APPLICATION_MODAL);
             Scene scene = new Scene(page);
             mainFrameStage.setScene(scene);

//             scene.getStylesheets().add((getStyleValue()));

             DateSearchController controller = loader.getController();
             controller.setDialogStage(mainFrameStage);
             mainFrameStage.showAndWait();
             return scene;
         } catch (IOException e) {
             e.printStackTrace();
         }
         return null;
    }
}
