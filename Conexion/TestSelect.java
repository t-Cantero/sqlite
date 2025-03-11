package Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {
    public static void main(String[] args) {
        String sql = "SELECT * FROM instituts"; // Cambia "tu_tabla" por una tabla real

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Conexión exitosa. Datos obtenidos:");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " +
                        rs.getString(2) + " | " +
                        rs.getString(3) + " | " +
                        rs.getString(4));
            }


        } catch (SQLException e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();
        }
    }
}
