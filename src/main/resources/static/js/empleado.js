let empleados = JSON.parse(localStorage.getItem("empleados")) || [];

function mostrarEmpleados() {
    const tablaBody = document.querySelector("#empleadosTable tbody");

    if (!tablaBody) return;

    tablaBody.innerHTML = "";

    empleados.forEach(emp => {
        const fila = document.createElement("tr");

        fila.innerHTML = `
            <td>${emp.nombre}</td>
            <td>${emp.apellidoP}</td>
            <td>${emp.apellidoM}</td>
            <td>${emp.nacionalidad}</td>
            <td>${emp.genero}</td>
            <td>${emp.dni}</td>
            <td>${emp.cargo}</td>
        `;

        tablaBody.appendChild(fila);
    });
}

mostrarEmpleados();


document.getElementById("btn-registrar")?.addEventListener("click", (e) => {
    e.preventDefault();

    const empleado = {
        nombre: document.getElementById("nombre").value,
        apellidoP: document.getElementById("apellidoP").value,
        apellidoM: document.getElementById("apellidoM").value,
        nacionalidad: document.getElementById("nacionalidad").value,
        genero: document.getElementById("genero").value,
        dni: document.getElementById("dni").value,
        cargo: document.getElementById("trabajo").value,
        foto: ""
    };

    if (!empleado.nombre || !empleado.apellidoP || !empleado.dni) {
        alert("Completa los campos obligatorios");
        return;
    }

    empleados.push(empleado);
    localStorage.setItem("empleados", JSON.stringify(empleados));

    mostrarEmpleados();
    document.getElementById("registroForm").reset();
});

document.getElementById("btn-limpiar")?.addEventListener("click", () => {
    document.getElementById("registroForm").reset();
});