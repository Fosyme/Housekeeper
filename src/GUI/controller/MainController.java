package GUI.controller;

import Core.model.Book;
import Core.model.Order;
import Core.model.User;
import Core.mutual.Display;
import Core.mutual.Info;
import GUI.Controller;
import GUI.Main;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

public class MainController implements Controller {
    private User user;

    @FXML
    private VBox paneMain;
    @FXML
    private Label username;

    @FXML
    private TableView<Book> bookTableView;
    @FXML
    private TableColumn<Book, String> bookName;
    @FXML
    private TableColumn<Book, String> bookDesc;

    @FXML
    private TextField keywordTextField;
    @FXML
    private Label lblClear;

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

    //添加账本事件
    @FXML
    void ctmAddBookEVent(ActionEvent event) {
        Info info = new Info();
        Optional<Pair<String, String>> result = bookDialog("添加账本", "", "");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //对结果进行处理
        result.ifPresent(pair -> {
            if (pair.getKey().isBlank()) {
                alert.setContentText("内容不能为空！");
            }
            alert.setTitle("提示");
            alert.setHeaderText(null);
            if (info.isBookExist(user.getUserID(), pair.getKey())) {
                alert.setContentText("账本已存在！");
                ctmAddBookEVent(event);
            } else {
                if (info.addBook(user.getUserID(), pair.getKey(), pair.getValue())) {
                    alert.setContentText("账本添加成功！");
                } else {
                    alert.setContentText("账本添加失败，请重试！");
                }
            }
            alert.show();
        });
        refreshBook();
    }

    //修改账本事件
    @FXML
    void ctmAlterBookEvent(ActionEvent event) {
        Info info = new Info();
        Book book = bookTableView.getSelectionModel().getSelectedItem();
        if (book == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("请选择账本！");
            alert.show();
            return;
        }
        String bookName = book.getBookName();
        String bookDesc = book.getBookDesc();
        Optional<Pair<String, String>> result = bookDialog("修改账本", bookName, bookDesc);
        result.ifPresent(pair -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            if (info.alterBook(book.getBookID(), pair.getKey(), pair.getValue())) {
                alert.setContentText("账本修改成功！");
            } else {
                alert.setContentText("账本修改失败，请重试！");
            }
            alert.show();
        });
        refreshBook();
    }

    //删除账本事件
    @FXML
    void ctmDeleteBookEvent(ActionEvent event) {
        Info info = new Info();
        Book book = bookTableView.getSelectionModel().getSelectedItem();
        if (book == null) {
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
                if (!info.deleteBook(book.getBookID())) {
                    Alert delete = new Alert(Alert.AlertType.ERROR);
                    delete.setTitle("提示");
                    delete.setHeaderText(null);
                    delete.setContentText("删除出错，请重试！");
                    delete.show();
                }
            }
        });
        refreshBook();
        orderTableView.setItems(null);
    }

    //添加账单
    @FXML
    void addButtonEvent(ActionEvent event) {
        //判断是否选择了账本
        Book book = bookTableView.getSelectionModel().getSelectedItem();
        if (book == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("请选择账本！");
            alert.show();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/add_order.fxml"));
            Parent page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("添加账单");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            AddOrderController controller = loader.getController();
            controller.initialize(user);
            controller.setSelectedBookID(book.getBookID());
            stage.showAndWait();
            refreshOrder(book.getBookID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修改账单事件
    @FXML
    void alterButtonEvent(ActionEvent event) {
        Order order = orderTableView.getSelectionModel().getSelectedItem();
        if (order == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("请选择账本和账单！");
            alert.show();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/alter_order.fxml"));
            Parent page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("修改账单");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            AlterOrderController controller = loader.getController();
            controller.initialize(user);
            controller.fillData(order);
            stage.showAndWait();
            refreshOrder(order.getBookID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //上下文添加账单
    @FXML
    void ctmAddOrderEvent(ActionEvent event) {
        addButtonEvent(event);
    }

    //上下文修改账单
    @FXML
    void ctmAlterOrderEvent(ActionEvent event) {
        alterButtonEvent(event);
    }

    //上下文删除账单
    @FXML
    void ctmDeleteOrderEvent(ActionEvent event) {
        Info info = new Info();
        Order order = orderTableView.getSelectionModel().getSelectedItem();
        if (order == null) {
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
                if (!info.deleteOrder(order.getOrderID())) {
                    Alert delete = new Alert(Alert.AlertType.ERROR);
                    delete.setTitle("提示");
                    delete.setHeaderText(null);
                    delete.setContentText("删除出错，请重试！");
                    delete.show();
                }
            }
        });
        refreshOrder(order.getBookID());
    }

    //模糊查询关键字
    @FXML
    void searchButtonEvent(ActionEvent event) {
        String keyword = keywordTextField.getText();
        if (keyword.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("内容不能为空！");
            alert.show();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/fuzzy_search.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("模糊查询");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            FuzzySearchController controller = loader.getController();
            controller.initialize(user);
            controller.setData(keyword);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //报表事件
    @FXML
    void reportButtonEvent(ActionEvent event) {
        Book book = bookTableView.getSelectionModel().getSelectedItem();
        if (book == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("请选择账本！");
            alert.show();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/report.fxml"));
            Parent page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("报表");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            ReportController controller = loader.getController();
            controller.initialize(user);
            controller.setPieChar(book.getBookID());
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //时间查询事件
    @FXML
    void dateSearchButtonEvent(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/date_search.fxml"));
            Parent page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("日期查询");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            DateSearchController controller = loader.getController();
            controller.initialize(user);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //设置事件
    @FXML
    void setButtonEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/set.fxml"));
            Parent page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("设置");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            SetController controller = loader.getController();
            controller.initialize(user);
            controller.setParentStage((Stage) paneMain.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //搜索框清空内容事件
    @FXML
    void lblClearClicked(MouseEvent mouseEvent) {
        keywordTextField.clear();
    }

    @Override
    public void initialize(User user) {
        this.user = user;
        Display display = new Display();
        username.setText(user.getUserName());
        bookTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, book, t1) -> {
                    if (t1 != null)
                        refreshOrder(t1.getBookID());
                });
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
        ObservableList<Book> list = display.updateBooks(user.getUserID());
        bookTableView.setItems(list);
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookDesc.setCellValueFactory(new PropertyValueFactory<>("bookDesc"));
    }


    //刷新账本内容
    void refreshBook() {
        Display display = new Display();
        ObservableList<Book> list = display.updateBooks(user.getUserID());
        bookTableView.refresh();
        bookTableView.setItems(list);
        bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookDesc.setCellValueFactory(new PropertyValueFactory<>("bookDesc"));
    }

    //刷新账本对应账单内容
    public void refreshOrder(String bookID) {
        Display display = new Display();
        ObservableList<Order> list = display.updateOrders(bookID);
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
