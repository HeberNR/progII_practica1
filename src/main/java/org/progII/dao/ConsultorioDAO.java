package org.progII.dao;

import org.progII.entities.Consultorio;
import org.progII.entities.Turno;
import org.progII.interfaces.AdmConection;
import org.progII.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultorioDAO implements AdmConection, DAO<Consultorio, Integer> {

  private static final String sql_insert = "INSERT INTO consultorio_db.consultorio (nro_consultorio, medico) VALUES (?, ?)";
  private static final String sql_update = "UPDATE consultorio_db.consultorio SET medico = ? WHERE nro_consultorio = ?";
  private static final String sql_delete = "DELETE FROM consultorio_db.consultorio WHERE nro_consultorio = ?";
  private static final String sql_getbyid = "SELECT * FROM consultorio_db.consultorio WHERE nro_consultorio = ?";
  private static final String sql_getall = "SELECT * FROM consultorio_db.consultorio ORDER BY medico";

  @Override
  public List<Consultorio> getAll() {
    List<Consultorio> consultorios = new ArrayList<>();
    try (Connection conn = obtenerConexion(); PreparedStatement pst = conn.prepareStatement(sql_getall); ResultSet rs = pst.executeQuery()) {
      while (rs.next()) {
        Consultorio consultorio = new Consultorio();
        consultorio.setNroConsultorio(rs.getInt("nro_consultorio"));
        consultorio.setMedico(rs.getString("medico"));
        consultorios.add(consultorio);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return consultorios;
  }

  @Override
  public void insert(Consultorio objeto) {
    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(sql_insert)) {
      pst.setInt(1, objeto.getNroConsultorio());
      pst.setString(2, objeto.getMedico());
      pst.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Consultorio objeto) {
    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(sql_update)) {
      pst.setString(1, objeto.getMedico());
      pst.setInt(2, objeto.getNroConsultorio());

      int filasAfectadas = pst.executeUpdate();
      if (filasAfectadas == 0) {
        System.err.println("Atención: No se encontró ningún consultorio con número " + objeto.getNroConsultorio() + " para actualizar.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
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
  public Consultorio getById(Integer id) {
    Consultorio consultorio = null;
    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(sql_getbyid)) {
      pst.setInt(1, id);
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          consultorio = new Consultorio();
          consultorio.setNroConsultorio(rs.getInt("nro_consultorio"));
          consultorio.setMedico(rs.getString("medico"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return consultorio;
  }

  @Override
  public boolean existsById(Integer id) {
    return getById(id) != null;
  }
}
