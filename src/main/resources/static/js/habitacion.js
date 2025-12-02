let habitaciones = JSON.parse(localStorage.getItem("habitaciones")) || [];

function mostrarHabitaciones() {
    const tabla = document.querySelector("#habitacionesTable tbody");
    if (!tabla) return;
    tabla.innerHTML = "";

    habitaciones.forEach(h => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td>${h.numero}</td>
            <td>${h.tipo}</td>
            <td>${h.precio}</td>
            <td>${h.estado}</td>
        `;
        tabla.appendChild(fila);
    });
}

mostrarHabitaciones();

document.getElementById("habitacionForm")?.addEventListener("submit", e => {
    e.preventDefault();

    const habitacion = {
        numero: document.getElementById("numero").value,
        tipo: document.getElementById("tipo").value,
        precio: document.getElementById("precio").value,
        estado: document.getElementById("estado").value
    };

    if (!habitacion.numero || !habitacion.tipo || !habitacion.precio) {
        alert("Completa todos los campos.");
        return;
    }

    habitaciones.push(habitacion);
    localStorage.setItem("habitaciones", JSON.stringify(habitaciones));

    mostrarHabitaciones();
    e.target.reset();
});


// Modal dinámico

function crearModal(mensaje, tipo = "exito") {
    const overlay = document.createElement("div");
    overlay.style.position = "fixed";
    overlay.style.top = "0";
    overlay.style.left = "0";
    overlay.style.width = "100%";
    overlay.style.height = "100%";
    overlay.style.background = "rgba(0,0,0,0.5)";
    overlay.style.display = "flex";
    overlay.style.justifyContent = "center";
    overlay.style.alignItems = "center";
    overlay.style.zIndex = "9999";

    const modal = document.createElement("div");
    modal.style.background = "white";
    modal.style.padding = "25px";
    modal.style.borderRadius = "12px";
    modal.style.width = "300px";
    modal.style.textAlign = "center";
    modal.style.boxShadow = "0 4px 15px rgba(0,0,0,0.3)";
    modal.style.animation = "fadeIn 0.3s ease";

    const texto = document.createElement("p");
    texto.textContent = mensaje;
    texto.style.marginBottom = "15px";
    texto.style.color = tipo === "error" ? "red" : "black";

    const btn = document.createElement("button");
    btn.textContent = "Aceptar";
    btn.style.background = "#2c5282";
    btn.style.color = "white";
    btn.style.border = "none";
    btn.style.padding = "8px 15px";
    btn.style.borderRadius = "8px";
    btn.style.cursor = "pointer";

    btn.onclick = () => overlay.remove();

    modal.appendChild(texto);
    modal.appendChild(btn);
    overlay.appendChild(modal);
    document.body.appendChild(overlay);
}

// Mostrar habitaciones

function mostrarHabitaciones() {
    const tabla = document.querySelector("#habitacionesTable tbody");
    tabla.innerHTML = "";

    habitaciones.forEach(h => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
            <td>${h.numero}</td>
            <td>${h.tipo}</td>
            <td>${h.precio}</td>
            <td>${h.estado}</td>
        `;
        tabla.appendChild(fila);
    });
}

mostrarHabitaciones();

// Evento del formulario

document.getElementById("habitacionForm").addEventListener("submit", e => {
    e.preventDefault();

    const habitacion = {
        numero: document.getElementById("numero").value,
        tipo: document.getElementById("tipo").value,
        precio: document.getElementById("precio").value,
        estado: document.getElementById("estado").value
    };

    // Validación con modal
    if (!habitacion.numero || !habitacion.tipo || !habitacion.precio) {
        crearModal("Completa todos los campos obligatorios.", "error");
        return;
    }

    habitaciones.push(habitacion);
    localStorage.setItem("habitaciones", JSON.stringify(habitaciones));

    mostrarHabitaciones();
    e.target.reset();

    // Modal de éxito
    crearModal("Habitación registrada exitosamente.", "exito");
});
