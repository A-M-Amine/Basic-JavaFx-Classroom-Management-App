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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class frameBDDController implements Initializable{



    @FXML
    private ComboBox<String> choiceB;

    @FXML
    private TextField tf_1;

    @FXML
    private TextField tf_2;

    @FXML
    private TextField tf_3;

    @FXML
    private TextField tf_4;

    @FXML
    private TextField tf_5;


    @FXML
    private TableView<General> tabG;

    @FXML
    private TableColumn<General, String> c1;

    @FXML
    private TableColumn<General, String> c2;

    @FXML
    private TableColumn<General, String> c3;

    @FXML
    private TableColumn<General, String> c4;

    @FXML
    private TableColumn<General, String> c5;

    ObservableList<General> oblist = FXCollections.observableArrayList();



    public void inserer() {

        if (choiceB.getValue().equals("ENSEIGNANT")) {

            try {
                Connection con = DBconnect.connection(1);
                ResultSet rs = con.createStatement().executeQuery("INSERT INTO ENSEIGNANT VALUES ("+tf_1.getText()+",'"+tf_2.getText()+"','"+tf_3.getText()+"')");

            }catch (SQLException e) { e.printStackTrace();}

        }else if (choiceB.getValue().equals("ETUDIANT")) {
            try {
                Connection con = DBconnect.connection(1);
                ResultSet rs = con.createStatement().executeQuery("INSERT INTO ETUDIANT VALUES ("+tf_1.getText()+",'"+tf_2.getText()+"','"+tf_3.getText()+"',to_date('"+tf_4.getText()+"','dd-mm-yyyy'),'"+tf_5.getText()+"')");

            }catch (SQLException e) { e.printStackTrace();}
        }else if (choiceB.getValue().equals("ETUDIANT UNITE")) {

            try {
                Connection con = DBconnect.connection(1);
                ResultSet rs = con.createStatement().executeQuery("INSERT INTO ETUDIANTUNITE VALUES ("+tf_1.getText()+",'"+tf_2.getText()+"',"+tf_3.getText()+","+tf_4.getText()+","+tf_5.getText()+")");

            }catch (SQLException e) { e.printStackTrace();}
        }else if (choiceB.getValue().equals("UNITE")) {

            try {
                Connection con = DBconnect.connection(1);
                ResultSet rs = con.createStatement().executeQuery("INSERT INTO UNITE VALUES ('"+tf_1.getText()+"','"+tf_2.getText()+"',"+tf_3.getText()+","+tf_4.getText()+") ");

            }catch (SQLException e) { e.printStackTrace();}
        }

    }

    public void changerScene() {

        if (choiceB.getValue().equals("ENSEIGNANT")) setScene_ENSEIGNANT();
        else if (choiceB.getValue().equals("ETUDIANT")) setScene_ETUDIANT();
        else if (choiceB.getValue().equals("ETUDIANT UNITE")) setScene_ETUDIANTUNITE();
        else if (choiceB.getValue().equals("UNITE")) setScene_UNITE();
    }

    public void setScene_ETUDIANT() {

        tabG.getItems().clear();
        oblist.clear();

        tf_4.setVisible(true);
        tf_5.setVisible(true);
        c4.setVisible(true);
        c5.setVisible(true);

        tf_1.setPromptText("matricule");
        tf_2.setPromptText("nom");
        tf_3.setPromptText("prénom");
        tf_4.setPromptText("date naissance");
        tf_5.setPromptText("adresse");

        c1.setText("Matricule_etu");
        c2.setText("Nom_etu");
        c3.setText("Prenom_etu");
        c4.setText("Date Naissance");
        c5.setText("Adresse");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("SELECT * from ETUDIANT");

            while (rs.next()) {

                oblist.add(new General(Integer.toString(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));
        c3.setCellValueFactory(new PropertyValueFactory<>("var3"));
        c4.setCellValueFactory(new PropertyValueFactory<>("var4"));
        c5.setCellValueFactory(new PropertyValueFactory<>("var5"));


        tabG.setItems(oblist);

    }



    public void setScene_ENSEIGNANT() {

        tabG.getItems().clear();
        oblist.clear();

        tf_1.setPromptText("matricule_ens");
        tf_2.setPromptText("nom_ens");
        tf_3.setPromptText("prénom_ens");
        tf_4.setVisible(false);
        tf_5.setVisible(false);

        c1.setText("Matricule_ens");
        c2.setText("Nom_ens");
        c3.setText("Prenom_ens");
        c4.setVisible(false);
        c5.setVisible(false);

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("SELECT * from ENSEIGNANT");

            while (rs.next()) {

                oblist.add(new General(Integer.toString(rs.getInt(1)),rs.getString(2),rs.getString(3)));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));
        c3.setCellValueFactory(new PropertyValueFactory<>("var3"));

        tabG.setItems(oblist);


    }

    public void setScene_ETUDIANTUNITE() {

        tabG.getItems().clear();
        oblist.clear();

        tf_4.setVisible(true);
        tf_5.setVisible(true);
        c4.setVisible(true);
        c5.setVisible(true);

        tf_1.setPromptText("matricule_etu");
        tf_2.setPromptText("code unite");
        tf_3.setPromptText("note cc");
        tf_4.setPromptText("note tp");
        tf_5.setPromptText("note examen");

        c1.setText("Matricule_etu");
        c2.setText("Code_Unite");
        c3.setText("note_CC");
        c4.setText("note_TP");
        c5.setText("Note_Examen");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("SELECT * from ETUDIANTUNITE");

            while (rs.next()) {

                oblist.add(new General(Integer.toString(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));
        c3.setCellValueFactory(new PropertyValueFactory<>("var3"));
        c4.setCellValueFactory(new PropertyValueFactory<>("var4"));
        c5.setCellValueFactory(new PropertyValueFactory<>("var5"));


        tabG.setItems(oblist);

    }

    public void setScene_UNITE() {

        tabG.getItems().clear();
        oblist.clear();


        tf_4.setVisible(true);
        tf_5.setVisible(false);
        c4.setVisible(true);
        c5.setVisible(false);

        tf_1.setPromptText("code unité");
        tf_2.setPromptText("libelle");
        tf_3.setPromptText("nb heures");
        tf_4.setPromptText("matricule_ens");

        c1.setText("Code_UNITE");
        c2.setText("Libelle");
        c3.setText("Nbr_Heures");
        c4.setText("Matricule_ens");

        try{
            Connection con = DBconnect.connection(1);
            ResultSet rs = con.createStatement().executeQuery("SELECT * from UNITE");

            while (rs.next()) {

                oblist.add(new General(rs.getString(1),rs.getString(2),Integer.toString(rs.getInt(3)),Integer.toString(rs.getInt(4))));

            }
        }catch (SQLException ex) { ex.printStackTrace(); }

        c1.setCellValueFactory(new PropertyValueFactory<>("var1"));
        c2.setCellValueFactory(new PropertyValueFactory<>("var2"));
        c3.setCellValueFactory(new PropertyValueFactory<>("var3"));
        c4.setCellValueFactory(new PropertyValueFactory<>("var4"));

        tabG.setItems(oblist);


    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {

        choiceB.getItems().addAll("ENSEIGNANT","ETUDIANT","ETUDIANT UNITE","UNITE");
        choiceB.setValue("ENSEIGNANT");
        changerScene();

    }


    public void changeScreen(ActionEvent event) throws IOException {

        Parent tableBD_Parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene_etu = new Scene(tableBD_Parent);

        Stage window_etu = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window_etu.setScene(scene_etu);
        window_etu.show();

    }

    public void versRequetes(ActionEvent event) throws IOException {

        Parent tableBD_Parent = FXMLLoader.load(getClass().getResource("tp6.fxml"));
        Scene scene_etu = new Scene(tableBD_Parent);

        Stage window_etu = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window_etu.setScene(scene_etu);
        window_etu.show();

    }

}

