package pe.edu.utp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.dto.EmpleadoDTO;
import pe.edu.utp.modelo.Empleado;
import pe.edu.utp.repository.EmpleadoRepository;
import pe.edu.utp.service.EmpleadoService;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado registrarEmpleado(EmpleadoDTO empleado) {
        Empleado obj = new Empleado();

        obj.setIdEmpleado(empleado.getIdEmpleado());
        obj.setDni(empleado.getDni());
        obj.setNacionalidad(empleado.getNacionalidad());
        obj.setGenero(empleado.getGenero());
        obj.setNombre(empleado.getNombre());
        obj.setApellidoP(empleado.getApellidoP());
        obj.setApellidoM(empleado.getApellidoM());

        return empleadoRepository.save(obj);
    }

    @Override
    public Empleado obtenerEmpleadoPorDni(String dni) {
        return empleadoRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con DNI: " + dni));
    }

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }
}