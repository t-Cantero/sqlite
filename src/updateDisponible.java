import java.sql.*;
import java.util.Scanner;

public class updateDisponible {

    public static void modificarDisponibilidad() {
        Scanner scanner = new Scanner(System.in);
        obtenerInfoLibro.listarLibros();

        System.out.print("Ingrese el código del libro: ");
        int cod_lib = scanner.nextInt();

        int nuevaDisponibilidad;
        do {
            System.out.print("Nueva disponibilidad (0 = No Disponible / 1 = Disponible): ");
            nuevaDisponibilidad = scanner.nextInt();
            if (nuevaDisponibilidad != 0 && nuevaDisponibilidad != 1) {
                System.out.println("Valor inválido. Debe ser 0 o 1.");
            }
        } while (nuevaDisponibilidad != 0 && nuevaDisponibilidad != 1);

        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = DatabaseConnection.getConnection();
            String query = "UPDATE libros SET disponible = ? WHERE cod_lib = ?";
            ps = cn.prepareStatement(query);
            ps.setInt(1, nuevaDisponibilidad);
            ps.setInt(2, cod_lib);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Disponibilidad actualizada correctamente.");
            } else {
                System.out.println("No se encontró un libro con ese código.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la disponibilidad. Mensaje: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null && !cn.isClosed()) cn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}