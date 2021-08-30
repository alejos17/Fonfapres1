package fonfapres.fonfapres1.Model;

public class Database {

    //Conectores para Mariadb pero no funcionan con IntelliJ
    //private final String driver = "org.mariadb.jdbc.Driver";
    //private final String url = "jdbc:mariadb://192.168.1.2:3306/GPEDIDOS?user=root&password=Alesan.2021";

    //Conectores de MySQL para la BD que esta en MariaDB en IntelliJ y JAVAFX no funciona el connector de MariaDB
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://192.168.1.2:3306/FONFAPRES1?user=root&password=Alesan.2021";


    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }
}
