package Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args){
        PreparedStatement st = null;
        Connection con = null;//Ahora la conexión se declara pero no se inicializa aquí

        String sql = "INSERT INTO empleados (num, nombre, departamento, edad, sueldo) VALUES (?, ?, ?, ?, ?)";

        try {
            con = Conexion.DatabaseConnection.getConnection();
            st = con.prepareStatement(sql);

            st.setInt(1,1);
            st.setString(2,"Andreu");
            st.setInt(3,10);
            st.setInt(4,32);
            st.setDouble(5,1000.0);
            st.executeUpdate();

            st.setInt(1,2);
            st.setString(2,"Bernat");
            st.setInt(3,20);
            st.setInt(4,28);
            st.setDouble(5,1200.0);
            st.executeUpdate();

            st.setInt(1,3);
            st.setString(2,"Claudia");
            st.setInt(3,10);
            st.setInt(4,26);
            st.setDouble(5,1400.0);
            st.executeUpdate();

            st.setInt(1,4);
            st.setString(2,"Damián");
            st.setInt(3,10);
            st.setInt(4,40);
            st.setDouble(5,1300.0);
            st.executeUpdate();
        } catch(SQLException ex){
            System.out.println("Error "+ ex.getMessage());
        }finally{
            try{
                if(st != null && !st.isClosed()){
                    st.close();
                }
            }catch(SQLException ex){
                System.out.println("No se ha podido cerrar el Statement por alguna razón");
            }
            try {
                if(con != null && !con.isClosed()){
                    con.close();
                }
            }catch (SQLException ex){
                System.out.println("No se ha podido cerrar la conexión por alguna razón");
            }
        }
    }
}
