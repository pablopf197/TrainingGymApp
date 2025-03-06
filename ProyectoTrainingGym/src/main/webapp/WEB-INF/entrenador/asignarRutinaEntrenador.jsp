<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="es.uma.proyectotaw.dto.UsuarioDTO" %>
<%@ page import="es.uma.proyectotaw.dto.RutinaAsignadaDTO" %>
<%@ page import="es.uma.proyectotaw.dto.RutinaPredefinidaDTO" %>
<%@ page import="es.uma.proyectotaw.dto.RutinaSesionentrenamientoDTO" %>
<%
    UsuarioDTO cliente = (UsuarioDTO) request.getAttribute("cliente");
    RutinaAsignadaDTO rutinaAsignada = (RutinaAsignadaDTO) request.getAttribute("rutinaAsignada");
    LocalDate semana = (LocalDate) request.getAttribute("semana");
    String semanaString = semana.toString();
    List<RutinaPredefinidaDTO> rutinasEntrenador = (List<RutinaPredefinidaDTO>) request.getAttribute("rutinasEntrenador");
    LocalDate semanaAnterior = semana.minusDays(7);
    String semanaAnteriorString = semanaAnterior.toString();
    LocalDate semanaSiguiente = semana.plusDays(7);
    String semanaSiguienteString = semanaSiguiente.toString();
    List<RutinaSesionentrenamientoDTO> rutinasSesiones = (List<RutinaSesionentrenamientoDTO>) request.getAttribute("rutinasSesiones");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/asignarRutinaEntrenador.css">
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
        <li><a href="/entrenadorMain/sesiones">Sesiones</a></li>
        <li><a href="/entrenadorMain/clientes" id="activo">Clientes</a></li>
        <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
    </ul>
</nav>
<div class="imagen-fondo">
    <div class="capa-gris"></div>
    <div id="modalAsignarRutina" class="modal">
        <div class="modal-content">
            <h2>Lista de Rutinas Semanales</h2>
            <div class="lista-rutinas-modal">
                <% for (RutinaPredefinidaDTO rutina : rutinasEntrenador) { %>
                <div class="rutina-modal">
                    <p><%= rutina.getNombre() %></p>
                    <form action="/entrenadorMain/clientes/entrenamiento/asignarRutina" method="post">
                        <input type="hidden" name="rutinaId" value="<%= rutina.getId() %>">
                        <input type="hidden" name="usuarioId" value="<%= cliente.getId() %>">
                        <input type="hidden" name="fecha" value="<%= semana %>">
                        <button type="submit">Asignar</button>
                    </form>
                </div>
                <% } %>
            </div>
        </div>
    </div>
    <div class="contenedor-rutina-titulo">
        <h1><%=cliente.getNombre()%> <%=cliente.getApellidos()%></h1>
        <div class="contenido">
            <div class="fecha">
                <img src="/img/flecha-izquierda.png" alt="flecha izquierda" class="icono" onclick="window.location.href='/entrenadorMain/clientes/entrenamiento?id=<%=cliente.getId()%>&fecha=<%=semanaAnteriorString%>'">
                <p>Sem. <%=semana.getDayOfMonth()%>/<%=semana.getMonthValue()%></p>
                <img src="/img/flecha-derecha.png" alt="flecha derecha" class="icono" onclick="window.location.href='/entrenadorMain/clientes/entrenamiento?id=<%=cliente.getId()%>&fecha=<%=semanaSiguienteString%>'">
            </div>
            <%
                if(rutinaAsignada != null){
            %>
                    <div class="info-rutina">
                        <p id="label-nombre">
                            <span class="label" > Nombre de la Rutina:</span>
                            <span class="nombre"><%=rutinaAsignada.getRutinaPredefinida().getNombre()%></span>
                        </p>
                        <p id="label-objetivos">
                            Objetivos de la Rutina:
                            <span class="objetivos"><%=rutinaAsignada.getRutinaPredefinida().getObjetivos()%></span>
                        </p>
                        <div id="div-sesiones">
                            <p>Sesiones:</p>

                            <div id="lista-sesiones">
                                <%
                                    Map<Integer, Double> mediasValoraciones = (Map<Integer, Double>) request.getAttribute("mediasValoraciones");
                                    List<Integer> sesionesSinValoracion = (List<Integer>) request.getAttribute("sesionesSinValoracion");
                                    int sesionId;
                                    for (RutinaSesionentrenamientoDTO rutinaHasSesion : rutinasSesiones) {
                                        sesionId = rutinaHasSesion.getSesionentrenamiento().getId();
                                        double mediaValoracion = mediasValoraciones.getOrDefault(sesionId, 0.0);
                                        int estrellasAmarillas = (int) Math.round(mediaValoracion);
                                %>
                                <div class="sesion">
                                    <p class="nombre-sesion"><%=rutinaHasSesion.getSesionentrenamiento().getNombre()%></p>
                                    <button class="btn-ver-sesion" onclick="window.location.href='/entrenadorMain/clientes/entrenamiento/sesion?id=<%=cliente.getId()%>&rutina=<%= rutinaAsignada.getId()%>&sesion=<%= rutinaHasSesion.getSesionentrenamiento().getId()%>'">Ver Valoración</button>
                                    <% if (sesionesSinValoracion.contains(sesionId)) { %>
                                        <p>(No ha sido valorada todavía)</p>
                                    <% } %>
                                    <div class="contenedor-iconos">
                                        <% for (int i = 0; i < estrellasAmarillas; i++) { %>
                                        <img src="/img/estrellaamarilla.png" alt="Estrella Amarilla" class="img-icono">
                                        <% } %>
                                        <% for (int i = estrellasAmarillas; i < 5; i++) { %>
                                        <img src="/img/estrellablanca.png" alt="Estrella Blanca" class="img-icono">
                                        <% } %>
                                    </div>

                                </div>
                                <%
                                    }
                                %>

                            </div>
                    </div>
            <%
                }else{
            %>
                    <div class="sin-rutina-div">
                        <p class="sin-rutina">No tiene ninguna rutina asignada todavía</p>
                        <button id="btn-asignar-rutina" onclick="mostrarModal()">Asignar Rutina</button>
                    </div>
            <%
                }
            %>
        </div>

    </div>
</div>
<script src="/scripts/asignarRutinaEntrenador.js"></script>
</body>
</html>