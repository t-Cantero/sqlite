import java.sql.*;
import java.util.Scanner;

public class deleteLibro {

    public static void eliminarLibro() {
        Scanner scanner = new Scanner(System.in);
        obtenerInfoLibro.listarLibros();

        System.out.print("Ingrese el código del libro a eliminar: ");
        int cod_lib = scanner.nextInt();

        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = DatabaseConnection.getConnection();
            String query = "DELETE FROM libros WHERE cod_lib = ?";
            ps = cn.prepareStatement(query);
            ps.setInt(1, cod_lib);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("No se encontró un libro con ese código.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el libro. Mensaje: " + e.getMessage());
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

