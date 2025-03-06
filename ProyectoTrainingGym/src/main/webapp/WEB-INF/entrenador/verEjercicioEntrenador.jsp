<%@ page import="es.uma.proyectotaw.entity.SesionentrenamientoEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.SesionentrenamientoHasSesionejercicioEntity" %>
<%@ page import="es.uma.proyectotaw.entity.EjercicioEntity" %>
<%@ page import="es.uma.proyectotaw.dto.EjercicioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EjercicioDTO ejercicio = (EjercicioDTO) request.getAttribute("ejercicio");
    String entrenamiento;
    if(ejercicio.getTipoEntrenamiento().getId() == 1){
        entrenamiento = "Body building";
    }else{
        entrenamiento = "Cross training";
    }
%>
<html>
<head>
    <title>Training Gym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/verEjercicioEntrenador.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
<nav>
    <div class="logo"><img src="/img/logoGym.png"></div>
    <ul class="enlaces">
        <li><a href="/entrenadorMain/inicio">Inicio</a></li>
        <li><a href="/entrenadorMain/rutinas">Rutinas</a></li>
        <li><a href="/entrenadorMain/sesiones" id="activo">Sesiones</a></li>
        <li><a href="/entrenadorMain/clientes">Clientes</a></li>
        <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
    </ul>
</nav>
<div class="imagen-fondo">
    <div class="capa-gris">
        <div class="contenedor-principal">
            <h1>Ejercicio: <%=ejercicio.getNombre()%></h1>
            <div>
                <p>Tipo de entrenamiento: <%=entrenamiento%></p>
                <p>Descripción: <%=ejercicio.getDescripcion()%></p>
            </div>
            <div class="video-container">
                <iframe src="<%=ejercicio.getVideo().replace("watch?v=", "embed/")%>" allowfullscreen></iframe>
        
            </div>
        </div>
    </div>
</div>
</body>
</html>
