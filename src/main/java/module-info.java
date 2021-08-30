module fonfapres.fonfapres {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.java;


    opens fonfapres.fonfapres1 to javafx.fxml;
    exports fonfapres.fonfapres1;
    opens fonfapres.fonfapres1.Classes;
}