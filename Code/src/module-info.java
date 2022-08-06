module BDD {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.fontawesomefx.fontawesome;
    requires ojdbc6;



    opens sample to javafx.fxml;
    exports sample;
}