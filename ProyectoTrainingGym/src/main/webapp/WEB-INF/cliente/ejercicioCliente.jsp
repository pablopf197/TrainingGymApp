
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.time.DayOfWeek" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="es.uma.proyectotaw.dto.SesionejercicioDTO" %>
<%@ page import="es.uma.proyectotaw.dto.ValoracionDTO" %>
<%@ page import="es.uma.proyectotaw.dto.RutinaAsignadaDTO" %>
<%@ page import="es.uma.proyectotaw.dto.SesionentrenamientoDTO" %>

<%
    SesionejercicioDTO ejercicio = (SesionejercicioDTO) request.getAttribute("ejercicio");
    List<ValoracionDTO> valoraciones  = (List<ValoracionDTO>) request.getAttribute("valoraciones");
    RutinaAsignadaDTO rutinaAsignada = (RutinaAsignadaDTO) request.getAttribute("rutinaAsignada");
    SesionentrenamientoDTO sesionentrenamiento = (SesionentrenamientoDTO) request.getAttribute("sesionEntrenamiento");

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
    <link rel="stylesheet" href="/styles/ejercicioCliente.css">
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

<div class="imagen-fondo">
    <div class="capa-gris"></div>
    <div class="card">
        <h1><%=ejercicio.getEjercicio().getNombre()%></h1>
        <label for="descripcion" class="blanco">Descripción del ejercicio:</label><textarea id="descripcion" class="description" readonly><%=ejercicio.getEjercicio().getDescripcion()%></textarea>
        <div class="input-group">
            <%
            if(ejercicio.getSeries()!=null){

            %>
                <label for="series" class="blanco">Series</label><input type="text" id="series" value="<%=ejercicio.getSeries()%>" readonly>
            <%
            } else{
            %>
                <label for="series" hidden="true" class="blanco">Series</label><input hidden="true" type="text" id="series" value="<%=ejercicio.getSeries()%>" readonly>
            <%
            }
            %>

            <%
                if(ejercicio.getRepeticiones()!=null){

            %>
                <label for="repeticiones" class="blanco">Repeticiones</label><input type="text" id="repeticiones" value="<%=ejercicio.getRepeticiones()%>" readonly>
            <%
            } else{
            %>
                <label for="repeticiones" class="blanco" hidden>Repeticiones</label><input hidden type="text" id="repeticiones" value="<%=ejercicio.getRepeticiones()%>" readonly>
            <%
                }
            %>

            <%
                if(ejercicio.getDuracion()!=null){

            %>
                <label for="duración">Duración</label><input type="text" id="duración" value="<%=ejercicio.getDuracion()%>" readonly>
            <%
            } else{
            %>
                <label for="duración" hidden>Duración</label><input hidden type="text" id="duración" value="<%=ejercicio.getDuracion()%>" readonly>
            <%
                }
            %>

        </div>
        <div class="button-group">
            <button onclick="document.getElementById('videoDialog').showModal()">Video ejercicio</button>
            <%
                Boolean valorado = false;
                for (ValoracionDTO val : valoraciones) {
                    if (val.getSesionejercicio().equals(ejercicio)) {
                        valorado = true;
                        break;
                    }
                }
                if(!valorado){
            %>
            <button onclick="document.getElementById('ratingDialog').showModal()">Completar ejercicio</button>
            <%
                }else{
            %>
            <button onclick="window.location.href='/clienteMain/rutina/sesion?rutinaId=<%=rutinaAsignada.getId()%>&id=<%=sesionentrenamiento.getId()%>'">Ejercicio ya completado, volver atrás</button>
            <%
                }
            %>
        </div>
    </div>
    <dialog id="ratingDialog">
        <h1>Ejercicio número 1</h1>
        <form id="ratingForm" method="post">
            <input hidden name="rutinaId" value="<%=rutinaAsignada.getId()%>">
            <div class="star-rating">
                <span class="star" data-value="1">&#9733;</span>
                <span class="star" data-value="2">&#9733;</span>
                <span class="star" data-value="3">&#9733;</span>
                <span class="star" data-value="4">&#9733;</span>
                <span class="star" data-value="5">&#9733;</span>
            </div>
            <div>
                <label for="comment">Comenta qué te ha parecido el ejercicio y si has podido completarlo:</label>
                <textarea id="comment" class="comment" name="comentario"></textarea>
            </div>
            <input type="hidden" id="sesion" name="sesionId" value="<%=sesionentrenamiento.getId()%>">
            <input type="hidden" id="ejercicio" name="ejercicio" value="<%=ejercicio.getId()%>">
            <input type="hidden" id="rating" name="rating" value="0">
            <input type="submit" class="submit-button">
            <button type="button" class="cancel-button" onclick="document.getElementById('ratingDialog').close()">Cancelar</button>
        </form>
    </dialog>
    <dialog class="video" id="videoDialog">
        <div class="contenedor-principal">
            <div class="video-container">
                <iframe src="<%=ejercicio.getEjercicio().getVideo().replace("watch?v=", "embed/")%>" allowfullscreen></iframe>
            </div>
            <button type="button" class="cancel-button" onclick="document.getElementById('videoDialog').close()">Cerrar</button>
        </div>
    </dialog>
</div>
<script src="/scripts/addValoracion.js"></script>
</body>
</html>
