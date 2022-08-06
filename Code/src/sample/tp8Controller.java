package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class tp8Controller implements Initializable {

    @FXML
    private TableView<General> tab_tp8;

    @FXML
    private TableColumn<General, String> c1;

    @FXML
    private TableColumn<General, String> c2;

    @FXML
    private TableColumn<General, String> c3;


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

    @FXML
    private RadioButton t_5;


    ObservableList<General> oblist = FXCollections.observableArrayList();

    @FXML
    void tache_1() {
        tab_tp8.getItems().clear();
        oblist.clear();

        c2.setVisible(true);
        c3.setVisible(false);
        c1.setText("Matricule");
        c2.setText("Moyenne Génerale");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("SELECT matricule_etu,AVG((note_cc + note_tp+ 2*note_examen)/4) AS moyenne_generale FROM EtudiantUnite Group by matricule_etu");

            while (rs.next()) {

                DecimalFormat df = new DecimalFormat("0.00");
                oblist.add(new General(rs.getString(1),df.format(rs.getDouble(2))));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));


        tab_tp8.setItems(oblist);

    }

    @FXML
    void tache_2(ActionEvent event) {

        tab_tp8.getItems().clear();
        oblist.clear();

        c2.setVisible(true);
        c3.setVisible(false);
        c1.setText("Libellé");
        c2.setText("Moyenne De Section");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("SELECT libelle, AVG(moy) from V_MOYENNE GROUP BY libelle ORDER BY libelle");

            while (rs.next()) {

                DecimalFormat df = new DecimalFormat("0.00");
                oblist.add(new General(rs.getString(1),df.format(rs.getDouble(2))));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));


        tab_tp8.setItems(oblist);

    }

    @FXML
    void tache_3(ActionEvent event) {
        tab_tp8.getItems().clear();
        oblist.clear();

        c2.setVisible(false);
        c3.setVisible(false);
        c1.setText("Moyenne Générale de la section");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("SELECT SUM(e.moyenne_generale)/COUNT(*) as sg from (SELECT matricule_etu,AVG((note_cc + note_tp+ 2*note_examen)/4) AS moyenne_generale FROM EtudiantUnite Group by matricule_etu) e");

            while (rs.next()) {

                DecimalFormat df = new DecimalFormat("0.00");
                oblist.add(new General(df.format(rs.getDouble(1))));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));


        tab_tp8.setItems(oblist);
    }

    @FXML
    void tache_4(ActionEvent event) {
        tab_tp8.getItems().clear();
        oblist.clear();

        c2.setVisible(true);
        c3.setVisible(true);
        c1.setText("libellé");
        c2.setText("Max Moyenne");
        c3.setText("Min Moyenne");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("select libelle, MAX(moy), MIN(moy) from v_moyenne GROUP BY libelle");

            while (rs.next()) {

                DecimalFormat df = new DecimalFormat("0.00");
                oblist.add(new General(rs.getString(1),df.format(rs.getDouble(2)),df.format(rs.getDouble(3))));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));
        c3.setCellValueFactory(new PropertyValueFactory<>("var3"));

        tab_tp8.setItems(oblist);
    }


    @FXML
    void tache_5(ActionEvent event) {

        tab_tp8.getItems().clear();
        oblist.clear();

        c2.setVisible(true);
        c3.setVisible(true);
        c1.setText("Matricule");
        c2.setText("Nom");
        c3.setText("Prénom");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("select matricule_etu, nom_etu, prenom_etu from V_MOYGENERALE where moyg >= (SELECT SUM(e.moyenne_generale)/COUNT(*) as sg from (SELECT matricule_etu,AVG((note_cc + note_tp+ 2*note_examen)/4) AS moyenne_generale FROM EtudiantUnite Group by matricule_etu) e)");

            while (rs.next()) {

                oblist.add(new General(rs.getString(1),rs.getString(2),rs.getString(3)));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));
        c3.setCellValueFactory(new PropertyValueFactory<>("var3"));


        tab_tp8.setItems(oblist);


    }


    @Override
    public void initialize(URL location, ResourceBundle resource) {

        t_1.setSelected(true);
        tache_1();
    }


    public void back(ActionEvent event) throws IOException {

        Parent tableBD_Parent = FXMLLoader.load(getClass().getResource("tp6.fxml"));
        Scene scene_etu = new Scene(tableBD_Parent);

        Stage window_etu = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window_etu.setScene(scene_etu);
        window_etu.show();

    }



}