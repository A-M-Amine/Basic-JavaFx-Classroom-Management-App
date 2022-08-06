package sample;

import java.sql.*;

public class DBconnect {


    public static Connection connection(int nb) {

        String url;
        String user;
        String password;

        if (nb == 1) {
            url = "jdbc:oracle:thin:bddadm:1521:ORCL";
            user = "C##BDDAdmin";
            password = "TPAdmin";
        }else if (nb == 2) {
            url = "jdbc:oracle:thin:bddetu:1521:ORCL";
            user = "C##Etudiant";
            password = "TPEtudiant";
        }else {
            url = "jdbc:oracle:thin:bddens:1521:ORCL";
            user = "C##Enseignant";
            password = "TPEnseignant";
        }

        try {
            Connection mycon = DriverManager.getConnection(url, user, password);
            return mycon;

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
