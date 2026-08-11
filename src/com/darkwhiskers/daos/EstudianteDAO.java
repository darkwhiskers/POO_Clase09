/**
 * @author  Mamba & Mike
 * Visit: https://darkwhiskers.org/
 * Repository: https://github.com/darkwhiskers
 * @since ©2026
 */
package com.darkwhiskers.daos;

import com.darkwhiskers.connection.ConexionDB;
import com.darkwhiskers.models.Estudiante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO implements GenericDAO<Estudiante, Integer> {

    @Override
    public void create(Estudiante e) {
        String sql = "INSERT INTO estudiante (dni, nombre, apellido, fechaNacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, e.getDni());
            stmt.setString(2, e.getNombre());
            stmt.setString(3, e.getApellido());
            stmt.setDate(4, Date.valueOf(e.getFecNac()));
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    @Override
    public Estudiante findById(Integer id) {
        Estudiante estudiante = null;
        String sql = "SELECT * FROM estudiante WHERE legajo = ?";
        try (Connection conn = ConexionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                estudiante = new Estudiante(
                        rs.getInt("legajo"),
                        rs.getInt("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("fechaNacimiento").toLocalDate()
                );
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return estudiante;
    }

    public Estudiante findByDni(int dni) {
        Estudiante estudiante = null;
        String sql = "SELECT * FROM estudiante WHERE dni = ?";

        try (Connection conn = ConexionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Setear parámetro
            stmt.setInt(1, dni);

            // Ejecutar consulta
            ResultSet rs = stmt.executeQuery();

            // Procesar resultado
            if (rs.next()) {
                estudiante = new Estudiante(
                        rs.getInt("legajo"),
                        rs.getInt("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("fechaNacimiento").toLocalDate()
                );
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return estudiante;
    }

    @Override
    public List<Estudiante> findAll() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";
        try (Connection conn = ConexionDB.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estudiante e = new Estudiante(rs.getInt("legajo"), rs.getInt("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getDate("fechaNacimiento").toLocalDate());
                lista.add(e);
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public void update(Estudiante e) {
        String sql = "UPDATE estudiante SET dni=?, nombre=?, apellido=?, fechaNacimiento=? WHERE legajo=?";
        try (Connection conn = ConexionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, e.getDni());
            stmt.setString(2, e.getNombre());
            stmt.setString(3, e.getApellido());
            stmt.setDate(4, Date.valueOf(e.getFecNac()));
            stmt.setInt(5, e.getLegajo());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Integer legajo) {
        String sql = "DELETE FROM estudiante WHERE legajo=?";
        try (Connection conn = ConexionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, legajo);
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}
