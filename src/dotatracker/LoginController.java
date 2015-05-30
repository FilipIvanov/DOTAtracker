    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotatracker;

import baza.Login;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author WakingBliss
 */
public class LoginController implements Initializable {

    @FXML
    private TextField korIme;
    @FXML
    private PasswordField korSifra;
    @FXML
    private Button potvrdiLog;
    @FXML
    private Button otkaziLog;

    private Connection conn = null;
    private ResultSet rs = null;
    private java.sql.PreparedStatement ps = null;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        conn = Login.get();
       
    }

    public void potvrdiAkcija(ActionEvent event) throws SQLException, IOException   {

        String korisnickoIme = korIme.getText().trim();
        String sifraKorisnika = korSifra.getText().trim();

        String sql = "SELECT * FROM login WHERE username = ? AND password = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, korisnickoIme);
            ps.setString(2, sifraKorisnika);
            rs = ps.executeQuery();

            if (rs.next()) {

                Parent root = FXMLLoader.load(getClass().getResource(
                        "/gui/DOTAtracker.fxml"));
                Stage core = new Stage();
                core.setScene(new Scene(root));
                core.setTitle("Core");
                core.show();
            }
        } 
        catch (Exception e) {
            e.printStackTrace();

        }
    }
}
