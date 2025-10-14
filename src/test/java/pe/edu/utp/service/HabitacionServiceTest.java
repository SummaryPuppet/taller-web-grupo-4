package pe.edu.utp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.edu.utp.dto.HabitacionDTO;
import pe.edu.utp.modelo.Habitacion;
import pe.edu.utp.repository.HabitacionRepository;
import pe.edu.utp.service.impl.HabitacionServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HabitacionServiceTest {
    @Mock
    private HabitacionRepository habitacionRepository;

    @InjectMocks
    private HabitacionServiceImpl habitacionService;

    private Habitacion habitacion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        habitacion = new Habitacion("101", "individual", 100.0);
    }

    @Test
    void listarTodas() {
        List<Habitacion> habitaciones = List.of(habitacion);
        when(habitacionRepository.findAll()).thenReturn(habitaciones);

        List<Habitacion> result = habitacionService.listarTodas();

        assertFalse(result.isEmpty());
        assertEquals("101", result.get(0).getNumero());
    }

    @Test
    void buscarPorNumero() {
        when(habitacionRepository.findByNumero("101")).thenReturn(Optional.of(habitacion));

        Optional<Habitacion> result = habitacionService.buscarPorNumero("101");

        assertTrue(result.isPresent());
        assertEquals("individual", result.get().getTipo());
    }

    @Test
    void guardar() {
        HabitacionDTO habitacionDTO = HabitacionDTO.builder()
                .tipo("Simple")
                .numero("101")
                .precio(100.0)
                .ocupada(false)
                .build();

        Habitacion habitacionEntity = Habitacion.builder()
                .tipo("Simple")
                .numero("101")
                .precio(100.0)
                .ocupada(false)
                .build();

        when(habitacionRepository.save(any(Habitacion.class))).thenReturn(habitacionEntity);

        Habitacion result = habitacionService.guardar(habitacionDTO);

        assertNotNull(result);
        assertEquals("101", result.getNumero());
        assertEquals("Simple", result.getTipo());
        assertEquals(100.0, result.getPrecio());
        assertFalse(result.isOcupada());
    }

    @Test
    void eliminar() {
        habitacion.setId(1L);
        habitacionService.eliminar(1L);
        verify(habitacionRepository, times(1)).deleteById(1L);
    }

    @Test
    void reservar() {
        habitacion.setOcupada(false);
        when(habitacionRepository.findByNumero("101")).thenReturn(Optional.of(habitacion));
        when(habitacionRepository.save(any(Habitacion.class))).thenReturn(habitacion);

        Habitacion result = habitacionService.reservar("101");

        assertNotNull(result);
        assertTrue(result.isOcupada());
    }

    @Test
    void liberar() {
        habitacion.setOcupada(true);
        when(habitacionRepository.findByNumero("101")).thenReturn(Optional.of(habitacion));
        when(habitacionRepository.save(any(Habitacion.class))).thenReturn(habitacion);

        Habitacion result = habitacionService.liberar("101");

        assertNotNull(result);
        assertFalse(result.isOcupada());
    }

    @AfterEach
    void tearDown() {
        habitacionRepository.deleteAll();
    }
}
