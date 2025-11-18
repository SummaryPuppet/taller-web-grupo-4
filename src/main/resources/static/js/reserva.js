// Selección de elementos
const form = document.getElementById("formReserva");
const tbody = document.getElementById("tablaBody");

// Crear modal dinámico
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

    const icon = document.createElement("div");
    icon.style.fontSize = "40px";
    icon.style.marginBottom = "10px";

    const texto = document.createElement("p");
    texto.textContent = mensaje;
    texto.style.marginBottom = "15px";

    const btn = document.createElement("button");
    btn.textContent = "Aceptar";
    btn.style.background = "#2c5282";
    btn.style.color = "white";
    btn.style.border = "none";
    btn.style.padding = "8px 15px";
    btn.style.borderRadius = "8px";
    btn.style.cursor = "pointer";

    btn.onclick = () => overlay.remove();

    modal.appendChild(icon);
    modal.appendChild(texto);
    modal.appendChild(btn);
    overlay.appendChild(modal);
    document.body.appendChild(overlay);
}

// Eventos del formulario
form.addEventListener("submit", function (e) {
    e.preventDefault();

    // Obtener valores
    const nombre = document.getElementById("nombre").value.trim();
    const correo = document.getElementById("correo").value.trim();
    const habitacion = document.getElementById("habitacion").value;
    const llegada = document.getElementById("llegada").value;
    const salida = document.getElementById("salida").value;
    const comentarios = document.getElementById("comentarios").value.trim() || "-";

    // Validación
    if (!nombre || !correo || !habitacion || !llegada || !salida) {
        crearModal("Por favor complete todos los campos obligatorios.", "error");
        return;
    }

    if (salida <= llegada) {
        crearModal("La fecha de salida debe ser posterior a la llegada.", "error");
        return;
    }

    // Crear fila
    const nuevaFila = document.createElement("tr");

    nuevaFila.innerHTML = `
        <td>${nombre}</td>
        <td>${correo}</td>
        <td>${habitacion}</td>
        <td>${llegada}</td>
        <td>${salida}</td>
        <td>${comentarios}</td>
    `;

    // Insertar fila
    tbody.appendChild(nuevaFila);

    // Limpiar inputs
    form.reset();

    // Modal de éxito
    crearModal("Reservación registrada exitosamente.", "exito");
});
