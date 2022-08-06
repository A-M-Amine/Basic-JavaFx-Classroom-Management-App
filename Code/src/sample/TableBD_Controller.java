package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableBD_Controller implements Initializable {

    @FXML
    private Button diconnectButton;

    @FXML
    private Button loadButton;

    @FXML
    private TableView<Etudiant> table_etu;

    @FXML
    private TableColumn<Etudiant, Integer> matetu;

    @FXML
    private TableColumn<Etudiant, String> nometu;

    @FXML
    private TableColumn<Etudiant, String> prenometu;

    @FXML
    private TableColumn<Etudiant, String> datenaiss;

    @FXML
    private TableColumn<Etudiant, String> adresse;

    @FXML
    private TextField mat_saisie;


    ObservableList<Etudiant> oblist_etu = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resource) {


    }

    public void loaddata() {

        try{
            Connection con = DBconnect.connection(2);
            ResultSet rs = con.createStatement().executeQuery("SELECT * from C##BDDAdmin.ETUDIANT WHERE matricule_etu = " + mat_saisie.getText());

            while (rs.next()) {

                oblist_etu.add(new Etudiant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        matetu.setCellValueFactory(new PropertyValueFactory<>("matricule_etu"));
        nometu.setCellValueFactory(new PropertyValueFactory<>("nom_etu"));
        prenometu.setCellValueFactory(new PropertyValueFactory<>("prenom_etu"));
        datenaiss.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        table_etu.setItems(oblist_etu);
    }



    public void changeScreen(ActionEvent event) throws IOException {

        Parent tableBD_Parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene_etu = new Scene(tableBD_Parent);

        Stage window_etu = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window_etu.setScene(scene_etu);
        window_etu.show();

    }
}
