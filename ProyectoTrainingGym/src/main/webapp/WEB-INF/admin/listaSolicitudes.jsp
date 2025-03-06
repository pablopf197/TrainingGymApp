<%@ page import="es.uma.proyectotaw.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.UsuarioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<UsuarioDTO> listaEntrenadores = (List<UsuarioDTO>) request.getAttribute("listaEntrenadores"); %>
<% List<UsuarioDTO> listaClientes = (List<UsuarioDTO>) request.getAttribute("listaClientes"); %>

<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/listaSolicitudes.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
<nav>
    <div class="logo"><img src="/img/logoGym.png"></div>
    <ul class="enlaces">
        <li><a href="/adminMain/inicio">Inicio</a></li>
        <li><a href="/adminMain/entrenadores">Entrenadores</a></li>
        <li><a href="/adminMain/clientes">Clientes</a></li>
        <li><a href="/adminMain/solicitudes" id="activo">Solicitudes</a></li>
        <li><a href="/adminMain/ejercicios">Ejercicios</a></li>
        <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
    </ul>
</nav>
<div class="imagen-fondo">
    <div class="capa-gris"></div>
    <h1 class="body">Solicitudes Entrenadores:</h1>
    <h1 class="cross">Solicitudes Clientes:</h1>
    <div class="contenido">
        <table class="tabla">
            <tbody>
            <%if(listaEntrenadores.size() > 0){
                for(UsuarioDTO e : listaEntrenadores){ %>
            <tr>
                <td><%=e.getNombre()%> <%=e.getApellidos()%></td>
                <td>
                    <button class="boton" onclick="window.location='/adminMain/solicitud/entrenador/<%=e.getId()%>'">Ver Datos</button>
                </td>
                <td>
                    <button class="boton" onclick="window.location='/adminMain/solicitud/aceptar/<%=e.getId()%>'">Aceptar</button>
                </td>
                <td>
                    <button class="boton" onclick="window.location='/adminMain/solicitud/rechazar/<%=e.getId()%>'">Rechazar</button>
                </td>
            </tr>
                <%}
            }else{%>
            <h3>No hay solicitudes de entrenadores</h3>
            <%}%>

            </tbody>
        </table>
    </div>

    <div class="contenido">
        <table class="tabla">
            <tbody>
            <%if(listaClientes.size() > 0){
                for(UsuarioDTO c : listaClientes){ %>
            <tr>
                <td><%=c.getNombre()%> <%=c.getApellidos()%></td>
                <td>
                    <button class="boton" onclick="window.location='/adminMain/solicitud/cliente/<%=c.getId()%>'">Ver Datos</button>
                </td>
                <td>
                    <button class="boton" onclick="window.location='/adminMain/solicitud/aceptar/<%=c.getId()%>'">Aceptar</button>
                </td>
                <td>
                    <button class="boton" onclick="window.location='/adminMain/solicitud/rechazar/<%=c.getId()%>'">Rechazar</button>
                </td>
            </tr>
            <%}
            }else{%>
            <h3>No hay solicitudes de clientes</h3>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

