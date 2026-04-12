<%@ page import="org.progII.dao.PacienteDAO" %>
<%@ page import="org.progII.dao.ConsultorioDAO" %>
<%@ page import="org.progII.entities.Paciente" %>
<%@ page import="org.progII.entities.Consultorio" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro de Turnos</title>
    <style>
        body { font-family: sans-serif; background-color: #121212; color: white; padding: 20px; }
        form { background: #1e1e1e; padding: 20px; border-radius: 8px; max-width: 450px; margin: auto; }
        select, input { width: 100%; padding: 10px; margin: 10px 0; border-radius: 4px; background: #2a2a2a; color: white; border: 1px solid #444; }
        button { background-color: #007bff; color: white; border: none; padding: 12px; cursor: pointer; width: 100%; font-weight: bold; }
        label { color: #007bff; font-weight: bold; }
        .link { text-align: center; display: block; margin-top: 15px; color: #aaa; text-decoration: none; }
    </style>
</head>
<body>

<form action="./TurnoServlet" method="post">
    <input type="hidden" name="accion" value="guardar">

    <h2 style="text-align: center;">Nuevo Turno</h2>

    <label>Fecha:</label>
    <input type="date" name="fecha" required>

    <label>Hora:</label>
    <input type="time" name="hora" required>

    <label>Seleccionar Consultorio (Médico):</label>
    <select name="nroConsultorio" required>
        <%
            ConsultorioDAO cDAO = new ConsultorioDAO();
            List<Consultorio> consultorios = cDAO.getAll();
            for (Consultorio c : consultorios) {
        %>
        <option value="<%= c.getNroConsultorio() %>">
            Cons. <%= c.getNroConsultorio() %> - <%= c.getMedico() %>
        </option>
        <% } %>
    </select>

    <label>Seleccionar Paciente:</label>
    <select name="nroPaciente" required>
        <%
            PacienteDAO pDAO = new PacienteDAO();
            List<Paciente> pacientes = pDAO.getAll();
            for (Paciente p : pacientes) {
        %>
        <option value="<%= p.getNroPaciente() %>">
            <%= p.getNombre() %>
        </option>
        <% } %>
    </select>

    <button type="submit">Confirmar Turno</button>
    <a href="listaTurnos.jsp" class="link">Ver lista de turnos</a>
</form>
</body>
</html>