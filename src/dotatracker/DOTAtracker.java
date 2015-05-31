/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotatracker;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author WakingBliss
 */
public class DOTAtracker extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
     
        try {
        Parent root;
	root = FXMLLoader.load(getClass().getResource("/gui/login.fxml"));
        Scene scene = new Scene(root, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("DOTA tracker");
        primaryStage.setResizable(false);
        primaryStage.show();
        
      
        
        
        }catch (Exception e) {
            System.err.print("NECEEEEE");
        }
        
        
       
        

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
