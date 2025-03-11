package Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getAllEmpleados {
    public static void main(String[] args) {
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            cn = Conexion.DatabaseConnection.getConnection(); // Se obtiene la conexión dentro del try
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM empleados");

            System.out.println("Número  Nombre  Dep  Edad  Sueldo");
            System.out.println("---------------------------------");

            while (rs.next()) {
                System.out.print(rs.getInt("num") + "\t\t");
                System.out.print(rs.getString("nombre") + "\t");
                System.out.print(rs.getInt("departamento") + "\t ");
                System.out.print(rs.getInt("edad") + "\t\t");
                System.out.println(rs.getDouble("sueldo"));
            }

        } catch (SQLException e) {
            System.out.println("Se ha producido un error al leer los empleados. Mensaje: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Se ha producido un error al cerrar el ResultSet: " + e.getMessage());
            }

            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println("Se ha producido un error al cerrar el Statement: " + e.getMessage());
            }

            try {
                if (cn != null && !cn.isClosed()) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println("Se ha producido un error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
