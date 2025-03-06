
<%@ page import="es.uma.proyectotaw.entity.SesionentrenamientoEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.SesionentrenamientoHasSesionejercicioEntity" %>
<%@ page import="es.uma.proyectotaw.entity.EjercicioEntity" %>
<%@ page import="es.uma.proyectotaw.dto.EjercicioDTO" %>
<%@ page import="es.uma.proyectotaw.dto.SesionentrenamientoDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<EjercicioDTO> listaEjercicios = (List<EjercicioDTO>) request.getAttribute("listaEjercicios");
    SesionentrenamientoDTO sesion = (SesionentrenamientoDTO) request.getAttribute("sesion");
%>
<html>
<head>
    <title>Training Gym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/verSesionEntrenador.css">
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
        <div class="capa-gris"></div>

        <div id="modalAgregarEjercicio" class="modal">
            <div class="modal-content">
                <!-- Lista de ejercicios -->
                <h2>Lista de Ejercicios</h2>
                <div class="lista-ejercicios-modal">
                    <% for (EjercicioDTO ejercicio : listaEjercicios) { %>
                    <div class="ejercicio-modal">
                        <p><%= ejercicio.getNombre() %></p>
                        <button onclick="addEjercicio(<%= ejercicio.getId() %>, '<%= ejercicio.getNombre() %>')">Añadir</button>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
        <div class="contenedor-sesion-titulo">
            <h1>Crea una Nueva Sesión de Entrenamiento</h1>
            <div class="contenido">
                <form method="post" action="/entrenadorMain/sesiones/guardar">
                    <label id="label-nombre">
                        Nombre:
                        <input type="text" name="nombre" class="nombre">
                    </label>
                    <label id="label-descripcion">
                        Descripción:
                        <textarea maxlength="249" name="descripcion"></textarea>
                    </label>
                    <p id="btn-add-ejercicio" onclick="mostrarModal()">Añadir ejercicio</p>
                    <div id="div-ejercicios">
                        <p>Ejercicios:</p>

                        <div id="lista-ejercicios"></div>
                    </div>
                    <input type="hidden" name="id" value="<%=sesion.getId()%>">
                    <input type="submit" value="Guardar">
                </form>
            </div>
        </div>
    </div>
    <script src="/scripts/verSesionEntrenador.js"></script>
</body>
</html>
