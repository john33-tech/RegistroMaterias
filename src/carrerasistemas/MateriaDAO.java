
package carrerasistemas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MateriaDAO {
    
    public void insertarMateria(String nombreMateria, int dificultad) {
        Conexion conexion = new Conexion();
        Connection con = conexion.conectar();

        String sql = "INSERT INTO materias (nombre, dificultad) VALUES (?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreMateria);
            ps.setInt(2, dificultad);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Materia guardada correctamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        } finally {
            conexion.desconectar(con);
        }
    }

    public DefaultTableModel cargarMaterias() {
        Conexion conexion = new Conexion();
        Connection con = conexion.conectar();
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Dificultad"}, 0);

        String sql = "SELECT * FROM materias";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getString("nombre"), rs.getInt("dificultad")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar materias: " + e.getMessage());
        } finally {
            conexion.desconectar(con);
        }

        return modelo;
    }

    public int obtenerDificultad(String nombreMateria) {
        Conexion conexion = new Conexion();
        Connection con = conexion.conectar();
        String sql = "SELECT dificultad FROM materias WHERE nombre = ?";
        int dificultad = -1;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreMateria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dificultad = rs.getInt("dificultad");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener dificultad: " + e.getMessage());
        } finally {
            conexion.desconectar(con);
        }

        return dificultad;
    }
    
    public void eliminarMateria(int id) {
    Conexion conexion = new Conexion();
    Connection con = conexion.conectar();

    String sql = "DELETE FROM materias WHERE id = ?";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Materia eliminada correctamente.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
    } finally {
        conexion.desconectar(con);
    }
    }
}
