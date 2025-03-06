
<%@ page import="es.uma.proyectotaw.entity.TipoentrenamientoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.TipoentrenamientoEnum" %><%@ page
contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <head>
        <title>TrainingGym</title>
        <link rel="stylesheet" href="/styles/registro.css" />
        <link rel="stylesheet" href="/styles/general.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100..900;1,100..900&display=swap"
            rel="stylesheet"
        />
    </head>

    <body>
        <nav>
            <div class="logo"><img src="/img/logoGym.png" /></div>
            <ul class="enlaces">
                <li><a href="inicio">Inicio</a></li>
                <li><a href="trabaja">Trabaja con Nosotros</a></li>
                <li><a href="acceso" class="acceso">Acceso</a></li>
            </ul>
        </nav>
        <div class="imagen-fondo">
            <div class="capa-gris"></div>
            <div class="contenido">
                <form action="/register" method="post" id="form" name="form" class="home-form">
                    <h1 style="margin-left: 29px; margin-bottom: -30px;">
                        ¡Únete a TrainingGym!
                    </h1>
                    <h1 class="home-text02">Datos personales</h1>
                    <div id="DatosPersonales" class="home-container01">
                        <div class="home-container02">
                            <div class="home-container03">
                                <input type="text" id="nombre" name="nombre" required placeholder="Nombre" class="home-textinput"/>
                                <input type="text" id="apellidos" name="apellidos" required placeholder="Apellidos" class="home-textinput01"/>
                            </div>

                            <div class="home-container04">
                                <input type="text" id="Fecha_nacimiento" name="Fecha_nacimiento" required placeholder="Fecha de Nacimiento: (dd/mm/aaaa)"class="home-textinput02"/>
                                
                                <div class="home-container05">
                                    <div class="home-container06">
                                        <div class="home-container07">
                                            <label class="home-text01">Sexo:&nbsp;</label
                                            ><select class="select" name="sexo">
                                                <option value="Hombre">Hombre</option>
                                                <option value="Mujer">Mujer</option>
                                                <option value="Otro">Otro</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input type="text" id="DNI" name="DNI" required placeholder="DNI/NIF" class="home-textinput03" />
                        </div>
                    </div>
                    <h1 class="home-text02">
                        <span>Datos de&nbsp;Contacto</span><br />
                    </h1>
                    <div id="DatosContacto" class="home-container08">
                        <div class="home-container09">
                            <input type="text" id="eMail" name="eMail" required placeholder="Correo Electrónico" class="home-textinput04"/>
                        </div>
                        <div class="home-container10">
                            <input type="text" id="telefono" name="telefono" required placeholder="Número de Teléfono" class="home-textinput05"/>
                        </div>
                    </div>
                    <h1 class="home-text05">
                        <span>Datos Físicos y Objetivos</span><br />
                    </h1>
                    <div id="Objetivos" class="home-container11">
                        <div class="home-container12">
                            <div class="home-container13">
                                <input type="text" id="altura" name="altura" required placeholder="Altura (en cm)" class="home-textinput06"/>
                                <input type="text" id="peso" name="peso" required placeholder="Peso (en kg)" class="home-textinput07"/>
                            </div>
                            <div class="home-container14">
                                <div class="home-container15" style="margin-top: 15px; margin-bottom: 5px;" >
                                    <label class="home-text08"
                                        >Tipo de Entrenamiento:</label
                                    ><select class="select" name="tipoEntrenamiento">
                                        <%
                                            for(TipoentrenamientoEnum tipo : TipoentrenamientoEnum.values()){

                                        %>
                                        <option value=<%=tipo%>><%= tipo%></option>
                                        <%
                                            }
                                        %>

                                    </select>
                                </div>
                            </div>
                            <div class="home-container16">
                                <div class="home-container17" style="margin-top: 15px;">
                                    <label>Objetivos:</label>
                                    <textarea name="objetivos" class="home-textarea">
                                    </textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <h1 class="home-text10">Datos de Acceso</h1>
                    <div class="home-container18">
                        <input type="text" id="usuario" name="usuario" required placeholder="Nombre de Usuario" class="home-textinput08"/>
                        <input type="password" id="contraseña" name="contraseña" required placeholder="Contraseña" class="home-textinput09" style="margin-left: 30px !important;"/>
                    </div>
                    <button type="submit" id="validar" class="home-button">
                        <span
                            ><span class="home-text12">Validar</span><br
                        /></span>
                    </button>
                </form>
                <p>
                    ¿Ya tienes cuenta?
                    <a href="acceso" id="enlace-registrarse">Inicia Sesión</a>
                </p>
            </div>
        </div>
    </body>
</html>
