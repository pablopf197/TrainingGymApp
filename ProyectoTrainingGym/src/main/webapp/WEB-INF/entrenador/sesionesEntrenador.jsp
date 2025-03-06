<%@ page import="es.uma.proyectotaw.entity.SesionentrenamientoEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.SesionentrenamientoDTO" %>
<%
    List<SesionentrenamientoDTO> listaSesiones = (List<SesionentrenamientoDTO>) request.getAttribute("sesiones");
    String filtro = (String) request.getAttribute("filtro");
    if(filtro == null ){
        filtro = "";
    };
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/sesionesEntrenador.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
<nav>
    <div class="logo"><img src="/img/logoGym.png"></div>
    <ul class="enlaces">
        <li><a href="/entrenadorMain/inicio" >Inicio</a></li>
        <li><a href="/entrenadorMain/rutinas">Rutinas</a></li>
        <li><a href="/entrenadorMain/sesiones" id="activo">Sesiones</a></li>
        <li><a href="/entrenadorMain/clientes">Clientes</a></li>
        <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
    </ul>
</nav>
<div class="imagen-fondo">
    <div class="capa-gris">
        <div class="contenedor-principal">
            <div class="contenedor-crear-sesion">
                <h1>Estas son tus Sesiones de Entrenamiento creadas</h1>
                <button class="crear-sesion" onclick="window.location.href='/entrenadorMain/sesiones/crear'">Crear Sesión</button>
                <div class="contenedor-sesion">
                    <% for (SesionentrenamientoDTO sesion: listaSesiones) { %>
                    <div class="sesion-item">
                        <p class="sesion-nombre"><%= sesion.getNombre()%></p>
                        <button class="ver-sesion" onclick="window.location.href='/entrenadorMain/sesiones/ver?id=<%= sesion.getId() %>'">Ver Sesión</button>
                        <button class="borrar-sesion" onclick="window.location.href='/entrenadorMain/sesiones/borrar?id=<%= sesion.getId() %>'">Borrar Sesión</button>
                    </div>
                    <% } %>
                </div>
            </div>
            <div class="filtro">
                <form class="formulario" action="/entrenadorMain/sesiones/filtrar" method="post" style="display: flex; flex-direction: column;">
                    <p><strong>Escribe nombre de la sesión</strong></p>
                    <input class="entrada" name="filtro" value="<%=filtro%>"> </br>
                    <input class="filtrar crear-rutina" type="submit" value="Filtrar">
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>

