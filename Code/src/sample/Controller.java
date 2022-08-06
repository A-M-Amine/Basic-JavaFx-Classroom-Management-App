package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private AnchorPane leftanc;

    @FXML
    private TextField user_name;

    @FXML
    private PasswordField passwordF;

    @FXML
    private Button login_button;

    @FXML
    private Label errormsg;

    @FXML
    void loginButtonClick(ActionEvent event) throws IOException {

        String user = user_name.getText();
        String pass = passwordF.getText();
        if (verifyLogin(user,pass) == 1) {
            changeScreen(event,1);
        }else if (verifyLogin(user,pass) == 2) {
            changeScreen(event, 2);
        }else if (verifyLogin(user,pass) == 3) {
            changeScreen(event,3);
        }
        else errormsg.setText("nom d'utilisateur ou mdp incorrect !");


        System.out.println(user + "   " + pass);
    }

    public void changeScreen(ActionEvent event,int nb) throws IOException {

        String scence_nb;
        if (nb == 1) scence_nb = "frameBDD.fxml";
        else if (nb == 2) scence_nb = "tableBD.fxml";
        else scence_nb = "frameENS.fxml";

        Parent tableBD_Parent = FXMLLoader.load(getClass().getResource(scence_nb));
        Scene scene_etu = new Scene(tableBD_Parent);

        Stage window_etu = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window_etu.setScene(scene_etu);
        window_etu.show();

    }



    static int verifyLogin(String user,String pass) {

        if (user.equals("BDDAdmin")) {
            if (pass.equals("TPAdmin")) return 1;
            else return -1;
        }else if (user.equals("Etudiant")) {
            if (pass.equals("TPEtudiant")) return 2;
            else return -1;
        }else if (user.equals("Enseignant")) {
            if (pass.equals("TPEnseignant")) return 3;
            else return -1;
        }
        return -1;
    }

}
