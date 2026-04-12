package org.progII.dao;

import org.progII.entities.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class TestSistema {

  public static void main(String[] args) {
    PacienteDAO pDAO = new PacienteDAO();
    ConsultorioDAO cDAO = new ConsultorioDAO();
    TurnoDAO tDAO = new TurnoDAO();

    System.out.println("--- INICIANDO TEST DE INTEGRACIÓN ---");

    try {
      // 1. CREAR PACIENTE (Es AI, mandamos 0)
      Paciente p = new Paciente(0, "Heber Programador", 42331122);
      pDAO.insert(p);
      System.out.println("1. Paciente creado con ID: " + p.getNroPaciente());

      // 2. CREAR CONSULTORIO (NO es AI, mandamos un número manual)
      Consultorio c = new Consultorio(5, "Dr. House");
      cDAO.insert(c);
      System.out.println("2. Consultorio nro 5 creado correctamente.");

      // 3. CREAR TURNO (Une a los dos anteriores)
      Turno t = new Turno(new Date(), Time.valueOf("10:00:00"), c.getNroConsultorio(), p.getNroPaciente());
      tDAO.insert(t);
      System.out.println("3. Turno insertado con éxito!");

      // 4. VERIFICACIÓN: Traer todo de la base de datos
      System.out.println("\n--- VERIFICACIÓN EN BASE DE DATOS ---");
      List<Turno> todosLosTurnos = tDAO.getAll();

      if (!todosLosTurnos.isEmpty()) {
        System.out.println("Se encontraron " + todosLosTurnos.size() + " turnos en la tabla.");
        for (Turno aux : todosLosTurnos) {
          System.out.println("ID: " + aux.getId() + " | Consultorio: " + aux.getNroConsultorio() + " | Paciente ID: " + aux.getNroPaciente());
        }
        System.out.println("\n¡TEST EXITOSO! El sistema es consistente.");
      }

    } catch (Exception e) {
      System.err.println("FALLÓ EL TEST:");
      e.printStackTrace();
    }
  }
}