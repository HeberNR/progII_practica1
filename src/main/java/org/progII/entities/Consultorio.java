package org.progII.entities;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Consultorio {

  private int nroConsultorio;
  private String medico;
  private List<Turno> turnos;

  public Consultorio(int nroConsultorio, String medico) {
    this.nroConsultorio = nroConsultorio;
    this.medico = medico;
    this.turnos = new ArrayList<>();
  }

  public void agregarTurno(Date dia, Time hora, int nroPaciente) {
    Turno nuevoTurno = new Turno(dia, hora, this.nroConsultorio, nroPaciente);
    this.turnos.add(nuevoTurno);
  }

  public void agregarTurno(Turno turno) {
    turnos.add(turno);
  }

  public int getNroConsultorio() {
    return nroConsultorio;
  }

  public void setNroConsultorio(int nroConsultorio) {
    this.nroConsultorio = nroConsultorio;
  }

  public String getMedico() {
    return medico;
  }

  public void setMedico(String medico) {
    this.medico = medico;
  }

  public List<Turno> getTurnos() {
    return turnos;
  }

  public void setTurnos(List<Turno> turnos) {
    this.turnos = turnos;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Consultorio that = (Consultorio) o;
    return nroConsultorio == that.nroConsultorio && Objects.equals(medico, that.medico) && Objects.equals(turnos, that.turnos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nroConsultorio, medico, turnos);
  }

  @Override
  public String toString() {
    return "Consultorio{" +
        "nroConsultorio=" + nroConsultorio +
        ", medico='" + medico + '\'' +
        ", turnos=" + turnos +
        '}';
  }
}
