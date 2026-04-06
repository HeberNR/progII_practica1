package org.progII.dao;

import org.progII.interfaces.AdmConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Time;

public class TurnoDAO implements AdmConection {

  public void agregarTurno(Date dia, Time hora, int nroConsultorio, int nroPaciente) {

    String sql = "INSERT INTO turno (dia, hora, nro_consultorio, nro_paciente) VALUES (?, ?, ?, ?)";

    try (Connection conn = obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setDate(1, dia);
      ps.setTime(2, hora);
      ps.setInt(3, nroConsultorio);
      ps.setInt(4, nroPaciente);

      ps.executeUpdate();

      System.out.println("Turno agregado correctamente");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
