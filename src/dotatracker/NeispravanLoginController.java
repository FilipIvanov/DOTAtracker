/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotatracker;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author WakingBliss
 */
public class NeispravanLoginController implements Initializable {
    
    
    @FXML 
    private Button pogresanLoginDugme;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void potvrdiPogresanLogin(ActionEvent event){
    
        Stage stage = (Stage) pogresanLoginDugme.getScene().getWindow();
        stage.close();
    
    
    }
    
}
