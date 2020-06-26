package GUI.controller;

import Core.model.User;
import Core.mutual.Login;
import GUI.Controller;
import GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SetController implements Controller {
    private User user;
    private Stage parentStage;

    @FXML
    private AnchorPane paneSet;

    //关于软件事件
    @FXML
    void aboutSoftwareButtonEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/soft_info.fxml"));
            Parent page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("关于软件");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            SoftInfoController controller = loader.getController();
            controller.initialize(user);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //用户信息
    @FXML
    void userInfoEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/user_info.fxml"));
            Parent page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("用户界面");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            UserInfoController controller = loader.getController();
            controller.initialize(user);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //切换用户
    @FXML
    void changeUserEvent(ActionEvent event) {
        try {
            ((Stage) paneSet.getScene().getWindow()).close();
            parentStage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fxml/sign_in.fxml"));
            Parent page = loader.load();
            Stage stage = new Stage();
            stage.setTitle("登录");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            SignInController controller = loader.getController();
            controller.initialize(user);
            Login login = new Login();
            login.writeConfig("", "", false, false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exitEvent(ActionEvent event) {
        Login login = new Login();
        login.signOut(user.getUserID());
        System.exit(0);
    }

    @Override
    public void initialize(User user) {
        this.user = user;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }
}
