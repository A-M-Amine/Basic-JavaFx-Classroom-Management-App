package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class tp6Controller implements Initializable {

    @FXML
    private TableView<General> tab_tp6;

    @FXML
    private TableColumn<General, String> c1;

    @FXML
    private TableColumn<General, String> c2;

    @FXML
    private TableColumn<General, String> c3;

    @FXML
    private TableColumn<General, String> c4;

    @FXML
    private ToggleGroup tache;

    @FXML
    private RadioButton t_1;

    @FXML
    private RadioButton t_2;

    @FXML
    private RadioButton t_3;

    @FXML
    private RadioButton t_4;

    ObservableList<General> oblist = FXCollections.observableArrayList();



    public void tache_1() {

        tab_tp6.getItems().clear();
        oblist.clear();

        c2.setVisible(true);
        c3.setVisible(false);
        c4.setVisible(false);
        c1.setText("Nom");
        c2.setText("Prénom");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("select distinct nom_etu, prenom_etu from etudiant e INNER JOIN etudiantunite eu ON e.matricule_etu = eu.matricule_etu and eu.note_examen = 20");

            while (rs.next()) {

                oblist.add(new General(rs.getString(1),rs.getString(2)));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));


        tab_tp6.setItems(oblist);
    }

    public void tache_2() {


        tab_tp6.getItems().clear();
        oblist.clear();

        c2.setVisible(true);
        c3.setVisible(false);
        c4.setVisible(false);
        c1.setText("Nom");
        c2.setText("Prénom");


        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("SELECT DISTINCT nom_etu, prenom_etu from etudiant e INNER JOIN (SELECT matricule_etu from etudiantunite eu where NOT EXISTS(\tSELECT * from etudiantunite eu2 where eu2.matricule_etu = eu.matricule_etu and eu2.code_unite = 'FEI0001') ) g on e.matricule_etu = g.matricule_etu");

            while (rs.next()) {

                oblist.add(new General(rs.getString(1),rs.getString(2)));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));


        tab_tp6.setItems(oblist);

    }

    public void  tache_3() {


        tab_tp6.getItems().clear();
        oblist.clear();

        c2.setVisible(false);
        c3.setVisible(false);
        c4.setVisible(false);
        c1.setText("Libellé");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("SELECT libelle from unite u where u.code_unite not in (select code_unite from etudiantunite)");

            while (rs.next()) {

                oblist.add(new General(rs.getString(1)));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));

        tab_tp6.setItems(oblist);


    }

    public void tache_4() {

        tab_tp6.getItems().clear();
        oblist.clear();

        c2.setVisible(true);
        c3.setVisible(true);
        c4.setVisible(true);

        c1.setText("Nom");
        c2.setText("Prenom");
        c3.setText("Moyenne");
        c4.setText("Libellé");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("SELECT DISTINCT etudiant.nom_etu, etudiant.prenom_etu, (etudiantunite.note_CC+ etudiantunite.note_TP + 2 * etudiantunite.note_examen) / 4 as moy, unite.libelle from etudiant, etudiantunite, unite where etudiant.matricule_etu = etudiantunite.matricule_etu and unite.code_unite = etudiantunite.code_unite ORDER BY etudiant.nom_etu");

            while (rs.next()) {

                oblist.add(new General(rs.getString(1),rs.getString(2),Float.toString(rs.getFloat(3)),rs.getString(4)));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));
        c3.setCellValueFactory(new PropertyValueFactory<>("var3"));
        c4.setCellValueFactory(new PropertyValueFactory<>("var4"));


        tab_tp6.setItems(oblist);

    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {

        t_1.setSelected(true);
        tache_1();
    }

    public void back(ActionEvent event) throws IOException {

        Parent tableBD_Parent = FXMLLoader.load(getClass().getResource("frameBDD.fxml"));
        Scene scene_etu = new Scene(tableBD_Parent);

        Stage window_etu = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window_etu.setScene(scene_etu);
        window_etu.show();

    }

    public void next(ActionEvent event) throws IOException {

        Parent tableBD_Parent = FXMLLoader.load(getClass().getResource("tp8.fxml"));
        Scene scene_etu = new Scene(tableBD_Parent);

        Stage window_etu = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window_etu.setScene(scene_etu);
        window_etu.show();

    }

}
