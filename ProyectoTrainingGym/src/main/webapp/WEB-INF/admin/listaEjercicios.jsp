<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.EjercicioDTO" %>

<%List<EjercicioDTO> listaEjercicios = (List<EjercicioDTO>) request.getAttribute("listaEjercicios"); %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainingGym</title>
    <link rel="stylesheet" href="/styles/general.css">
    <link rel="stylesheet" href="/styles/listaEjercicios.css">
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
        <li><a href="/adminMain/solicitudes">Solicitudes</a></li>
        <li><a href="/adminMain/ejercicios" id="activo">Ejercicios</a></li>
        <li><a href="/inicio" class="cerrar-sesion">Cerrar Sesión</a></li>
    </ul>
</nav>
<div class="imagen-fondo">
    <div class="capa-gris"></div>
    <h1 class="encabezado">Lista de ejercicios:</h1> </br>

    <div class="contenido">
        <%if(listaEjercicios.isEmpty()){%>
        <h2>No hay ninfún ejercicio creado actualmente</h2>
        <%} else {%>
        <table>
            <tr>
                <th>Nombre</th>
                <th>Tipo de ejercicio</th>
                <th></th>
                <th></th>
            </tr>
            <%for(EjercicioDTO e : listaEjercicios){%>
            <tr>
                <th><%=e.getNombre()%></th>
                <th><%=e.getTipoEntrenamiento().getTipo()%></th>
                <th><button class="boton" onclick="window.location='/adminMain/datosEjercicio/<%=e.getId()%>'">Editar</button></th>
                <th><button class="boton" onclick="window.location='/adminMain/borrarEjercicio/<%=e.getId()%>'">Borrar</button></th>
            </tr>
            <%}%>
        </table>
        <%}%>
    </div>
    <div class="contenido2">
        <div class="filtro">
            <form class="formulario" action="/adminMain/filtrar/ejercicios" method="post">
                <p>Escribe nombre, o tipo de ejercicio</p>
                <input class="entrada" name="filtro"> </br>
                <input class="filtrar" type="submit" value="Filtrar">
            </form>
        </div>
        <button class="boton" onclick="window.location='/adminMain/nuevoEjercicio'">Crear nuevo </br>ejercicio</button>
    </div>
</div>
</body>
</html>
