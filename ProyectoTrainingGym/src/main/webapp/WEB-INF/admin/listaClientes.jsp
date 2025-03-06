<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.UsuarioDTO" %>

<%
    List<UsuarioDTO> listaClientes = (List<UsuarioDTO>) request.getAttribute("listaClientes");
    List<UsuarioDTO> entrenadoresBodyBuilding = (List<UsuarioDTO>) request.getAttribute("entrenadoresBodyBuilding");
    List<UsuarioDTO> entrenadoresCrossTraining = (List<UsuarioDTO>) request.getAttribute("entrenadoresCrossTraining");
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/listaClientes.css">
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
        <li><a href="/adminMain/clientes" id="activo">Clientes</a></li>
        <li><a href="/adminMain/solicitudes">Solicitudes</a></li>
        <li><a href="/adminMain/ejercicios">Ejercicios</a></li>
        <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
    </ul>
</nav>
<div class="imagen-fondo">
    <div class="capa-gris"></div>
    <h1 class="encabezado">Lista de clientes</h1> </br>

    <div class="contenido">
        <table>
            <tr>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Genero</th>
                <th>Entrenador</th>
                <th></th>
                <th></th>
            </tr>
            <%for(UsuarioDTO u : listaClientes){%>
            <tr>
                <form action="/adminMain/editarCliente" method="post">
                    <input type="hidden" name="idCliente" value="<%=u.getId()%>">
                    <th><%=u.getDni()%></th>
                    <th><%=u.getNombre()%> <%=u.getApellidos()%></th>
                    <th><%=u.getGenero()%></th>
                    <th>
                        <select name="idEntrenador">
                            <option value="0" <%=(u.getEntrenador() == null)? "selected" : ""%>>Sin Entrenador</option>
                            <%if(u.getTipoEntrenamiento().getTipo().toString().equals("body_building")){
                                for(UsuarioDTO e : entrenadoresBodyBuilding){%>
                                    <option value="<%=e.getId()%>" <%=(u.getEntrenador() != null && e.equals(u.getEntrenador())? "selected" : "")%>><%=e.getNombre()%> <%=e.getApellidos()%></option>
                                <%}
                            } else {
                                for(UsuarioDTO e : entrenadoresCrossTraining){%>
                                    <option value="<%=e.getId()%>" <%=(u.getEntrenador() != null && e.equals(u.getEntrenador())? "selected" : "")%>><%=e.getNombre()%> <%=e.getApellidos()%></option>
                                <%}
                            }%>
                        </select>
                    </th>
                    <th><input class="boton" type="submit" value="Guardar"></th>
                </form>
                    <th><button class="boton" onclick="window.location='/adminMain/cliente/borrar/<%=u.getId()%>'">Borrar</button></th>
            </tr>
            <%}%>
        </table>
    </div>
    <div class="contenido2">
        <div class="filtro">
            <form class="formulario" action="/adminMain/filtrar/clientes" method="post">
                <p>Escribe nombre, apellidos o DNI</p>
                <input class="entrada" name="filtro"> </br>
                <input class="filtrar" type="submit" value="Filtrar">
            </form>
        </div>
    </div>
</div>
</body>
</html>
