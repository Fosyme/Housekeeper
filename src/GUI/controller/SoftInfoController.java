package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SoftInfoController {
    private Stage dialogStage;

    @FXML
    private Button closeButton;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void closeButtonEvent(ActionEvent event) {
        dialogStage.close();
    }
}
