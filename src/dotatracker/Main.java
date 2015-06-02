/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotatracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Tadija
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/gui/login.fxml"));
        Scene scene = new Scene(root, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
