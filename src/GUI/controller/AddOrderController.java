package GUI.controller;

import Core.OrderInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class AddOrderController {
    private OrderInterface orderInterface;
    @FXML
    private ToggleGroup way;

    @FXML
    private TextField accountbookname;
    @FXML
    private TextField moneyTextField;

    @FXML
    private ComboBox<?> classificationComboBox;

    @FXML
    private DatePicker datePickerTextField;

    @FXML
    private Button addButton;

    @FXML
    private TextArea memoTextArea;

    @FXML
    private RadioButton outputRadioButton;

    @FXML
    private ToggleGroup RadioButton;

    @FXML
    private RadioButton inputRadioButton;

    @FXML
    private ComboBox<String> accountbookComboBox;

    @FXML
    void accountbookComboBoxEvent(ActionEvent event) {

    }

    @FXML
    void addButtonEvent(ActionEvent event) {
        String type=(String)way.getSelectedToggle().getUserData();//账单类型
        String money=moneyTextField.getText();//账单金额
        String classification=accountbookComboBox.getSelectionModel().getSelectedItem();//账单分类
        String memo=memoTextArea.getText();//账单备注
        String date=datePickerTextField.getValue().toString();//账单日期
        String orderMsg[]={type,money,classification,memo,date};

       boolean c=  orderInterface.addOrder(1,orderMsg);//要获取账本索引，没做
       if (c){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("添加");
           alert.setContentText("添加成功");
           alert.setHeaderText("信息");
           Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
           stage.initStyle(StageStyle.UTILITY);

       }
    }

    public void setDialogStage(Stage mainFrameStage) {

    }

    //    初始化
    public void initialization() {
        List<String> list = new ArrayList<>();
        list.add("吃饭");
        list.add("网购");
        list.add("电费");
        list.add("水费");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        ObservableList<String> observableList = FXCollections.observableList(list);
        accountbookComboBox.setItems(observableList);

        outputRadioButton.setUserData("支出");
        inputRadioButton.setUserData("收入");


    }
}

