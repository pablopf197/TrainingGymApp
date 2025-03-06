<%@ page import="es.uma.proyectotaw.entity.UsuarioEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.DayOfWeek" %>
<%@ page import="es.uma.proyectotaw.dto.UsuarioDTO" %>
<%
    List<UsuarioDTO> listaClientes = (List<UsuarioDTO>) request.getAttribute("clientes");
    LocalDate fecha = LocalDate.now();
    LocalDate lunes = fecha.with(DayOfWeek.MONDAY);
    String fechaLunes = lunes.toString();
    String filtro = (String) request.getAttribute("filtro");
    if(filtro == null ){
        filtro = "";
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/clientesEntrenador.css">
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
        <div class="contenedor-cliente-titulo" style="width: auto;">
            <div class="contenido" style="justify-content: normal;">
                <div>
                    <h1 style="margin: 0px;">Clientes</h1>
                    <div class="tabla-filtro-container" style="width: 100%;">

                        <div class="tabla">
                            <table>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Apellidos</th>
                                    <th>Dni</th>
                                    <th></th>
                                    <th></th>
                                        <%
                            for (UsuarioDTO cliente: listaClientes) {
                        %>

                                <tr>
                                    <td><%= cliente.getNombre()%></td>
                                    <td><%= cliente.getApellidos()%></td>
                                    <td><%= cliente.getDni()%></td>

                                    <td>
                                        <div class="btn-cliente">
                                            <button onclick="window.location.href='/entrenadorMain/clientes/perfil?id=<%=cliente.getId()%>'">Informacion del cliente</button>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="btn-cliente">
                                            <button onclick="window.location.href='/entrenadorMain/clientes/entrenamiento?id=<%=cliente.getId()%>&fecha=<%=fechaLunes%>'">Entrenamiento</button>
                                        </div>
                                    </td>

                                </tr>
                                <%}%>
                            </table>
                        </div>
                </div>

                </div>
                <div>

                    <div class="filtro-container" style="width: 100% !important; max-width: 100% !important;">
                        <div class="filtro">
                            <form class="formulario" action="/entrenadorMain/clientes/filtrar" method="post">
                                <p>Escribe nombre, apellidos o DNI</p>
                                <input class="entrada" name="filtro" value="<%=filtro%>"> </br>
                                <div class="home-container07">
                                    <div class="btn-cliente">
                                        <input class="filtrar" type="submit" value="Filtrar">
                                    </div>
                                </div>
                            </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
