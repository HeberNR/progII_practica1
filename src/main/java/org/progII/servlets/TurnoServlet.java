package org.progII.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.progII.dao.TurnoDAO;
import org.progII.entities.Turno;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/TurnoServlet")
public class TurnoServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().println("¡El Servlet está funcionando!");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String accion = request.getParameter("accion");
    TurnoDAO dao = new TurnoDAO();

    try {
      if ("cancelarPintura".equals(accion)) {
        int nroConsultorio = Integer.parseInt(request.getParameter("nroConsultorio"));
        String fechaStr = request.getParameter("fecha");

        dao.deletePorPintura(nroConsultorio, fechaStr);

      } else {
        String fechaStr = request.getParameter("fecha");
        String horaStr = request.getParameter("hora");

        if (horaStr.length() == 5) {
          horaStr += ":00";
        }

        int nroConsultorio = Integer.parseInt(request.getParameter("nroConsultorio"));
        int nroPaciente = Integer.parseInt(request.getParameter("nroPaciente"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = sdf.parse(fechaStr);
        Time hora = Time.valueOf(horaStr);

        Turno nuevo = new Turno(fecha, hora, nroConsultorio, nroPaciente);
        dao.insert(nuevo);
      }

      response.sendRedirect("listaTurnos.jsp");

    } catch (Exception e) {
      e.printStackTrace();
      response.sendRedirect("listaTurnos.jsp?error=1");
    }
  }
}