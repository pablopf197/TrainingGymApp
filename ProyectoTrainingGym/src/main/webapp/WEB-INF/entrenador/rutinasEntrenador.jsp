<%@ page import="es.uma.proyectotaw.entity.RutinaPredefinidaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.RutinaPredefinidaDTO" %>
<%
    List<RutinaPredefinidaDTO> listaRutinas = (List<RutinaPredefinidaDTO>) request.getAttribute("rutinas");
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
    <link rel="stylesheet" href="/styles/rutinasEntrenador.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
<nav>
    <div class="logo"><img src="/img/logoGym.png"></div>
    <ul class="enlaces">
        <li><a href="/entrenadorMain/inicio" >Inicio</a></li>
        <li><a href="/entrenadorMain/rutinas" id="activo">Rutinas</a></li>
        <li><a href="/entrenadorMain/sesiones">Sesiones</a></li>
        <li><a href="/entrenadorMain/clientes">Clientes</a></li>
        <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
    </ul>
</nav>
<div class="imagen-fondo">
    <div class="capa-gris">
        <div class="contenedor-principal">
            <div class="contenedor-crear-rutina">
                <h1>Estas son tus Rutinas Semanales creadas</h1>
                <button class="crear-rutina" onclick="window.location.href='/entrenadorMain/rutinas/crear'">Crear Rutina</button>
                <div class="contenedor-rutinas">
                    <%
                        for (RutinaPredefinidaDTO rutina: listaRutinas) {
                    %>
                    <div class="rutina-item">
                        <p class="rutina-nombre"><%= rutina.getNombre()%></p>
                        <button class="ver-rutina" onclick="window.location.href='/entrenadorMain/rutinas/ver?id=<%= rutina.getId() %>'">Ver Rutina</button>
                        <button class="borrar-rutina" onclick="window.location.href='/entrenadorMain/rutinas/borrar?id=<%= rutina.getId() %>'">Borrar Rutina</button>
                    </div>
                    <%}%>
                </div>
            </div>
            <div class="filtro">
                <form class="formulario" action="/entrenadorMain/rutinas/filtrar" method="post">
                    <p><strong>Escribe nombre u objetivos de la rutina</strong></p>
                    <input class="entrada" name="filtro" value="<%=filtro%>"> </br>
                    <input class="filtrar crear-rutina" type="submit" value="Filtrar">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
