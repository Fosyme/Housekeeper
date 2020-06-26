package GUI;

import Core.mutual.Login;
import GUI.controller.SignInController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class OpenFormAfterThis {
    public static void signIn(String userName, String userPassword) {
        Login login = new Login();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/sign_in.fxml"));
            Parent page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("用户登录");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            SignInController controller = loader.getController();
            //先赋予信息再做初始化(含监听器)
            controller.smartFill(userName, userPassword);
            controller.setRemember(false);
            controller.initialize(null);
            stage.setOnCloseRequest(windowEvent ->
                    login.writeConfig(userName, "", false, false));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exitApp(WindowEvent windowEvent, String userID) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("提示");
        alert.setHeaderText(null);
        alert.setContentText("是否退出程序？");
        Optional<ButtonType> optional = alert.showAndWait();
        optional.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                new Login().signOut(userID);
                System.exit(0);
            }
        });
        windowEvent.consume();
    }
}
