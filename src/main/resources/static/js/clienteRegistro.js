window.addEventListener("load", function () {
    // Lista de nombres (puedes poner los que quieras)
    const nombres = ["Adrian", "Alexander", "Pedro", "Ana", "Carlos", "Lucía", "Sofía", "Diego"];

    // GENERAR 3 BURBUJAS
    for (let i = 0; i < 3; i++) {
        const nombreRandom = nombres[Math.floor(Math.random() * nombres.length)];

        // Hacer que no salgan todas juntas (intervalos)
        setTimeout(() => {
            crearBurbuja(`${nombreRandom} se registró`);
        }, i * 1500); // Cada 1.5 segundos aparece una nueva
    }
});

function crearBurbuja(texto) {
    const contenedor = document.getElementById("burbujas-container");

    const burbuja = document.createElement("div");
    burbuja.classList.add("burbuja");
    burbuja.textContent = texto;

    contenedor.appendChild(burbuja);

    // Eliminar después de X segundos (hazlo igual que en CSS)
    setTimeout(() => {
        burbuja.remove();
    }, 6000); // 6 segundos
}

//este es la fucion para limpiar xd
function limpiarFormulario() {
    document.getElementById("registroCliente").reset();
    localStorage.clear();
}