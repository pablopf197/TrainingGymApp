let botonPulsado = false;

// Función para mostrar el modal
function mostrarModal() {
    botonPulsado = true; // Se ha pulsado el botón
    document.getElementById('modalAsignarRutina').style.display = 'block';
}

// Evento para cerrar el modal si se hace clic fuera de él

window.onclick = function(event) {
    let modal = document.getElementById('modalAsignarRutina');
    if (event.target == modal) {
        modal.style.display = "none";
    }
}