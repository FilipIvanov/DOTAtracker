/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dotatracker;

import baza.Login;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author WakingBliss
 */
public class DOTAtracker extends Application {
    
    // polja za igrace
    @FXML
    private TextField imeIgracaPolje;
    @FXML
    private TextField ulogaIgracaPolje;
    @FXML 
    private TextField godineIgracaPolje;
    @FXML
    private TextField omiljeniHerojPolje;
    
    // kolone za igrace
    @FXML
    private TableColumn<Igrac, String> imeIgracaKol;
    @FXML 
    private TableColumn <Igrac, String> ulogaIgracaKol;
    @FXML
    private TableColumn <Igrac, Integer> godineIgracaKol;
    @FXML
    private TableColumn <Igrac, String> omiljeniHerojKol;
    
    
    //tabela
    @FXML
    private TableView<Igrac> tabelaIgraca;
    
    //lista
    private ObservableList<Igrac> podaciIgraca;

    
    
    
    public static class Igrac {
    
      //  private SimpleIntegerProperty id = new SimpleIntegerProperty();
        private SimpleStringProperty ime = new SimpleStringProperty();
        private SimpleStringProperty uloga = new SimpleStringProperty();
        private SimpleIntegerProperty godine = new SimpleIntegerProperty();
        private SimpleStringProperty omiljeniHeroj = new SimpleStringProperty();
        
        public Igrac( String ime, String uloga, Integer godine, String omiljeniHeroj){
        this.ime.setValue(ime);
        this.uloga.setValue(uloga);
        this.godine.setValue(godine);
        this.omiljeniHeroj.setValue(omiljeniHeroj);
        }
        
//        public Integer getId(){
//            if(id!= null){
//            return 0;
//            }
//                return id.getValue();
//        }
//        
//        
        public String getIme(){
            if(ime!= null){
            return "";
            }
            return ime.getValueSafe();
        }
        
        public String getUloga(){
            if(uloga!= null){
            return "";
            }
            return uloga.getValueSafe();
        }
        
        public Integer getGodine(){
            if(godine!= null){
            return 0;
            }
            
            return godine.getValue();
        }
        
//        public SimpleIntegerProperty idProperty() {
//            return id;
//        }
//       
        public SimpleStringProperty imeProperty() {
            return ime;
        }
        public SimpleStringProperty ulogaProperty(){
            return uloga;
        }
        public SimpleIntegerProperty godineProperty(){
            return godine;
        }
        public SimpleStringProperty omiljeniHerojProperty(){
            return omiljeniHeroj;
        }
    
        
    
    }
    
    public void initialize (URL url, ResourceBundle rb){
    
        imeIgracaKol.setCellValueFactory(new PropertyValueFactory<Igrac, String>("ime_igraca"));
        ulogaIgracaKol.setCellValueFactory(new PropertyValueFactory<Igrac, String>("uloga"));
        godineIgracaKol.setCellValueFactory(new PropertyValueFactory<Igrac, Integer>("godine"));
        omiljeniHerojKol.setCellValueFactory(new PropertyValueFactory<Igrac, String>("omiljeni_heroj"));
  
        
         podaciIgraca = FXCollections.observableArrayList();
         tabelaIgraca.setItems(podaciIgraca);
         tabelaIgraca.setEditable(false);
         Login.get();
         ucitajPodatkeIgraca();
    }
    
    public void dodajIgracaAkcija(ActionEvent event) throws IOException, SQLException{
        
        
        System.err.println("GRESKA");
        boolean uspesno = true;

        String sql = ("INSERT INTO igrac (`ime_igraca`, `uloga`, `godine`, `omiljeni_heroj`) VALUES (?,?,?,?)");
        PreparedStatement ps = Login.get().prepareStatement(sql);
        
        String ime = imeIgracaPolje.getText();
        if ((ime == null) || (ime.trim().equals(""))){
            uspesno = false;
        }
        else{
            ps.setString(1, ime);
        }
        String uloga = ulogaIgracaPolje.getText();
        if ((uloga == null) || (uloga.trim().equals(""))){
            uspesno = false;
        }
        else{
            ps.setString(2, uloga);
        }
       
        Integer godine = null;
        
        try {
            godine = Integer.parseInt(godineIgracaPolje.getText());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        if (godine == null){
            uspesno = false;
        }
        else
           
            ps.setInt(3, godine);
            
        
        String omiljeniHeroj = omiljeniHerojPolje.getText();
        if ((omiljeniHeroj == null) || (omiljeniHeroj.trim().equals(""))){
            uspesno = false;
        }
        else{
            ps.setString(4, omiljeniHeroj);
        }
        
        
        
        if(uspesno){
            
                podaciIgraca.add(new Igrac( imeIgracaPolje.getText(),ulogaIgracaPolje.getText(), Integer.parseInt(godineIgracaPolje.getText()), omiljeniHerojPolje.getText()));
            
        ps.execute();
        imeIgracaPolje.clear();
        ulogaIgracaPolje.clear();
        godineIgracaPolje.clear();
        omiljeniHerojPolje.clear();
        
        }
        
        
    }
    
    private void ucitajPodatkeIgraca() {
        try{
            ResultSet rs = Login.query ("SELECT * FROM igrac");
            podaciIgraca.clear();
            while (rs.next()){
            
                
                
            podaciIgraca.add(new Igrac( rs.getString("ime_igraca"), rs.getString("uloga"), 
                        rs.getInt("godine"), rs.getString("omiljeni_heroj")));
            
            }
        
        }
        catch (Exception e){
            System.out.println("" + e.getMessage());
       }
    }
   
    
    
    
    
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
