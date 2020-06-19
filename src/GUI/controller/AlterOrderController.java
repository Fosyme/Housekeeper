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
    private ToggleGroup cate;

    @FXML
    private TextField idTextField;

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
        String type=(String)cate.getSelectedToggle().getUserData();//账单类型
        String money=moneyTextField.getText();//账单金额
        String classification=classificationComboBox.getSelectionModel().getSelectedItem();//账单分类
        String memo=memoTextArea.getText();//账单备注
        String date=datePickerText.getValue().toString();//账单日期
        String neworderMsg []={type,money,classification,memo,date};
        boolean a=orderInterface.alterOrder(1,2,neworderMsg);//索引都没有做
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
            alert.setContentText("修改成功");
            alert.setHeaderText("信息");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.UTILITY);
        }

    }

    public void setDialogStage(Stage mainFrameStage) {
    }
//初始化
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
        classificationComboBox.setItems(observableList);

        outputRadioButton.setUserData("支出");
        inputRadioButton.setUserData("收入");
    }
}
