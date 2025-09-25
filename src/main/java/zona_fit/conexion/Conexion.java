package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        var baseDatos = "zona_fit_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var user = "root";
        var password = "admin";
        try {
            conexion = DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            System.out.println("Error al conectarnos a la BD" + e.getMessage());
        }
        return conexion;
    }
}
