<%@ page import="org.progII.dao.TurnoDAO" %>
<%@ page import="org.progII.entities.Turno" %>
<%@ page import="org.progII.dao.ConsultorioDAO" %>
<%@ page import="org.progII.entities.Consultorio" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestión de Turnos</title>
    <style>
        body { font-family: sans-serif; background-color: #121212; color: white; padding: 40px; }
        .container { max-width: 900px; margin: auto; }

        table { width: 100%; border-collapse: collapse; background: #1e1e1e; margin-bottom: 30px; border-radius: 8px; overflow: hidden; }
        th, td { border: 1px solid #333; padding: 15px; text-align: left; }
        th { background-color: #007bff; color: white; text-transform: uppercase; font-size: 0.8em; }
        tr:hover { background-color: #252525; }

        .back-link { color: #007bff; text-decoration: none; margin-bottom: 20px; display: inline-block; font-weight: bold; }

        .pintura-box {
            background: #231111;
            padding: 25px;
            border: 1px solid #dc3545;
            border-radius: 8px;
            margin-top: 40px;
        }

        .pintura-box h3 { color: #dc3545; margin-top: 0; }

        .grid-form { display: grid; grid-template-columns: 1fr 1fr auto; gap: 15px; align-items: end; }

        select, input {
            padding: 10px;
            border-radius: 4px;
            background: #2a2a2a;
            color: white;
            border: 1px solid #444;
            width: 100%;
        }

        .btn-danger {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 11px 20px;
            cursor: pointer;
            font-weight: bold;
            border-radius: 4px;
        }

        .btn-danger:hover { background-color: #a71d2a; }

        label { display: block; margin-bottom: 5px; font-size: 0.9em; color: #aaa; }
    </style>
</head>
<body>
<div class="container">
    <a href="index.jsp" class="back-link">← Volver al registro</a>

    <h1>Turnos Agendados</h1>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Fecha</th>
            <th>Hora</th>
            <th>Consultorio</th>
            <th>ID Paciente</th>
        </tr>
        </thead>
        <tbody>
        <%
            TurnoDAO tDAO = new TurnoDAO();
            List<Turno> turnos = tDAO.getAll();
            if (turnos.isEmpty()) {
        %>
        <tr><td colspan="5" style="text-align: center;">No hay turnos registrados.</td></tr>
        <%
        } else {
            for(Turno t : turnos) {
        %>
        <tr>
            <td><%= t.getId() %></td>
            <td><%= t.getDia() %></td>
            <td><%= t.getHora() %></td>
            <td>Cons. <%= t.getNroConsultorio() %></td>
            <td><%= t.getNroPaciente() %></td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

    <div class="pintura-box">
        <h3>Mantenimiento: Cancelar por Pintura</h3>
        <p>Utilice esta opción para liberar la agenda de un consultorio que deba ser pintado.</p>

        <form action="./TurnoServlet" method="post" class="grid-form">
            <input type="hidden" name="accion" value="cancelarPintura">

            <div>
                <label>Fecha de pintura:</label>
                <input type="date" name="fecha" required>
            </div>

            <div>
                <label>Seleccionar Consultorio:</label>
                <select name="nroConsultorio" required>
                    <%
                        ConsultorioDAO cDAO = new ConsultorioDAO();
                        List<Consultorio> listaC = cDAO.getAll();
                        for(Consultorio c : listaC) {
                    %>
                    <option value="<%= c.getNroConsultorio() %>">
                        Cons. <%= c.getNroConsultorio() %> (<%= c.getMedico() %>)
                    </option>
                    <% } %>
                </select>
            </div>

            <button type="submit" class="btn-danger">EJECUTAR CANCELACIÓN</button>
        </form>
    </div>
</div>
</body>
</html>