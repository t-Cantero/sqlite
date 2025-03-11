package Conexion;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    static java.sql.Connection con = null; //Solo se declara aquí

    public static void main(String[] args){
        Statement st = null;
        String sql = "CREATE TABLE empleados(" +
                " num INTEGER PRIMARY KEY, "+
                " nombre VARCHAR(255), "+
                " departamento INTEGER, "+
                " edad INTEGER, " +
                " sueldo REAL);";

        try{
            con = Conexion.DatabaseConnection.getConnection();
            st = con.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getMessage());
        }finally{
            try{
                if(st != null && !st.isClosed()){
                    st.close();
                }
            }catch (SQLException ex){
                System.out.println("No se ha podido cerrar el Statement por alguna razón");
            }
        }
    }
}
