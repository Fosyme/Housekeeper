package GUI.controller;

import Core.OrderInterface;
import Core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddOrderController {
    private OrderInterface orderInterface;
    private int bookIndex;

    @FXML
    public AnchorPane paneAddOrder;

    @FXML
    private ToggleGroup togGpMod;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField moneyTextField;

    @FXML
    public ComboBox<String> combWay;

    @FXML
    private ComboBox<String> combCate;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea descTextArea;

    @FXML
    private RadioButton outputRadioButton;

    @FXML
    private RadioButton inputRadioButton;

    @FXML
    void addButtonEvent(ActionEvent event) {
        String moneyRegex = "^\\d{0,8}|\\d{0,8}\\.\\d{1,2}$";
        String type = (String) togGpMod.getSelectedToggle().getUserData();  //账单类型
        String name = nameTextField.getText();                              //账单名字
        String money = moneyTextField.getText();                            //账单金额
        String way = combWay.getSelectionModel().getSelectedItem();         //账单方式
        String cate = combCate.getSelectionModel().getSelectedItem();       //账单分类
        String desc = descTextArea.getText();                               //账单备注
        if (datePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("请选择日期！");
            alert.showAndWait();
            return;
        }
        String date = datePicker.getValue().toString(); //账单日期
        if (type == null || name.isEmpty() || money.isEmpty() || way == null || cate == null || desc.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText(null);
            alert.setContentText("请填写完整的内容！");
            alert.showAndWait();
            return;
        }
        if (!money.matches(moneyRegex)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("你输入的金额有误！");
            alert.setContentText("正确格式为'00.00'，整数位最多8位，小数位最多2位");
            alert.showAndWait();
        }
        String[] orderMsg = {
            name, money, way, type, date, cate, desc
        };
        boolean b = orderInterface.addOrder(bookIndex, orderMsg);
        Alert alert;
        if (b) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("添加成功！");
            alert.showAndWait();
            ((Stage)paneAddOrder.getScene().getWindow()).close();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setHeaderText(null);
            alert.setContentText("添加失败");
            alert.showAndWait();
        }
    }

    public void setBookIndex(int bookIndex) {
        this.bookIndex = bookIndex;
    }

    //初始化
    public void initialization(User user) {
        orderInterface = new OrderInterface(user);
        //order_way
        List<String> listWay = new ArrayList<>();
        listWay.add("现金");
        listWay.add("支付宝");
        listWay.add("微信");
        listWay.add("银行卡");
        listWay.add("信用卡");
        listWay.add("其他");
        ObservableList<String> oListWay = FXCollections.observableList(listWay);
        combWay.setItems(oListWay);
        combWay.getSelectionModel().selectFirst();
        combWay.setEditable(false);
        //order_cate
        List<String> listCate = new ArrayList<>();
        listCate.add("消费");
        listCate.add("转账");
        listCate.add("餐饮");
        listCate.add("交通");
        listCate.add("娱乐");
        listCate.add("购物");
        listCate.add("通讯");
        listCate.add("生活");
        listCate.add("租房");
        listCate.add("医疗");
        listCate.add("教育");
        listCate.add("其他");
        ObservableList<String> oListCate = FXCollections.observableList(listCate);
        combCate.setItems(oListCate);
        combCate.getSelectionModel().selectFirst();
        combCate.setEditable(false);
        //order_mod
        outputRadioButton.setUserData("支出");
        inputRadioButton.setUserData("收入");
        outputRadioButton.setSelected(true);
    }
}

