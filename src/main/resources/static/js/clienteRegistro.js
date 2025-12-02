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

    setTimeout(() => {
        burbuja.remove();
    }, 6000); // 6 segundos
}

//este es la fucion para limpiar xd
function limpiarFormulario() {
    document.getElementById("registroCliente").reset();
    localStorage.clear();
}

document.getElementById("registroCliente").addEventListener("submit", function (e) {
    e.preventDefault();

    // Datos del formulario
    const cliente = {
        nombre: document.getElementById("nombre").value,
        apellidoPaterno: document.getElementById("apellidoPaterno").value,
        apellidoMaterno: document.getElementById("apellidoMaterno").value,
        nacionalidad: document.getElementById("nacionalidad").value,
        genero: document.getElementById("genero").value,
        dni: document.getElementById("dni").value
    };

    // Obtener lista actual o crear una
    let clientes = JSON.parse(localStorage.getItem("clientes")) || [];

    // Agregar nuevo cliente
    clientes.push(cliente);

    // Guardar en localStorage
    localStorage.setItem("clientes", JSON.stringify(clientes));

    alert("Cliente registrado!");

    // Limpiar formulario
    this.reset();

    // (Opcional) Redirigir
    window.location.href = "/admin/clientes";
});