package GUI.controller;

import Core.model.User;
import GUI.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SoftInfoController implements Controller {
    public AnchorPane softInfo;

    @FXML
    void closeButtonEvent(ActionEvent event) {
        ((Stage) softInfo.getScene().getWindow()).close();
    }

    @Override
    public void initialize(User user) {

    }
}
