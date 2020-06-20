package GUI.controller;

import Core.OrderInterface;
import GUI.OpenFormAfterThis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class AddOrderController {

    private OrderInterface orderInterface;

    @FXML
    public AnchorPane paneFindPwd;
    @FXML
    private ToggleGroup way;
    @FXML
    private TextField billnameTextField;


    @FXML
    private TextField moneyTextField;

    @FXML
    private ComboBox<String> classificationComboBox;

    @FXML
    private DatePicker datePickerTextField;

    @FXML
    private Button addButton;

    @FXML
    private TextArea memoTextArea;

    @FXML
    private RadioButton outputRadioButton;

    @FXML
    private RadioButton inputRadioButton;



    @FXML
    void addButtonEvent(ActionEvent event) {
        String type=(String)way.getSelectedToggle().getUserData();//账单类型
        String name=billnameTextField.getText();//账单名字
        String money=moneyTextField.getText();//账单金额
        String classification= (String) classificationComboBox.getSelectionModel().getSelectedItem();//账单分类
        String memo=memoTextArea.getText();//账单备注
        String date=datePickerTextField.getValue().toString();//账单日期
        String orderMsg[]={type,name,money,classification,memo,date};
       boolean c=  orderInterface.addOrder(2,orderMsg);//要获取账本索引，没做
       if (c){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle(null);
           alert.setHeaderText("密码修改成功！");
           alert.setContentText("请记住你的密码是：" );
           alert.showAndWait();
           OpenFormAfterThis.signIn((Stage) paneFindPwd.getScene().getWindow());

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
        ObservableList<String> observableList = FXCollections.observableList(list);
        classificationComboBox.setItems(observableList);

        outputRadioButton.setUserData("支出");
        inputRadioButton.setUserData("收入");


    }
}

