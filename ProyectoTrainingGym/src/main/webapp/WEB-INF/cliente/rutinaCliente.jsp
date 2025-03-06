
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.uma.proyectotaw.entity.RutinaSesionentrenamientoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.DayOfWeek" %>
<%@ page import="es.uma.proyectotaw.dto.RutinaAsignadaDTO" %>
<%@ page import="es.uma.proyectotaw.dto.ClienteDTO" %>
<%@ page import="es.uma.proyectotaw.dto.RutinaSesionentrenamientoDTO" %>

<%
    RutinaAsignadaDTO rutinaasignada = (RutinaAsignadaDTO) request.getAttribute("rutinaAsignada");
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");

    List<RutinaSesionentrenamientoDTO> sesiones = (List<RutinaSesionentrenamientoDTO>) request.getAttribute("sesiones");

    LocalDate fecha = LocalDate.now();
    LocalDate lunes = fecha.with(DayOfWeek.MONDAY);
    String fechaLunes = lunes.toString();
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/rutinaCliente.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
<nav>
    <div class="logo"><img src="/img/logoGym.png" alt="TrainingGym Logo"></div>
    <ul class="enlaces">
        <li><a href="/clienteMain/inicio" >Inicio</a></li>
        <li><a href="/clienteMain/perfil">Perfil</a></li>
        <li><a href="/clienteMain/rutina?fecha=<%=fechaLunes%>" id="activo">Rutina</a></li>
        <li><a href="/clienteMain/desarrollo?fecha=<%=fechaLunes%>">Desarrollo</a></li>
        <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
    </ul>
</nav>
<%
if(rutinaasignada != null){

%>
<h1><%=rutinaasignada.getRutinaPredefinida().getNombre()%></h1>
<div class="imagen-fondo">
    <div class="capa-gris"></div>
    <div class="sesiones">
        <%
            for(RutinaSesionentrenamientoDTO sesion : sesiones){
        %>
        <div class="session">
            <h2 ><a class="session-link" href="/clienteMain/rutina/sesion?rutinaId=<%=rutinaasignada.getId()%>&id=<%=sesion.getSesionentrenamiento().getId()%>"><%=sesion.getSesionentrenamiento().getNombre()%></a></h2>


        </div>
        <%
            };
        %>
    </div>
</div>
<%
    } else{
%>
<h1>Aún no se le ha asignado ninguna rutina</h1>
<div class="imagen-fondo">
    <div class="capa-gris"></div>
</div>
<%
    }
%>

</body>
</html>
