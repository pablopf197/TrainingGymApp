
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/mainAdmin.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
    <nav>
        <div class="logo"><img src="/img/logoGym.png"></div>
        <ul class="enlaces">
            <li><a href="/adminMain/inicio"  id="activo">Inicio</a></li>
            <li><a href="/adminMain/entrenadores">Entrenadores</a></li>
            <li><a href="/adminMain/clientes">Clientes</a></li>
            <li><a href="/adminMain/solicitudes">Solicitudes</a></li>
            <li><a href="/adminMain/ejercicios">Ejercicios</a></li>
            <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
        </ul>
    </nav>
    <div class="imagen-fondo">
        <div class="capa-gris"></div>
        <h1>Bienvenido Administrador! ¿Qué quieres hacer?</h1>
        <div class="contenido">
            <div class="entrenadores" onclick="window.location='/adminMain/entrenadores'">
                <img src="/img/entrenadoresIcono.png" alt="Entrenador Icono">
                <h2>Entrenadores</h2>
            </div>
            <div class="clientes" onclick="window.location='/adminMain/clientes'">
                <img src="/img/clienteIcono.png" alt="Cliente Icono">
                <h2>Clientes</h2>
            </div>
            <div class="solicitudes" onclick="window.location='/adminMain/solicitudes'">
                <img src="/img/solicitudesIcono.png" alt="Solicitud Icono">
                <h2>Solicitudes</h2>
            </div>
            <div class="ejercicios" onclick="window.location='/adminMain/ejercicios'">
                <img src="/img/ejercicioIcono.png" alt="Ejercicio Icono">
                <h2>Ejercicios</h2>
            </div>
        </div>
    </div>
</body>
</html>
