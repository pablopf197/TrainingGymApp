
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.DayOfWeek" %>
<%@ page import="java.util.Map" %>
<%@ page import="es.uma.proyectotaw.dto.*" %>
<%
    UsuarioDTO cliente = (UsuarioDTO) request.getAttribute("usuario");
    List<ValoracionDTO> valoraciones = (List<ValoracionDTO>) request.getAttribute("valoraciones");
    String puntuacion = request.getParameter("puntuacion");
    if (puntuacion == null) puntuacion = "";

    LocalDate fecha = LocalDate.now();
    LocalDate lunes = fecha.with(DayOfWeek.MONDAY);
    String fechaLunes = lunes.toString();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/valoracionesSesionEntrenador.css">
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
        <li><a href="/clienteMain/rutina?fecha=<%=fechaLunes%>" >Rutina</a></li>
        <li><a href="/clienteMain/desarrollo?fecha=<%=fechaLunes%>" id="activo">Desarrollo</a></li>
        <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
    </ul>
</nav>
<div class="imagen-fondo">
    <div class="capa-gris"></div>
    <div class="contenedor-rutina-titulo">
        <h1><%=cliente.getNombre()%> <%=cliente.getApellidos()%></h1>
        <div class="contenido">
            <button class="btn-ver-valoraciones" onclick="window.location.href='/clienteMain/desarrollo?fecha=<%=fechaLunes%>'">Volver a desarrollo</button>
            <form method="post" action="/clienteMain/desarrollo/sesionesValoradas/filtrar" class="filtro-form">
                <label for="puntuacion">Puntuacion:</label>
                <select name="puntuacion" id="puntuacion" class="filtro-select" onselect="">
                    <option></option>
                    <option value="0" <%=("0".equals(puntuacion) ? "selected" : "")%> >0 estrellas</option>
                    <option value="1" <%=("1".equals(puntuacion) ? "selected" : "")%> >1 estrella</option>
                    <option value="2" <%=("2".equals(puntuacion) ? "selected" : "")%> >2 estrellas</option>
                    <option value="3" <%=("3".equals(puntuacion) ? "selected" : "")%> >3 estrellas</option>
                    <option value="4" <%=("4".equals(puntuacion) ? "selected" : "")%> >4 estrellas</option>
                    <option value="5" <%=("5".equals(puntuacion) ? "selected" : "")%> >5 estrellas</option>
                </select>
                <input type="submit" value="Filtrar" class="filtro-boton">
            </form>
            <div id="lista-sesiones">
                <%
                if(valoraciones.isEmpty()){
                %>
                <p class="sin-rutina">No se ha encontrado ninguna sesion de ejercicio valorada</p>
                <%
                }else{
                    for (ValoracionDTO valoracion : valoraciones) {
                        int punt = (valoracion != null) ? valoracion.getPuntuacion() : 0;
                        String comentario = (valoracion != null && valoracion.getDescripcion() != null) ? valoracion.getDescripcion() : cliente.getNombre() + " " + cliente.getApellidos() + " no ha escrito ningún comentario todavía.";
                %>
                <div class="valoracion">
                    <div class="sesion">
                        <p class="nombre-sesion"><%=valoracion.getSesionejercicio().getEjercicio().getNombre()%></p>
                        <div class="contenedor-iconos">
                            <% for (int i = 0; i < punt; i++) { %>
                            <img src="/img/estrellaamarilla.png" alt="Estrella Amarilla" class="img-icono">
                            <% } %>

                            <%-- Mostrar estrellas blancas para completar hasta 5 --%>
                            <% for (int i = punt; i < 5; i++) { %>
                            <img src="/img/estrellablanca.png" alt="Estrella Blanca" class="img-icono">
                            <% } %>
                        </div>
                    </div>
                    <div class="sesion">
                        <div class="intensidad-div">
                            <p><input type="text" readonly value="<%= (valoracion.getSesionejercicio().getSeries() != null) ? valoracion.getSesionejercicio().getSeries() : "-" %>" class="intensidad" name="series"> series</p>
                            <p><input type="text" readonly value="<%= (valoracion.getSesionejercicio().getRepeticiones() != null) ? valoracion.getSesionejercicio().getRepeticiones() : "-" %>" class="intensidad" name="repeticiones"> repeticiones</p>
                            <p><input type="text" readonly value="<%= (valoracion.getSesionejercicio().getDuracion() != null) ? valoracion.getSesionejercicio().getDuracion() : "-" %>" class="intensidad" name="duracion"> min</p>
                        </div>
                        <div class="descripcion-div">
                            Comentario:
                            <textarea readonly class="descripcion" name="descripcion"><%= comentario %></textarea>
                        </div>
                    </div>
                </div>
                <%
                    }
                }
                %>

            </div>

        </div>
    </div>
</div>
</body>
</html>