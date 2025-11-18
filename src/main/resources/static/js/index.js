/* =========================
   VALIDACIÓN DEL FORMULARIO
========================= */

const inputDestino = document.querySelector(".search-bar input[type='text']");
const inputFecha = document.querySelector(".search-bar input[type='date']");
const inputHuespedes = document.querySelector(".search-bar input[type='number']");
const btnBuscar = document.querySelector(".search-bar button");

// Crear contenedor de mensajes
const msg = document.createElement("p");
msg.style.textAlign = "center";
msg.style.fontWeight = "600";
msg.style.marginTop = "10px";
document.querySelector(".search-bar").appendChild(msg);

btnBuscar.addEventListener("click", () => {
    msg.style.color = "red";

    if (inputDestino.value.trim() === "") {
        msg.textContent = "❌ Debes ingresar un destino.";
        return;
    }

    if (inputFecha.value === "") {
        msg.textContent = "❌ Selecciona una fecha.";
        return;
    }

    const hoy = new Date().toISOString().split("T")[0];
    if (inputFecha.value < hoy) {
        msg.textContent = "❌ La fecha no puede ser anterior a hoy.";
        return;
    }

    if (inputHuespedes.value < 1) {
        msg.textContent = "❌ Debes ingresar al menos 1 huésped.";
        return;
    }

    msg.style.color = "green";
    msg.textContent = "✔ Búsqueda realizada correctamente.";

    filtrarHoteles(inputDestino.value.trim());
});


/* =========================
   FILTRAR HOTELES POR DESTINO
========================= */

const tarjetasHoteles = document.querySelectorAll(".hotel-card");

function filtrarHoteles(destino) {
    const destinoLower = destino.toLowerCase();

    let encontrados = 0;

    tarjetasHoteles.forEach(card => {
        const ubicacion = card.querySelector("p").textContent.toLowerCase();

        if (ubicacion.includes(destinoLower)) {
            card.style.display = "block";
            encontrados++;
        } else {
            card.style.display = "none";
        }
    });

    if (encontrados === 0) {
        msg.style.color = "red";
        msg.textContent = "⚠ No se encontraron hoteles en ese destino.";
    }
}



/* =========================
   MODAL PARA RESERVAS
========================= */

// Crear modal
const modal = document.createElement("div");
modal.style.position = "fixed";
modal.style.top = "0";
modal.style.left = "0";
modal.style.width = "100%";
modal.style.height = "100%";
modal.style.background = "rgba(0,0,0,0.7)";
modal.style.display = "none";
modal.style.justifyContent = "center";
modal.style.alignItems = "center";
modal.style.zIndex = "1000";

modal.innerHTML = `
  <div style="
    background: white;
    padding: 20px;
    border-radius: 12px;
    width: 320px;
    text-align: center;
    animation: fadeIn 0.4s ease;">
    <h2 style="color:#0077b6;">Reserva realizada</h2>
    <p>Tu reserva fue procesada correctamente.</p>
    <button id="cerrarModal" style="
      margin-top: 15px;
      padding: 10px 20px;
      background:#0077b6;
      color:white;
      border:none;
      font-weight:600;
      border-radius:8px;
      cursor:pointer;">
        Cerrar
    </button>
  </div>
`;

document.body.appendChild(modal);

const btnCerrarModal = modal.querySelector("#cerrarModal");

// Mostrar modal al reservar
document.querySelectorAll(".reserve-btn").forEach(btn => {
    btn.addEventListener("click", () => {
        modal.style.display = "flex";
    });
});

// Cerrar modal
btnCerrarModal.addEventListener("click", () => {
    modal.style.display = "none";
});
