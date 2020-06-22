package GUI;

import GUI.controller.SignInController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
}
