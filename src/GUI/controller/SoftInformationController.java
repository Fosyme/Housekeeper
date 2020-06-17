package GUI.controller;

import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SoftInformationController {

    private Stage dialogStage;
    public Stage getDialogStage() {
        return dialogStage;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private Button closeButton;

    @FXML
    void closeButtonEvent(ActionEvent event) {
        dialogStage.close();
    }


}
