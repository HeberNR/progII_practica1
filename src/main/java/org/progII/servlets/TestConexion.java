package org.progII.servlets;

import org.progII.interfaces.AdmConection;
import java.sql.Connection;

public class TestConexion implements AdmConection {

  public static void main(String[] args) {

    TestConexion test = new TestConexion();

    Connection conn = test.obtenerConexion();

    if (conn != null) {
      System.out.println("Todo OK");
    } else {
      System.out.println("Algo fallo");
    }
  }
}