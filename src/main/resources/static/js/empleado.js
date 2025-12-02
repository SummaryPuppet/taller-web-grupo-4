// Cargar datos guardados
let empleados = JSON.parse(localStorage.getItem("empleados")) || [];

const form = document.getElementById("employeeForm");
const tableBody = document.getElementById("employeeTable");

// Mostrar empleados
function mostrarEmpleados() {
    tableBody.innerHTML = "";

    empleados.forEach((emp, index) => {
        const fila = `
            <tr>
                <td>${emp.nombre}</td>
                <td>${emp.dni}</td>
                <td>${emp.cargo}</td>
                <td>
                    <button class="delete-btn" onclick="eliminarEmpleado(${index})">Eliminar</button>
                </td>
            </tr>
        `;
        tableBody.innerHTML += fila;
    });
}

// Registrar empleado
form.addEventListener("submit", (e) => {
    e.preventDefault();

    const nuevoEmpleado = {
        nombre: document.getElementById("nombre").value,
        dni: document.getElementById("dni").value,
        cargo: document.getElementById("cargo").value
    };

    empleados.push(nuevoEmpleado);
    localStorage.setItem("empleados", JSON.stringify(empleados));

    form.reset();
    mostrarEmpleados();
});

// Eliminar
function eliminarEmpleado(i) {
    empleados.splice(i, 1);
    localStorage.setItem("empleados", JSON.stringify(empleados));
    mostrarEmpleados();
}

// ----------------------
// BÚSQUEDA DEL SELECT
// ----------------------

const searchInput = document.getElementById("cargoSearch");
const selectCargo = document.getElementById("cargo");

searchInput.addEventListener("keyup", () => {
    const filter = searchInput.value.toLowerCase();

    for (let i = 0; i < selectCargo.options.length; i++) {
        let txt = selectCargo.options[i].text.toLowerCase();

        selectCargo.options[i].style.display =
            txt.includes(filter) ? "" : "none";
    }
});

mostrarEmpleados();
