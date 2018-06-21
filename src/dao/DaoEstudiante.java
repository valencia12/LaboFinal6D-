package dao;
import com.sun.istack.internal.logging.Logger;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Estudiante;
/**
 *
 * @author LN710Q
 */
public class DaoEstudiante {
    private static final String SQL_INSERT= "INSERT INTO alumnos(carnet, nombres, apellidos, edad, universidad, estado) VALUES (?,?,?,?,?,?)";
    private static final String SQL_DELETE= "DELETE FROM alumnos WHERE carnet= ?";
    private static final String SQL_UPDATE= "UPDATE alumnos SET nombres=?, apellidos=?, edad=?, universidad=?, estado=?  WHERE carnet=?";
    private static final String SQL_READ= "SELECT * FROM alumnos WHERE carnet=?";
    private static final String SQL_READALL= "SELECT * FROM alumnos";
    private static final Conexion con= Conexion.conectar();
    
    public boolean create(Estudiante e){
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, e.getCarnet());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setInt(4, e.getEdad());
            ps.setString(5, e.getUniversidad());
            ps.setBoolean(6, e.isEstado());
            if(ps.executeUpdate()>0){
                return true;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Error");
        }finally{
            con.cerrarConexion();
        }
        return false;
    }
    public boolean delete(Object carnet){
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, carnet.toString());
            if(ps.executeUpdate()>0){
                return true;
            }          
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Error");
        }finally{
            con.cerrarConexion();
        }
        return false;
    }
    public boolean update(Estudiante e){
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setInt(3, e.getEdad());
            ps.setString(4, e.getUniversidad());
            ps.setBoolean(5, e.isEstado());
            ps.setString(6, e.getCarnet());
            if(ps.executeUpdate()>0){
                return true;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Error");
        }finally{
            con.cerrarConexion();
        }
        return false;
    }
    public Estudiante read(Object carnet){
        Estudiante e=null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps=con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, carnet.toString());
            rs=ps.executeQuery();
            while(rs.next()){
                e= new Estudiante(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getBoolean(7));
            }
            rs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Error");
        }finally{
            con.cerrarConexion();
        }
        return e;
    }
    public ArrayList<Estudiante> readAll(){
        ArrayList<Estudiante> all= new ArrayList<>();
        Statement s;
        ResultSet rs;
        try{
            s=con.getCnx().prepareStatement(SQL_READALL);
            rs= s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Estudiante(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5) , rs.getString(6), rs.getBoolean(7)));
            }
            rs.close();
        }catch(Exception ex){
            System.out.println("No servis");
        }finally{
            con.cerrarConexion();
        } 
        return all;
    }
}