# **TrainingGymApp - Aplicación Web de Gimnasio Online**

## **Descripción**
TrainingGymApp es una aplicación web desarrollada con Java y Spring Boot, diseñada para ser un gimnasio online con tres roles principales: **Administrador**, **Entrenador** y **Cliente**. Esta aplicación permite la gestión eficiente de entrenadores, clientes, rutinas de entrenamiento y ejercicios. Los entrenadores pueden diseñar sesiones de entrenamiento y rutinas personalizadas, mientras que los clientes tienen acceso a su progreso, rutinas semanales y pueden calificar los ejercicios completados.

Este proyecto fue desarrollado como parte de la asignatura de Tecnología de Aplicaciones Web en colaboración con otros compañeros, dentro del Grado de Ingeniería de Software de la Universidad de Málaga. Fue uno de los primeros proyectos software que desarrollé con tecnologías como Spring Boot y JSP, aunque el proyecto cumple con los requisitos funcionales, con el tiempo he adquirido más experiencia y he identificado bastantes áreas de mejora, como la refactorización de código y la optimización de la experiencia de usuario.

A pesar de que no tengo tiempo actualmente para realizar todas las mejoras necesarias, este proyecto fue clave en mi desarrollo profesional, permitiéndome aprender a trabajar en equipo, crear aplicaciones web complejas desde cero y adaptarme a nuevas tecnologías.

## **Foto de la Página de Inicio**
![Página de Inicio](ProyectoTrainingGym/src/main/resources/static/img/inicio.png)

## **Características Principales**

- **Roles de Usuario**:
  - **Administrador**: 
    - Gestiona las solicitudes de registro de entrenadores y clientes.
    - Asigna entrenadores a los clientes.
    - Crea y gestiona ejercicios, con descripción y enlaces a videos de YouTube.
  - **Entrenador**:
    - Crea sesiones de entrenamiento con detalles como series y repeticiones o duración para cada ejercicio.
    - Diseña rutinas semanales personalizadas combinando sesiones de entrenamiento.
    - Accede a la información de sus clientes asignados, les asigna rutinas semanales y visualiza sus valoraciones.
  - **Cliente**:
    - Visualiza su rutina semanal y marca los ejercicios completados.
    - Valora cada ejercicio completado con estrellas y comentarios.
    - Visualiza y compara el progreso histórico de sus rutinas y valoraciones.

## **Videos de Demostración**
He preparado varios videos donde se muestra el funcionamiento de la aplicación en diferentes roles y escenarios:

- **Uso como Entrenador**: [Ver video](https://www.youtube.com/watch?v=GnomR2otDqU&ab_channel=PabloPardoDev)
- **Uso como Cliente**: [Ver video](https://www.youtube.com/watch?v=pc_HPwvMtYQ&ab_channel=PabloPardoDev)
- **Registro como Cliente**: [Ver video](https://www.youtube.com/watch?v=oK6D81Bj8uU&ab_channel=PabloPardoDev)
- **Solicitud de Trabajo como Entrenador**: [Ver video](https://www.youtube.com/watch?v=f3y_uaFCrRo&ab_channel=PabloPardoDev)
- **Uso como Administrador**: [Ver video](https://www.youtube.com/watch?v=oIAWd9KiqXw&ab_channel=PabloPardoDev)

## **Tecnologías Utilizadas**

- **Backend**: Java, SpringBoot
- **Frontend**: JSP, HTML, CSS, JavaScript
- **Base de Datos**: MySQL
- **HTML/CSS/JS**: Para el desarrollo de la interfaz de usuario.
- **Herramientas de Desarrollo**: GitHub, Maven, IntelliJ IDEA, Visual Studio Code, Figma, Trello

## **Estructura del Proyecto**
El proyecto está organizado en las siguientes carpetas principales:

- **controller**: Controladores que manejan las solicitudes HTTP.
- **dao**: Acceso a datos y operaciones con la base de datos.
- **dto**: Objetos para la transferencia de datos.
- **entity**: Modelos de la base de datos
- **service**: Lógica de negocio de la aplicación.
- **resources**: Archivos de configuración y recursos estáticos como imágenes.
- **webapp/WEB-INF**: Archivos JSP y recursos relacionados con el frontend

## **Cómo Ejecutar el Proyecto**

1. **Clonar el Repositorio**:
   - Ejecuta el siguiente comando para clonar el proyecto:
     ```bash
     git clone https://github.com/pablopf197/TrainingGymApp.git
     ```

2. **Requisitos**:
   - **JDK 11 o superior**.
   - **MySQL**
   - **Maven**

3. **Base de Datos**:
   En la carpeta BaseDatosActual podrás encontrar dos scripts SQL necesarios para configurar la base de datos correctamente:
   - **`script-creacion-bd.sql`**: Ejecuta este script para crear la base de datos y todas las tablas necesarias.
   - **`script-insert-datos.sql`**: Después de crear la base de datos, ejecuta este script para insertar algunos datos de ejemplo. Esto te permitirá probar la aplicación con contenido ya cargado.

4. **Instalación**:
   - Configura la base de datos y las credenciales de acceso en el archivo `application.properties`.
   - Ejecuta la aplicación con el siguiente comando:
     ```bash
     mvn spring-boot:run
     ```

5. **Accede a la aplicación**:
   - La aplicación estará disponible en `http://localhost:8081`.

## **Mejoras Posibles**
Este proyecto es una versión funcional de la aplicación que aún puede ser mejorada en varias áreas. Algunas de las áreas que podrían optimizarse incluyen:

- **Refactorización del Código**: Existe mucho código repetido en las páginas JSP y CSS que puede ser unificado y optimizado.
- **Frontend**: La interfaz de usuario podría beneficiarse de un diseño más moderno y responsive. Un rediseño podría mejorar la experiencia del usuario.
- **Estructura del Proyecto**: A pesar de que el proyecto funciona correctamente, algunos patrones de diseño y mejores prácticas de arquitectura pueden ser aplicados para mejorar la escalabilidad y mantenibilidad.
- **Pruebas**: Actualmente no se han implementado pruebas automáticas. La incorporación de pruebas unitarias y de integración mejoraría la confiabilidad del proyecto.

Aunque no tengo tiempo de hacer estas mejoras ahora mismo debido a que estoy trabajando en otros proyectos, el código base es completamente funcional y representa una sólida experiencia en el uso de Spring Boot, JSP y gestión de aplicaciones web.
