package GUI.controller;

import Core.*;
import GUI.Main;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.Properties;

public class MainController {
    private MainInterface main;
    private BookInterface bookInterface;
    private OrderInterface orderInterface;

    @FXML
    private VBox paneMain;

    @FXML
    private Button checkButton;

    @FXML
    private Label username;

    @FXML
    private Button reportButton;

    @FXML
    private Button addButton;

    @FXML
    private Button alterButton;

    @FXML
    private Button setButton;

    @FXML
    private TableView<Book> bookTableView;

    @FXML
    private TableColumn<Book, String> bookName;

    @FXML
    private TableColumn<Book, String> bookDesc;

    @FXML
    private MenuItem ctmAddBook;

    @FXML
    private MenuItem ctmDeleteBook;

    @FXML
    private MenuItem ctmAlterBook;

    @FXML
    private TextField keywordTextField;

    @FXML
    private Label lblClear;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Order> orderTableView;

    @FXML
    private TableColumn<Order, String> nameColumn;

    @FXML
    private TableColumn<Order, String> modColumn;

    @FXML
    private TableColumn<Order, String> wayColumn;

    @FXML
    private TableColumn<Order, String> moneyColumn;

    @FXML
    private TableColumn<Order, String> cateColumn;

    @FXML
    private TableColumn<Order, String> descColumn;

    @FXML
    private TableColumn<Order, String> dateColumn;

    @FXML
    private MenuItem ctmAddOrder;

    @FXML
    private MenuItem ctmDeleteOrder;

    @FXML
    private MenuItem ctmAlterOrder;

