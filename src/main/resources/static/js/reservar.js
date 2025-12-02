// Script para `reservar.html`: resumen dinámico y validaciones
const reservaForm = document.getElementById('reservaForm');
const resumenHotel = document.getElementById('resumenHotel');
const resumenFechas = document.getElementById('resumenFechas');
const resumenHuespedes = document.getElementById('resumenHuespedes');
const resumenHabitacion = document.getElementById('resumenHabitacion');

function crearModal(mensaje, tipo = 'exito') {
    const overlay = document.createElement('div');
    overlay.style.position = 'fixed';
    overlay.style.top = '0';
    overlay.style.left = '0';
    overlay.style.width = '100%';
    overlay.style.height = '100%';
    overlay.style.background = 'rgba(0,0,0,0.5)';
    overlay.style.display = 'flex';
    overlay.style.justifyContent = 'center';
    overlay.style.alignItems = 'center';
    overlay.style.zIndex = '9999';

    const modal = document.createElement('div');
    modal.style.background = 'white';
    modal.style.padding = '22px';
    modal.style.borderRadius = '10px';
    modal.style.width = '340px';
    modal.style.textAlign = 'center';
    modal.style.boxShadow = '0 6px 20px rgba(0,0,0,0.2)';

    const icon = document.createElement('div');
    icon.style.fontSize = '34px';
    icon.style.marginBottom = '10px';
    icon.textContent = tipo === 'exito' ? '✅' : '❌';

    const texto = document.createElement('p');
    texto.textContent = mensaje;
    texto.style.marginBottom = '14px';

    const btn = document.createElement('button');
    btn.textContent = 'Aceptar';
    btn.style.background = '#2c5282';
    btn.style.color = 'white';
    btn.style.border = 'none';
    btn.style.padding = '8px 14px';
    btn.style.borderRadius = '8px';
    btn.style.cursor = 'pointer';
    btn.onclick = () => overlay.remove();

    modal.appendChild(icon);
    modal.appendChild(texto);
    modal.appendChild(btn);
    overlay.appendChild(modal);
    document.body.appendChild(overlay);
}

function actualizarResumen() {
    if (!reservaForm) return;
    resumenHotel.textContent = reservaForm.hotel.value || '-';
    resumenFechas.textContent = (reservaForm.checkin.value && reservaForm.checkout.value)
        ? `${reservaForm.checkin.value} → ${reservaForm.checkout.value}`
        : '-';
    resumenHuespedes.textContent = reservaForm.huespedes.value || '-';
    resumenHabitacion.textContent = reservaForm.habitacion.value || '-';
}

if (reservaForm) {
    // Actualizar resumen en input
    reservaForm.addEventListener('input', actualizarResumen);

    reservaForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const hotel = reservaForm.hotel.value.trim();
        const checkin = reservaForm.checkin.value;
        const checkout = reservaForm.checkout.value;
        const huespedes = parseInt(reservaForm.huespedes.value, 10);
        const habitacion = reservaForm.habitacion.value.trim();

        if (!hotel) {
            crearModal('Seleccione un hotel válido.', 'error');
            return;
        }

        if (!checkin || !checkout) {
            crearModal('Ingrese fechas de entrada y salida.', 'error');
            return;
        }

        const inDate = new Date(checkin);
        const outDate = new Date(checkout);
        if (!(outDate > inDate)) {
            crearModal('La fecha de salida debe ser posterior a la fecha de entrada.', 'error');
            return;
        }

        if (!Number.isInteger(huespedes) || huespedes < 1) {
            crearModal('Ingrese un número válido de huéspedes (mínimo 1).', 'error');
            return;
        }

        if (!habitacion) {
            crearModal('Seleccione un tipo de habitación.', 'error');
            return;
        }

        // Obtener texto legible de la opción seleccionada y notas
        const habitacionLabel = reservaForm.habitacion.options[reservaForm.habitacion.selectedIndex].textContent || habitacion;
        const notas = document.getElementById('notas') ? document.getElementById('notas').value.trim() : '';

        // Leer email guardado (si el usuario inició sesión previamente)
        let correoGuardado = null;
        try {
            correoGuardado = localStorage.getItem('loginEmail') || null;
        } catch (err) {
            console.error('Error leyendo loginEmail de localStorage', err);
            correoGuardado = null;
        }

        // Construir objeto reserva (forma estándar para storage)
        const nuevaReserva = {
            cliente: null,
            correo: correoGuardado,
            hotel: hotel,
            llegada: checkin,
            salida: checkout,
            huespedes: huespedes,
            habitacion: habitacionLabel,
            comentarios: notas || '-',
            creadoEn: new Date().toISOString()
        };

        // Guardar en localStorage bajo la clave 'reservas'
        try {
            const existentes = JSON.parse(localStorage.getItem('reservas') || '[]');
            existentes.push(nuevaReserva);
            localStorage.setItem('reservas', JSON.stringify(existentes));
        } catch (err) {
            console.error('Error guardando reserva en localStorage', err);
        }

        // Éxito: mostrar modal y reiniciar
        crearModal('Reserva confirmada con éxito. ¡Disfruta tu viaje!', 'exito');
        setTimeout(() => {
            reservaForm.reset();
            resumenHotel.textContent = '-';
            resumenFechas.textContent = '-';
            resumenHuespedes.textContent = '-';
            resumenHabitacion.textContent = '-';
        }, 600);
    });

    // Inicializar resumen al cargar
    actualizarResumen();
}
