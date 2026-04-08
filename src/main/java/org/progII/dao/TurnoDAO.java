package org.progII.dao;

import org.progII.entities.Turno;
import org.progII.interfaces.AdmConection;
import org.progII.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO implements AdmConection, DAO<Turno, Integer> {

  private static final String sql_insert = "INSERT INTO consultorio_db.turno (dia, hora, nro_consultorio, nro_paciente) VALUES (?, ?, ?, ?)";
  private static final String sql_update = "UPDATE consultorio_db.turno SET dia = ?, hora = ?, nro_consultorio = ?, nro_paciente = ? WHERE id = ?";
  private static final String sql_delete = "DELETE FROM consultorio_db.turno WHERE id = ?";
  private static final String sql_getbyid = "SELECT * FROM consultorio_db.turno WHERE id = ?";
  private static final String sql_getall = "SELECT * FROM consultorio_db.turno ORDER BY dia, hora";

  @Override
  public List<Turno> getAll() {
    List<Turno> turnos = new ArrayList<>();

    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(sql_getall);
         ResultSet rs = pst.executeQuery()) {
      while (rs.next()) {
        Turno turno = new Turno();
        turno.setId(rs.getInt("id"));
        turno.setDia(rs.getDate("dia"));
        turno.setHora(rs.getTime("hora"));
        turno.setNroConsultorio(rs.getInt("nro_consultorio"));
        turno.setNroPaciente(rs.getInt("nro_paciente"));
        turnos.add(turno);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return turnos;
  }

  @Override
  public void insert(Turno objeto) {
    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS)) {

      pst.setDate(1, new java.sql.Date(objeto.getDia().getTime()));
      pst.setTime(2, objeto.getHora());
      pst.setInt(3, objeto.getNroConsultorio());
      pst.setInt(4, objeto.getNroPaciente());
      pst.executeUpdate();

      try (ResultSet rs = pst.getGeneratedKeys()) {
        if (rs.next()) {
          objeto.setId(rs.getInt(1));
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Turno objeto) {
    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(sql_update)) {
      pst.setDate(1, new java.sql.Date(objeto.getDia().getTime()));
      pst.setTime(2, objeto.getHora());
      pst.setInt(3, objeto.getNroConsultorio());
      pst.setInt(4, objeto.getNroPaciente());
      pst.setInt(5, objeto.getId());

      int filasAfectadas = pst.executeUpdate();

      if (filasAfectadas == 0) {
        System.err.println("Atención: No se encontró ningún turno con ID " + objeto.getId() + " para actualizar.");
      }

    } catch (SQLException e) {
      System.err.println("Error al actualizar el turno ID " + objeto.getId() + ": " + e.getMessage());
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
  public Turno getById(Integer id) {
    Turno turno = null;
    try (Connection conn = obtenerConexion();
         PreparedStatement pst = conn.prepareStatement(sql_getbyid)) {
      pst.setInt(1, id);
      try (ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
          turno = new Turno();
          turno.setId(rs.getInt("id"));
          turno.setDia(rs.getDate("dia"));
          turno.setHora(rs.getTime("hora"));
          turno.setNroConsultorio(rs.getInt("nro_consultorio"));
          turno.setNroPaciente(rs.getInt("nro_paciente"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return turno;
  }

  @Override
  public boolean existsById(Integer id) {
    return getById(id) != null;
  }
}
