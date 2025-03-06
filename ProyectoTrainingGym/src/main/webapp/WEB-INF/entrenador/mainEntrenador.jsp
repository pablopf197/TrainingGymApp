
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/mainEntrenador.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
    <nav>
        <div class="logo"><img src="/img/logoGym.png"></div>
        <ul class="enlaces">
            <li><a href="/entrenadorMain/inicio"  id="activo">Inicio</a></li>
            <li><a href="/entrenadorMain/rutinas">Rutinas</a></li>
            <li><a href="/entrenadorMain/sesiones">Sesiones</a></li>
            <li><a href="/entrenadorMain/clientes">Clientes</a></li>
            <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
        </ul>
    </nav>
    <div class="imagen-fondo">
        <div class="capa-gris"></div>
        <h1>Hola ${usuario.nombre} ${usuario.apellidos}</h1>
        <div class="contenido">
            <div class="rutina" onclick="window.location='/entrenadorMain/rutinas'">
                <img src="/img/rutinaIcono.png" alt="Rutina Icono">
                <h2>Rutinas Semanales</h2>
            </div>
            <div class="sesiones" onclick="window.location='/entrenadorMain/sesiones'">
                <img src="/img/sesionIcono.png" alt="Sesion Icono">
                <h2>Sesiones de Entrenamiento</h2>
            </div>
            <div class="clientes" onclick="window.location='/entrenadorMain/clientes'">
                <img src="/img/clienteIcono.png" alt="Cliente Icono">
                <h2>Clientes</h2>
            </div>
        </div>
    </div>
</body>
</html>
