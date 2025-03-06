
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/acceso.css">
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
    <nav>
        <div class="logo"><img src="/img/logoGym.png"></div>
        <ul class="enlaces">
            <li><a href="inicio">Inicio</a></li>
            <li><a href="trabaja">Trabaja con Nosotros</a></li>
            <li><a href="acceso" class="acceso">Acceso</a></li>
        </ul>
    </nav>
    <div class="imagen-fondo">
        <div class="capa-gris"></div>
        <div class="contenido">
            <h2>Inicio de Sesión</h2>
            <form:form action="/login" modelAttribute="usuario" method="post" class="formulario">
                <div>
                    <label for="username">Nombre de usuario:</label>
                    <form:input type="text" id="username" path="user" />
                </div>
                <div>
                    <label for="password">Contraseña:</label>
                    <form:password id="password" path="password" />
                </div>
                <form:button type="submit">INICIAR SESIÓN</form:button>
            </form:form>
            <p id="mensaje-error">${error}</p>
            <p>¿Aún no tienes cuenta? <a href="registro" id="enlace-registrarse">Registrarse</a></p>
        </div>
    </div>
</body>
</html>
