<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.DayOfWeek" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LocalDate fecha = LocalDate.now();
    LocalDate lunes = fecha.with(DayOfWeek.MONDAY);
    String fechaLunes = lunes.toString();
%>

<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/mainCliente.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
    <nav>
        <div class="logo"><img src="/img/logoGym.png"></div>
        <ul class="enlaces">
            <li><a href="/clienteMain/inicio"  id="activo">Inicio</a></li>
            <li><a href="/clienteMain/perfil">Perfil</a></li>
            <li><a href="/clienteMain/rutina?fecha=<%=fechaLunes%>">Rutina</a></li>
            <li><a href="/clienteMain/desarrollo?fecha=<%=fechaLunes%>">Desarrollo</a></li>
            <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
        </ul>
    </nav>
    <div class="imagen-fondo">
        <div class="capa-gris"></div>
        <h1>Hola ${usuario.nombre} ${usuario.apellidos}</h1>
        <div class="contenido">
            <div class="perfil" onclick="window.location='/clienteMain/perfil'">
                <img src="/img/clienteIcono.png" alt="Cliente Icono">
                <h2>Perfil Personal</h2>
            </div>
            <div class="rutina" onclick="window.location.href='/clienteMain/rutina?fecha=<%=fechaLunes%>'">
                <img src="/img/rutinaIcono.png" alt="Rutina Icono">
                <h2>Rutina Semanal</h2>
            </div>
            <div class="desarrollo" onclick="window.location='/clienteMain/desarrollo?fecha=<%=fechaLunes%>'">
                <img src="/img/clienteIcono.png" alt="Cliente Icono">
                <h2>Desarrollo y Evolución</h2>
            </div>
        </div>
    </div>
</body>
</html>
