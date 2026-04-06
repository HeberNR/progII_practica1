package org.progII.entities;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Turno {
  private int id;
  private Date dia;
  private Time hora;
  private int nroConsultorio;
  private int nroPaciente;

  public Turno() {
  }

  public Turno(Date dia, Time hora, int nroConsultorio, int nroPaciente) {
    this.dia = dia;
    this.hora = hora;
    this.nroConsultorio = nroConsultorio;
    this.nroPaciente = nroPaciente;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDia() {
    return dia;
  }

  public void setDia(Date dia) {
    this.dia = dia;
  }

  public Time getHora() {
    return hora;
  }

  public void setHora(Time hora) {
    this.hora = hora;
  }

  public int getNroConsultorio() {
    return nroConsultorio;
  }

  public void setNroConsultorio(int nroConsultorio) {
    this.nroConsultorio = nroConsultorio;
  }

  public int getNroPaciente() {
    return nroPaciente;
  }

  public void setNroPaciente(int nroPaciente) {
    this.nroPaciente = nroPaciente;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Turno turno = (Turno) o;
    return id == turno.id && nroConsultorio == turno.nroConsultorio && nroPaciente == turno.nroPaciente && Objects.equals(dia, turno.dia) && Objects.equals(hora, turno.hora);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dia, hora, nroConsultorio, nroPaciente);
  }

  @Override
  public String toString() {
    return "Turno{" +
        "id=" + id +
        ", dia=" + dia +
        ", hora=" + hora +
        ", nroConsultorio=" + nroConsultorio +
        ", nroPaciente=" + nroPaciente +
        '}';
  }
}
