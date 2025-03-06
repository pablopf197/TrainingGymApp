
<%@ page import="es.uma.proyectotaw.entity.SesionentrenamientoEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="es.uma.proyectotaw.dto.RutinaPredefinidaDTO" %>
<%@ page import="es.uma.proyectotaw.dto.SesionentrenamientoDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    RutinaPredefinidaDTO rutina = (RutinaPredefinidaDTO) request.getAttribute("rutina");
    List<SesionentrenamientoDTO> listaSesiones = (List<SesionentrenamientoDTO>) request.getAttribute("listaSesiones");
%>
<html>
<head>
    <title>Training Gym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/verRutinaEntrenador.css">
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
    <div class="capa-gris"></div>

    <div id="modalAgregarSesion" class="modal">
        <div class="modal-content">
            <!-- Lista de Sesiones -->
            <h2>Lista de Sesiones de Entrenamiento</h2>
            <div class="lista-sesiones-modal">
                <% for (SesionentrenamientoDTO sesion : listaSesiones) { %>
                <div class="sesion-modal">
                    <p><%= sesion.getNombre() %></p>
                    <button onclick="addSesion(<%= sesion.getId() %>, '<%= sesion.getNombre() %>')">Añadir</button>
                </div>
                <% } %>
            </div>
        </div>
    </div>
    <div class="contenedor-rutina-titulo">
        <h1>Rutina Semanal</h1>
        <div class="contenido">
            <form method="post" action="/entrenadorMain/rutinas/guardar">
                <label id="label-nombre">
                    Nombre:
                    <input type="text" name="nombre" class="nombre">
                </label>
                <label id="label-objetivos">
                    Objetivos:
                    <textarea name="objetivos"></textarea>
                </label>
                <p id="btn-add-sesion" onclick="mostrarModal()">Añadir Sesión</p>
                <div id="div-sesiones">
                    <p>Sesiones de Entrenamiento:</p>

                    <div id="lista-sesiones"></div>
                </div>
                <input type="hidden" name="id" value="<%=rutina.getId()%>">
                <input type="submit" value="Guardar">
            </form>
        </div>
    </div>
</div>
<script src="/scripts/verRutinaEntrenador.js"></script>
</body>
</html>
