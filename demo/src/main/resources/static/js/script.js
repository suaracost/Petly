document.addEventListener('DOMContentLoaded', function() {
    // Selector para el carrusel de servicios
    const carruselServicios = document.querySelector('.carrusel:not(.carrusel-veterinarios)');
    document.querySelector('.carrusel-control.izquierda:not(.carrusel-veterinarios-control)').addEventListener('click', function() {
      carruselServicios.scrollBy({ left: -432, behavior: 'smooth' });
    });
    document.querySelector('.carrusel-control.derecha:not(.carrusel-veterinarios-control)').addEventListener('click', function() {
      carruselServicios.scrollBy({ left: 432, behavior: 'smooth' });
    });

    // Selector para el carrusel de veterinarios
    const carruselVeterinarios = document.querySelector('.carrusel-veterinarios');
    document.querySelector('.carrusel-veterinarios-control.izquierda').addEventListener('click', function() {
      carruselVeterinarios.scrollBy({ left: -432, behavior: 'smooth' });
    });
    document.querySelector('.carrusel-veterinarios-control.derecha').addEventListener('click', function() {
      carruselVeterinarios.scrollBy({ left: 432, behavior: 'smooth' });
    });
});
