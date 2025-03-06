// Variable para controlar si se ha pulsado el botón de añadir ejercicio
let botonPulsado = false;

// Función para mostrar el modal
function mostrarModal() {
    botonPulsado = true; // Se ha pulsado el botón
    document.getElementById('modalAgregarEjercicio').style.display = 'block';
}

// Evento para cerrar el modal si se hace clic fuera de él

window.onclick = function(event) {
    let modal = document.getElementById('modalAgregarEjercicio');
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Función para añadir un ejercicio a la lista
function addEjercicio(id, name) {
    let nombre = name;
    console.log('Añadiendo ejercicio ' + id + ' - ' + name);
    let listaEjercicios = document.getElementById('lista-ejercicios');
    let ejercicio = document.createElement('div');
    ejercicio.classList.add('ejercicio');
    ejercicio.innerHTML = `
                <p class="nombre-ejercicio">${nombre}</p>
                <input type="hidden" name="ejercicios" value="${id}">
                <button class="btn-ver-ejercicio">Ver ejercicio</button>
                <p><input type="text" value="-" class="intensidad" name="series">series</p>
                <p><input type="text" value="-" class="intensidad" name="repeticiones">repeticiones</p>
                <p><input type="text" value="-" class="intensidad" name="duracion">min</p>
                <div class="contenedor-iconos">
                    <img src="/img/flecha-arriba.png" alt="Icono Subir" class="img-icono" onclick="moverEjercicio(this, 'arriba')">
                    <img src="/img/flecha-abajo.png" alt="Icono Bajar" class="img-icono" onclick="moverEjercicio(this, 'abajo')">
                    <img src="/img/borrar.png" alt="Icono Borrar" class="img-icono" onclick="borrarEjercicio(this)">
                </div>
            `;






    listaEjercicios.appendChild(ejercicio);
    document.getElementById('modalAgregarEjercicio').style.display = 'none';
    botonPulsado = false;

}

function moverEjercicio(elemento, direccion) {
    let ejercicio = elemento.closest('.ejercicio');
    if (direccion === 'arriba') {
        let ejercicioAnterior = ejercicio.previousElementSibling;
        if (ejercicioAnterior !== null) {
            ejercicio.parentNode.insertBefore(ejercicio, ejercicioAnterior);
        }
    } else if (direccion === 'abajo') {
        let ejercicioSiguiente = ejercicio.nextElementSibling;
        if (ejercicioSiguiente !== null) {
            ejercicio.parentNode.insertBefore(ejercicioSiguiente, ejercicio);
        }
    }
}

function borrarEjercicio(elemento) {
    let ejercicio = elemento.closest('.ejercicio');
    ejercicio.remove();
}