    //自定义一个对话框, 用于账本的操作
    private Optional<Pair<String, String>> bookDialog(String title, String bookName, String bookDesc) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        //定义一个面板
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20, 150, 10, 10));
        //定义一个文本框和一个文本域
        TextField bookNameText = new TextField();
        TextArea bookDescText = new TextArea();
        bookNameText.setText(bookName);
        bookDescText.setText(bookDesc);
        //向面板加入四个Node
        pane.add(new Label("账  本  名："), 0, 0);
        pane.add(new Label("账本描述："), 0, 1);
        pane.add(bookNameText, 1, 0);
        pane.add(bookDescText, 1, 1);
        //定义"确定"按钮
        ButtonType buttonType = new ButtonType("确定", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);
        //将面板加入对话框
        dialog.getDialogPane().setContent(pane);
        //设置焦点
        Platform.runLater(bookNameText::requestFocus);
        //定义结果集的触发方式和内容
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonType) {
                return new Pair<>(bookNameText.getText(), bookDescText.getText());
            }
            return null;
        });
        //运行对话框
        return dialog.showAndWait();
    }

    @FXML
    void ctmAddBookEVent(ActionEvent event) {
        Optional<Pair<String, String>> result = bookDialog("添加账本", "", "");
        //对结果进行处理
        result.ifPresent(pair -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            if (bookInterface.isBookExist(pair.getKey())) {
                alert.setContentText("账本已存在！");
                alert.showAndWait();
                ctmAddBookEVent(event);
            } else {
                if (bookInterface.addBook(pair.getKey(), pair.getValue())) {
                    alert.setContentText("账本添加成功！");
                } else {
                    alert.setContentText("账本添加失败，请重试！");
                }
                alert.show();
            }
        });
        ctmRefreshBookEvent();
    }

    @FXML
    void ctmAlterBookEvent(ActionEvent event) {
        int index = bookTableView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Book book = main.getUser().getBooks().get(index);
            String bookName = book.getBookName();
            String bookDesc = book.getBookDesc();
            Optional<Pair<String, String>> result = bookDialog("修改账本", bookName, bookDesc);
            result.ifPresent(pair -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("提示");
                alert.setHeaderText(null);
                if (bookInterface.alterBook(index, pair.getKey(), pair.getValue())) {
                    alert.setContentText("账本修改成功！");
                } else {
                    alert.setContentText("账本修改失败，请重试！");
                }
                alert.show();
            });
            ctmRefreshBookEvent();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("请选择账本！");
            alert.show();
        }
    }

    @FXML
    void ctmDeleteBookEvent(ActionEvent event) {
        int index = bookTableView.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("请选择账本！");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("提示");
        alert.setHeaderText(null);
        alert.setContentText("请问是否删除？");
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                if (!bookInterface.deleteBook(index)) {
                    Alert delete = new Alert(Alert.AlertType.ERROR);
                    delete.setTitle("提示");
                    delete.setHeaderText(null);
                    delete.setContentText("删除出错，请重试！");
                    delete.show();
                }
            }
        });
        ctmRefreshBookEvent();
        orderTableView.setItems(null);
    }

    //刷新账本信息
    void ctmRefreshBookEvent() {
        ObservableList<Book> list = main.refreshBookData();
        bookTableView.refresh();
        bookTableView.setItems(list);
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookDesc.setCellValueFactory(new PropertyValueFactory<>("bookDesc"));
    }

    @FXML
    void addButtonEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/addOrder.fxml"));
            AnchorPane page = loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("添加账单");
            mainFrameStage.setResizable(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);

            //加载CSS样式文件
            scene.getStylesheets().add((getStyleValue()));
            AddOrderController controller = loader.getController();
            controller.initialization(main.getUser());
            //判断是否选择了账本
            int index = bookTableView.getSelectionModel().getSelectedIndex();
            if (index == -1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("警告");
                alert.setHeaderText(null);
                alert.setContentText("请选择账本！");
                alert.showAndWait();
                return;
            }
            controller.setBookIndex(index);
            mainFrameStage.showAndWait();
            changeSelectedBookItem(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void alterButtonEvent(ActionEvent event) {
        int bookIndex = bookTableView.getSelectionModel().getSelectedIndex();
        int orderIndex = orderTableView.getSelectionModel().getSelectedIndex();
        if (bookIndex == -1 || orderIndex == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("请选择账本和账单！");
            alert.show();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/alterOrder.fxml"));
            AnchorPane page = loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("修改账单");
            mainFrameStage.setResizable(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            //加载CSS样式文件
            scene.getStylesheets().add((getStyleValue()));

            AlterOrderController controller = loader.getController();
            controller.initialization(main.getUser());
            controller.dataPadding(bookIndex, orderIndex);
            mainFrameStage.showAndWait();
            changeSelectedBookItem(bookIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ctmAddOrderEvent(ActionEvent event) {
        addButtonEvent(event);
    }

    @FXML
    void ctmAlterOrderEvent(ActionEvent event) {
        alterButtonEvent(event);
    }

    @FXML
    void ctmDeleteOrderEvent(ActionEvent event) {
        int bookIndex = bookTableView.getSelectionModel().getSelectedIndex();
        int orderIndex = orderTableView.getSelectionModel().getSelectedIndex();
        if (bookIndex == -1 || orderIndex == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("请选择账本和账单！");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("提示");
        alert.setHeaderText(null);
        alert.setContentText("请问是否删除？");
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                if (!orderInterface.deleteOrder(bookIndex, orderIndex)) {
                    Alert delete = new Alert(Alert.AlertType.ERROR);
                    delete.setTitle("提示");
                    delete.setHeaderText(null);
                    delete.setContentText("删除出错，请重试！");
                    delete.show();
                }
            }
        });
        changeSelectedBookItem(bookIndex);
    }

    //搜索
    @FXML
    void searchButtonEvent(ActionEvent event) {
        //模糊查询关键字
        String keyword = keywordTextField.getText();
        if (keyword.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("内容不能为空！");
            alert.show();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("fxml/screenOrder.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("模糊查询");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                ScreenOrder controller = loader.getController();
                controller.initialization(main.getUser());
                controller.setTabScreen(orderInterface.fuzzyQueryOrder(keyword));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    Scene setButtonEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/set.fxml"));
            AnchorPane page = loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("设置");
            mainFrameStage.setResizable(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            //加载CSS样式文件
            scene.getStylesheets().add((getStyleValue()));
            SetController controller = loader.getController();
            controller.initialization(main.getUser());
            controller.setParent((Stage) paneMain.getScene().getWindow());
            mainFrameStage.show();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    void reportButtonEvent(ActionEvent event) {
        int bookIndex = bookTableView.getSelectionModel().getSelectedIndex();
        if (bookIndex == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("请选择账本！");
            alert.showAndWait();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/report.fxml"));
            AnchorPane page = loader.load();
            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("报表");
            mainFrameStage.setResizable(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            //加载CSS样式文件
            scene.getStylesheets().add((getStyleValue()));
            ReportController controller = loader.getController();
            controller.initialization(main.getUser());
            controller.setPieChar(bookIndex);
            mainFrameStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void checkButtonEvent(ActionEvent actionEvent) {
        //查询
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/dateSearch.fxml"));
            AnchorPane page = loader.load();

            Stage mainFrameStage = new Stage();
            mainFrameStage.setTitle("日期查询");
            mainFrameStage.setResizable(false);
            mainFrameStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            mainFrameStage.setScene(scene);
            scene.getStylesheets().add((getStyleValue()));
            DateSearchController controller = loader.getController();
            controller.initialization(main.getUser());
            mainFrameStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void lblClearClicked(MouseEvent mouseEvent) {
        keywordTextField.clear();
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
        main = new MainInterface(user);
        bookInterface = new BookInterface(user);
        orderInterface = new OrderInterface(user);
        username.setText(user.getUserName());
        bookTableView
                .getSelectionModel()
                .selectedIndexProperty()
                .addListener((observableValue, number, t1) -> changeSelectedBookItem((Integer) t1));
        lblClear.setVisible(false);
        keywordTextField
                .textProperty()
                .addListener((observableValue, s, t1) -> {
                    if (s.isEmpty() && !t1.isEmpty()) {
                        lblClear.setVisible(true);
                    }
                    if (!s.isEmpty() && t1.isEmpty()) {
                        lblClear.setVisible(false);
                    }
                });
        ObservableList<Book> list = main.initializeBookData();
        bookTableView.setItems(list);
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookDesc.setCellValueFactory(new PropertyValueFactory<>("bookDesc"));
    }

    public void changeSelectedBookItem(int bookIndex) {
        if (bookIndex == -1) {
            return;
        }
        ObservableList<Order> list = main.getOrderOfBook(bookIndex);
        orderTableView.refresh();
        orderTableView.setItems(list);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("orderName"));
        modColumn.setCellValueFactory(new PropertyValueFactory<>("orderMod"));
        wayColumn.setCellValueFactory(new PropertyValueFactory<>("orderWay"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("orderPrice"));
        cateColumn.setCellValueFactory(new PropertyValueFactory<>("orderCate"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("orderDesc"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    }
}
