package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class frameENSController {

    @FXML
    private TableView<Enseignant> table_ens;

    @FXML
    private TableColumn<Enseignant, String> matens;

    @FXML
    private TableColumn<Enseignant, String> nomens;

    @FXML
    private TableColumn<Enseignant, String> prenomens;

    @FXML
    private TextField Mat;

    @FXML
    private TextField nm;

    @FXML
    private TextField pm;

    @FXML
    private TextField matens_saisie;

    ObservableList<Enseignant> oblist_ens = FXCollections.observableArrayList();


    public void loadData() {

        try {
            Connection con = DBconnect.connection(3);
            ResultSet rs = con.createStatement().executeQuery("SELECT * from C##BDDAdmin.ENSEIGNANT WHERE matricule_ens = " + matens_saisie.getText());

            while (rs.next()) {

                oblist_ens.add(new Enseignant(rs.getInt(1),rs.getString(2),rs.getString(3)));

            }

        }catch (SQLException e) { e.printStackTrace();}

        matens.setCellValueFactory(new PropertyValueFactory<>("matricule_ens"));
        nomens.setCellValueFactory(new PropertyValueFactory<>("nom_ens"));
        prenomens.setCellValueFactory(new PropertyValueFactory<>("prenom_ens"));

        table_ens.setItems(oblist_ens);

    }


    public void insererdata() {

        try {
            Connection con = DBconnect.connection(3);
            ResultSet rs = con.createStatement().executeQuery("INSERT INTO C##BDDAdmin.ENSEIGNANT VALUES ("+Mat.getText()+",'"+nm.getText()+"','"+pm.getText()+"')");


        }catch (SQLException e) { e.printStackTrace();}

    }



    public void changeScreen(ActionEvent event) throws IOException {

        Parent tableBD_Parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene_etu = new Scene(tableBD_Parent);

        Stage window_etu = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window_etu.setScene(scene_etu);
        window_etu.show();

    }

}
