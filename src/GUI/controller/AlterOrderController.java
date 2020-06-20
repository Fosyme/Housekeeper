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

public class AlterOrderController {
    private OrderInterface orderInterface;
   @FXML
   private TextField nameTextField;
    @FXML
    private ToggleGroup cate;
    @FXML
    private ComboBox<String> combType;

    @FXML
    private RadioButton outputRadioButton;

    @FXML
    private Button alterButton;

    @FXML
    private TextField moneyTextField;

    @FXML
    private DatePicker datePickerText;

    @FXML
    private Button checkButton;

    @FXML
    private RadioButton inputRadioButton;

    @FXML
    private TextArea memoTextArea;

    @FXML
    private ComboBox<String> classificationComboBox;
    private int selectedBookIndex;
    private int selectedorderIndex;

    @FXML
    void outputRadioButtonEvent(ActionEvent event) {

    }

    @FXML
    void inputRadioButtonEvent(ActionEvent event) {

    }

    @FXML
    void classificationComboBoxEvent(ActionEvent event) {

    }

    @FXML
    void checkButtonEvent(ActionEvent event) {

    }

    @FXML
    void alterButtonEvent(ActionEvent event) {

        String type = (String) cate.getSelectedToggle().getUserData();  //账单类型
        String name = nameTextField.getText();                          //账单名字
        String money = moneyTextField.getText();                            //账单金额
        String way = combType.getSelectionModel().getSelectedItem();         //账单方式
        String cate = classificationComboBox.getSelectionModel().getSelectedItem();       //账单分类
        String desc = memoTextArea.getText();                               //账单备注
        String date = datePickerText.getValue().toString();
        String neworderMsg []={type,name,money,way,cate,desc,date};

        boolean a=orderInterface.alterOrder(selectedBookIndex,selectedorderIndex,neworderMsg);//索引都没有做
        if (a){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("修改");
            alert.setContentText("修改成功");
            alert.setHeaderText("信息");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.UTILITY);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("修改");
            alert.setContentText("修改失败");
            alert.setHeaderText("信息");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.UTILITY);
        }

    }

    public void setDialogStage(Stage mainFrameStage) {
    }
//初始化
    public void initialization() {
        //order_way
        List<String> listWay = new ArrayList<>();
        listWay.add("现金");
        listWay.add("支付宝");
        listWay.add("微信");
        listWay.add("银行卡");
        listWay.add("信用卡");
        listWay.add("其他");
        ObservableList<String> oListWay = FXCollections.observableList(listWay);
        combType.setItems(oListWay);
        combType.getSelectionModel().selectFirst();
        combType.setEditable(false);
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
        classificationComboBox.setItems(oListCate);
        classificationComboBox.getSelectionModel().selectFirst();
        classificationComboBox.setEditable(false);
        //order_mod
        outputRadioButton.setUserData("支出");
        inputRadioButton.setUserData("收入");
        outputRadioButton.setSelected(true);
    }
    public void setSelectedBookIndex(int selectedBookIndex,int selectedorderIndex) {

        this.selectedBookIndex = selectedBookIndex;
        this.selectedorderIndex=selectedorderIndex;
    }
}
