package pe.edu.utp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.edu.utp.dto.EmpleadoDTO;
import pe.edu.utp.modelo.Empleado;
import pe.edu.utp.repository.EmpleadoRepository;
import pe.edu.utp.service.impl.EmpleadoServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmpleadoServiceTest {
    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        empleado = new Empleado("Carlos", "Pérez", "Guerrero", "peruano", "masculino", "1234567890",  "R-501", "Recepcionista");
    }

    @Test
    void registrarEmpleado() {
        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
                .dni("1234567890")
                .nombre("María")
                .apellidoP("Guerrero")
                .apellidoM("Perez")
                .genero("masculino")
                .nacionalidad("peruano")
                .trabajo("Recepcionista")
                .idEmpleado("R-501")
                .build();

        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleado);

        Empleado result = empleadoService.registrarEmpleado(empleadoDTO);

        assertNotNull(result);
        assertEquals("1234567890", result.getDni());
    }

    @Test
    void obtenerEmpleadoPorDni() {
        when(empleadoRepository.findByDni("1234567890")).thenReturn(Optional.of(empleado));

        Empleado result = empleadoService.obtenerEmpleadoPorDni("1234567890");

        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
    }

    @Test
    void listarEmpleados() {
        List<Empleado> empleados = List.of(empleado);
        when(empleadoRepository.findAll()).thenReturn(empleados);

        List<Empleado> result = empleadoService.listarEmpleados();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
