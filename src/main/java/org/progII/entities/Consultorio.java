package org.progII.entities;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.text.SimpleDateFormat;

public class Consultorio {

  private int nroConsultorio;
  private String medico;
  private List<Turno> turnos;

  public Consultorio() {
    this.turnos = new ArrayList<>();
  }

  public Consultorio(int nroConsultorio, String medico) {
    this();
    this.nroConsultorio = nroConsultorio;
    this.medico = medico;
  }

  public void agregarTurno(Date dia, Time hora, int nroPaciente) {
    Turno nuevoTurno = new Turno(dia, hora, this.nroConsultorio, nroPaciente);
    this.turnos.add(nuevoTurno);
  }

  public void agregarTurno(Turno turno) {
    if (turno != null) {
      this.turnos.add(turno);
    }
  }

  public void cancelarTurnosPorDia(Date fechaPintura) {
    Iterator<Turno> it = turnos.iterator();
    while (it.hasNext()) {
      Turno t = it.next();
      if (esMismoDia(t.getDia(), fechaPintura)) {
        it.remove();
      }
    }
  }

  private boolean esMismoDia(Date dia1, Date dia2) {
    if (dia1 == null || dia2 == null) return false;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    return sdf.format(dia1).equals(sdf.format(dia2));
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
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Consultorio that = (Consultorio) o;
    return nroConsultorio == that.nroConsultorio &&
        Objects.equals(medico, that.medico);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nroConsultorio, medico);
  }

  @Override
  public String toString() {
    return "Consultorio{" +
        "nroConsultorio=" + nroConsultorio +
        ", medico='" + medico + '\'' +
        ", cantidadTurnos=" + turnos.size() +
        '}';
  }
}