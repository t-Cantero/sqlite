import java.sql.Connection;//Necesario para establecer la conexión
import java.sql.DriverManager;//Necesario para establecer la conexión
import java.sql.ResultSet;
import java.sql.Statement;

public class obtenerInfoLibro {
    public static void listarLibros(){
        String url = "jdbc:sqlite:libreria.db";
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            cn = DriverManager.getConnection(url);//Establecer conexión
            System.out.println("La conexión con la BD se ha establecido correctamente.");

            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM libros");

            System.out.println("Código de Libro     ISBN            Título  ID Autor  ID Categoria  Año   Disponibilidad");
            System.out.println("----------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.print(rs.getInt("cod_lib")+"\t\t\t\t");
                System.out.print(rs.getString("ISBN") + "\t");
                System.out.print(rs.getString("titulo") + "\t\t");
                System.out.print(rs.getInt("autor_id") + "\t\t\t ");
                System.out.print(rs.getInt("categoria_id") + "\t\t ");
                System.out.print(rs.getInt("anio") + "\t");

                // Mostrar "Disponible" o "No Disponible"
                String disponibilidad = rs.getBoolean("disponible") ? "Disponible" : "No Disponible";
                System.out.println(disponibilidad);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            try{
                if(cn != null){
                    cn.close();
                    System.out.println("Conexión cerrada.");
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
