package GUI;

import Core.UserLogin;
import GUI.controller.SignInController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class OpenFormAfterThis {
    public static void signIn(Stage window, String userName, String userPassword) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/signIn.fxml"));
            Pane page = loader.load();
            Stage signIn = new Stage();
            signIn.setTitle("用户登录");
            signIn.setResizable(false);
            signIn.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            signIn.setScene(scene);
            SignInController controller = loader.getController();
            controller.initialization();
            controller.afterSignUp(userName, userPassword);
            window.close();
            signIn.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void signIn(Stage window) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/signIn.fxml"));
            Pane page = loader.load();
            Stage signIn = new Stage();
            signIn.setTitle("用户登录");
            signIn.setResizable(false);
            signIn.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            signIn.setScene(scene);
            window.close();
            signIn.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exitApp(WindowEvent windowEvent, UserLogin userLogin) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("提示");
        alert.setHeaderText(null);
        alert.setContentText("是否退出程序？");
        Optional<ButtonType> optional = alert.showAndWait();
        optional.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                userLogin.signOut();
                System.exit(0);
            }
        });
        windowEvent.consume();
    }
}
