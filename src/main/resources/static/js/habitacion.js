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
