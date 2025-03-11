package Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataStatement {
    public static void main(String[] args) {
        Connection cn = null;
        Statement st = null;
        String sql;

        try {
            cn = Conexion.DatabaseConnection.getConnection(); // Se obtiene la conexión dentro del try
            st = cn.createStatement();

            sql = "INSERT INTO EMPLEADOS (num, nombre, departamento, edad, sueldo) VALUES (5, 'Arturo', 10, 32, 1000.0)";
            st.executeUpdate(sql);

            sql = "INSERT INTO EMPLEADOS (num, nombre, departamento, edad, sueldo) VALUES (6, 'Juan', 20, 28, 1200.0)";
            st.executeUpdate(sql);

            sql = "INSERT INTO EMPLEADOS (num, nombre, departamento, edad, sueldo) VALUES (7, 'Martín', 10, 26, 1400.0)";
            st.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (st != null && !st.isClosed()) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("No se ha podido cerrar el Statement por alguna razón");
            }

            try {
                if (cn != null && !cn.isClosed()) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("No se ha podido cerrar la conexión por alguna razón");
            }
        }
    }
}

