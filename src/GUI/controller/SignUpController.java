package GUI.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SignUpController {

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField SecretprotectionproblemTextField;

    @FXML
    private TextField SexTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField answerTextField;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField CellPhonenumberTextField;

    @FXML
    private TextField AddressTextField;

    @FXML
    private ComboBox<String> comBoxQuestion;

    @FXML
    void LoginButtonEvent(ActionEvent event) {
        var a = nameTextField.getText();//用户名
        if (a.length() <= 3 || a.length() >= 8) {

        }
    }

    public void initialization() {
        List<String> list = new ArrayList<>();
        list.add("你第个宠物的名字是什么?");
        list.add("你出生城市的名称是什么?");
        list.add("你孩童时期的呢称是什么?");
        list.add("你父母相遇的城市的名称是什么?");
        list.add("你年纪最大的表亲的名字是什么?");
        list.add("你的母校名称是什么?");
        ObservableList<String> observableList = FXCollections.observableList(list);
        comBoxQuestion.setItems(observableList);
    }

    public void setDialogStage(Stage mainFrameStage) {
    }
}
