package pe.edu.utp.service;

import pe.edu.utp.dto.EmpleadoDTO;
import pe.edu.utp.modelo.Empleado;

import java.util.List;

public interface EmpleadoService {
    Empleado registrarEmpleado(EmpleadoDTO empleado);

    Empleado obtenerEmpleadoPorDni(String dni);

    List<Empleado> listarEmpleados();
}