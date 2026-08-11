/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.daos;

import com.darkwhiskers.connection.ConexionDB;
import com.darkwhiskers.models.Materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO implements GenericDAO<Materia, Integer> {

    @Override
    public void create(Materia materia) {
        String sql = "INSERT INTO materia (nombreMateria) VALUES (?)";
        try (Connection conn = ConexionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, materia.getNombreMateria());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    @Override
    public List<Materia> findAll() {
        List<Materia> lista = new ArrayList<>();
        String sql = "SELECT * FROM materia";
        try (Connection conn = ConexionDB.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Materia m = new Materia(rs.getInt("codigo"), rs.getString("nombreMateria"));
                lista.add(m);
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Materia findById(Integer codigo) {
        Materia materia = null;
        String sql = "SELECT * FROM materia WHERE codigo = ?";
        try (Connection conn = ConexionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                materia = new Materia(
                        rs.getInt("codigo"),
                        rs.getString("nombreMateria")
                );
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return materia;}

    @Override
    public void update(Materia materia) {
        String sql = "UPDATE materia SET nombreMateria=? WHERE codigo=?";
        try (Connection conn = ConexionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, materia.getNombreMateria());
            stmt.setInt(2, materia.getCodigo());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Integer codigo) {
        String sql = "DELETE FROM materia WHERE codigo=?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}
