package org.progII.entities;

import java.util.Objects;

public class Paciente {

  private int nroPaciente;
  private String nombre;
  private int telefono;

  public Paciente() {
  }

  public Paciente(int nroPaciente, String nombre, int telefono) {
    this.nombre = nombre;
    this.telefono = telefono;
  }

  public int getNroPaciente() {
    return nroPaciente;
  }

  public void setNroPaciente(int nroPaciente) {
    this.nroPaciente = nroPaciente;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getTelefono() {
    return telefono;
  }

  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Paciente paciente = (Paciente) o;
    return nroPaciente == paciente.nroPaciente && telefono == paciente.telefono && Objects.equals(nombre, paciente.nombre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nroPaciente, nombre, telefono);
  }

  @Override
  public String toString() {
    return "Paciente{" +
        "nroPaciente=" + nroPaciente +
        ", nombre='" + nombre + '\'' +
        ", telefono=" + telefono +
        '}';
  }
}
