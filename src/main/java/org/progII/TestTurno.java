package org.progII;

import org.progII.dao.TurnoDAO;

import java.sql.Date;
import java.sql.Time;

public class TestTurno {

  public static void main(String[] args) {

    TurnoDAO dao = new TurnoDAO();

    dao.agregarTurno(
        Date.valueOf("2026-04-15"),
        Time.valueOf("10:00:00"),
        1,
        1
    );
  }
}