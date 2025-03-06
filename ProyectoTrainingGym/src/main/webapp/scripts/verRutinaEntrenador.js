let botonPulsado = false;

// Función para mostrar el modal
function mostrarModal() {
    botonPulsado = true; // Se ha pulsado el botón
    document.getElementById('modalAgregarSesion').style.display = 'block';
}

// Evento para cerrar el modal si se hace clic fuera de él

window.onclick = function(event) {
    let modal = document.getElementById('modalAgregarSesion');
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function addSesion(id, name) {
    let nombre = name;
    let listaSesiones = document.getElementById('lista-sesiones');
    let sesion = document.createElement('div');
    sesion.classList.add('sesion');
    sesion.innerHTML = `
                <p class="nombre-sesion">${nombre}</p>
                <input type="hidden" name="sesiones" value="${id}">
                <button class="btn-ver-sesion">Ver Sesión</button>
                <div class="contenedor-iconos">
                    <img src="/img/flecha-arriba.png" alt="Icono Subir" class="img-icono" onclick="moverSesion(this, 'arriba')">
                    <img src="/img/flecha-abajo.png" alt="Icono Bajar" class="img-icono" onclick="moverSesion(this, 'abajo')">
                    <img src="/img/borrar.png" alt="Icono Borrar" class="img-icono" onclick="borrarSesion(this)">
                </div>
            `;






    listaSesiones.appendChild(sesion);
    document.getElementById('modalAgregarSesion').style.display = 'none';
    botonPulsado = false;

}

function moverSesion(elemento, direccion) {
    let sesion = elemento.closest('.sesion');
    if (direccion === 'arriba') {
        let sesionAnterior = sesion.previousElementSibling;
        if (sesionAnterior !== null) {
            sesion.parentNode.insertBefore(sesion, sesionAnterior);
        }
    } else if (direccion === 'abajo') {
        let sesionSiguiente = sesion.nextElementSibling;
        if (sesionSiguiente !== null) {
            sesion.parentNode.insertBefore(sesionSiguiente, sesion);
        }
    }
}

function borrarSesion(elemento) {
    let sesion = elemento.closest('.sesion');
    sesion.remove();
}