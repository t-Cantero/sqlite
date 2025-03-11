import java.sql.*;
import java.util.Scanner;

public class addLibro {
    public static void agregarLibro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los datos del libro:");
        System.out.println("------------------------------");
        int cod_lib = (obtenerCodLib()+1);
        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();

        System.out.print("Id del Autor: ");
        int autor_id = scanner.nextInt();

        System.out.print("Id de la Categoría: ");
        int categoria_id = scanner.nextInt();

        System.out.print("Año: ");
        int anio = scanner.nextInt();

        int disponible;
        do {
            System.out.println("Disponibilidad (0 = No Disponible / 1 = Disponible): ");
            disponible = scanner.nextInt();
            if (disponible != 0 && disponible != 1) {
                System.out.println("Valor inválido. Debe ser 0 o 1.");
            }
        } while (disponible != 0 && disponible != 1);


        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = DatabaseConnection.getConnection();
            String query = "INSERT INTO libros (cod_lib,ISBN, titulo, autor_id, categoria_id, anio,disponible) VALUES (?, ?, ?, ?, ?, ? ,?)";
            ps = cn.prepareStatement(query);

            ps.setInt(1, cod_lib);
            ps.setString(2, ISBN);
            ps.setString(3, titulo);
            ps.setInt(4, autor_id);
            ps.setInt(5, categoria_id);
            ps.setInt(6, anio);
            ps.setInt(7, disponible);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Libro agregado correctamente.");
            } else {
                System.out.println("No se pudo agregar el libro.");
            }
        } catch (SQLException e) {
            System.out.println("Se ha producido un error al agregar el libro. Mensaje: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null && !cn.isClosed()) cn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

    public static Integer obtenerCodLib() {
        String url = "jdbc:sqlite:libreria.db";
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        Integer codLib = null; // Para almacenar el valor obtenido

        try {
            cn = DriverManager.getConnection(url);
            st = cn.createStatement();
            rs = st.executeQuery("SELECT cod_lib FROM libros ORDER BY cod_lib DESC LIMIT 1");

            if (rs.next()) { // Si hay resultados
                codLib = rs.getInt("cod_lib");
            } else {
                codLib = 1;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }

        return codLib;
    }

}
