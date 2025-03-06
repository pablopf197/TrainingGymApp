<%@ page import="es.uma.proyectotaw.entity.UsuarioEntity" %>
<%@ page import="es.uma.proyectotaw.dto.UsuarioDTO" %>

<%UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/solicitudEntrenador.css">
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
    <h1>Solicitud de entrenador:</h1> </br>

    <div class="contenido">
        <table>
            <tr>
                <th>Nombre:</th>
                <th><input class="inputs" value="<%=usuario.getNombre()%>" readonly></th>
                <th>Apellidos:</th>
                <th><input class="inputs" value="<%=usuario.getApellidos()%>" readonly></th>
            </tr>
            <tr>
                <th>Fecha de nacimiento:</th>
                <th><input class="inputs" value="<%=usuario.getFechaNacimiento()%>" readonly></th>
                <th>Género:</th>
                <th><input class="inputs" value="<%=usuario.getGenero()%>" readonly></th>
            </tr>
            <tr>
                <th>Tipo de entrenador:</th>
                <th><input class="inputs" value="<%=usuario.getTipoEntrenamiento().getTipo()%>" readonly></th>
                <th>DNI:</th>
                <th><input class="inputs" value="<%=usuario.getDni()%>" readonly></th>
            </tr>
            <tr>
                <th>Correo:</th>
                <th><input class="inputs" value="<%=usuario.getCorreo()%>" readonly></th>
                <th>Teléfono:</th>
                <th><input class="inputs" value="<%=usuario.getTelefono()%>" readonly></th>
            </tr>
            <tr>
                <th></th>
                <th></th>
                <th><button class="filtrar" onclick="window.location='/adminMain/solicitud/aceptar/<%=usuario.getId()%>'">Aceptar</button></th>
                <th><button class="filtrar" onclick="window.location='/adminMain/solicitud/rechazar/<%=usuario.getId()%>'">Rechazar</button></th>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
