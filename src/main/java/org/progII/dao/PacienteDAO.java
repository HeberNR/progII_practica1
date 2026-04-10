package org.progII.dao;

import org.progII.entities.Paciente;
import org.progII.interfaces.AdmConection;
import org.progII.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements AdmConection, DAO<Paciente, Integer> {

    private static final String sql_insert = "INSERT INTO consultorio_db.paciente (telefono, nombre) VALUES (?, ?)";
    private static final String sql_update = "UPDATE consultorio_db.paciente SET telefono = ?, nombre = ? WHERE id = ?";
    private static final String sql_delete = "DELETE FROM consultorio_db.paciente WHERE id = ?";
    private static final String sql_getbyid = "SELECT * FROM consultorio_db.paciente WHERE id = ?";
    private static final String sql_getall = "SELECT * FROM consultorio_db.paciente ORDER BY nombre, telefono";


    @Override
    public List<Paciente> getAll() {
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conn = obtenerConexion(); PreparedStatement pst = conn.prepareStatement(sql_getall); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setNroPaciente(rs.getInt("nro_paciente"));
                paciente.setTelefono(rs.getInt("telefono"));
                paciente.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public void insert(Paciente objeto) {
        try (Connection conn = obtenerConexion(); PreparedStatement pst = conn.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, objeto.getTelefono());
            pst.setString(2, objeto.getNombre());
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    objeto.setNroPaciente(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Paciente objeto) {
        try (Connection conn = obtenerConexion(); PreparedStatement pst = conn.prepareStatement(sql_update)) {
            pst.setInt(1, objeto.getTelefono());
            pst.setString(2, objeto.getNombre());

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas == 0) {
                System.err.println("Atención: No se encontró ningún turno con ID " + objeto.getNroPaciente() + " para actualizar.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el turno ID " + objeto.getNroPaciente() + ": " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection conn = obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql_delete)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paciente getById(Integer id) {
        Paciente paciente = null;
        try (Connection conn = obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(sql_getbyid)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    paciente = new Paciente();
                    paciente.setNroPaciente(rs.getInt("nro_paciente"));
                    paciente.setTelefono(rs.getInt("telefono"));
                    paciente.setNombre(rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    @Override
    public boolean existsById(Integer id) {
        return getById(id) != null;
    }
}
