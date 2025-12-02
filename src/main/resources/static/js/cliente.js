document.addEventListener("DOMContentLoaded", () => {
    const filas = document.querySelectorAll("table tbody tr");

    filas.forEach(fila => {

        fila.addEventListener("mouseenter", () => {
            fila.style.background = "rgba(255, 255, 255, 0.15)";
            fila.style.transition = "0.25s";
        });

        fila.addEventListener("mouseleave", () => {
            if (!fila.classList.contains("seleccionada")) {
                fila.style.background = "transparent";
            }
        });

        fila.addEventListener("click", () => {
            // Quitar selección de otras filas
            filas.forEach(f => {
                f.classList.remove("seleccionada");
                f.style.background = "transparent";
            });

            // Marcar fila actual
            fila.classList.add("seleccionada");
            fila.style.background = "rgba(255, 0, 13, 0.22)";
        });
    });
});



document.addEventListener("DOMContentLoaded", () => {
    const filas = Array.from(document.querySelectorAll("tbody tr"));
    const registrosPorPagina = 10;
    let paginaActual = 1;
    const totalPaginas = Math.ceil(filas.length / registrosPorPagina);

    function mostrarPagina(numero) {
        paginaActual = numero;
        const inicio = (numero - 1) * registrosPorPagina;
        const fin = inicio + registrosPorPagina;

        filas.forEach((fila, index) => {
            fila.style.display = (index >= inicio && index < fin) ? "" : "none";
        });

        actualizarNumeros();
    }

    function actualizarNumeros() {
        const cont = document.getElementById("numerosPaginas");
        cont.innerHTML = "";

        for (let i = 1; i <= totalPaginas; i++) {
            const btn = document.createElement("button");
            btn.textContent = i;
            if (i === paginaActual) btn.classList.add("activo");
            btn.addEventListener("click", () => mostrarPagina(i));
            cont.appendChild(btn);
        }
    }

    window.primeraPagina = () => mostrarPagina(1);
    window.paginaAnterior = () => {
        if (paginaActual > 1) mostrarPagina(paginaActual - 1);
    };
    window.paginaSiguiente = () => {
        if (paginaActual < totalPaginas) mostrarPagina(paginaActual + 1);
    };
    window.ultimaPagina = () => mostrarPagina(totalPaginas);

    // Mostrar desde la página 1 al cargar
    mostrarPagina(1);
});


window.addEventListener("DOMContentLoaded", function () {
    const cuerpoTabla = document.getElementById("tablaClientes");

    // Leer clientes guardados
    let clientes = JSON.parse(localStorage.getItem("clientes")) || [];

    clientes.forEach(c => {
        const fila = document.createElement("tr");


        fila.innerHTML = `
            <td>${c.nombre}</td>
            <td>${c.apellidoPaterno}</td>
            <td>${c.apellidoMaterno}</td>
            <td>${c.nacionalidad}</td>
            <td>${c.genero}</td>
            <td>${c.dni}</td>
        `;

        cuerpoTabla.appendChild(fila);
    });
});