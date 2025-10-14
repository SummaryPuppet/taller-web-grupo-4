package pe.edu.utp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.edu.utp.modelo.Cliente;
import pe.edu.utp.modelo.Habitacion;
import pe.edu.utp.modelo.Reserva;
import pe.edu.utp.repository.ReservaRepository;
import pe.edu.utp.service.impl.ReservaServiceImpl;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ReservaServiceTest {
    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    private Reserva reserva;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Cliente cliente = new Cliente("Luis", "Mendoza", "García", "Colombiano", "masculino", "101010101");

        Habitacion habitacion = new Habitacion("201", "doble", 150.0, true);

        reserva = new Reserva(1l, cliente, habitacion, LocalDateTime.now(), LocalDateTime.now().plusDays(3), true);
    }

    @Test
    void crearReserva() {
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva result = reservaService.crearReserva(reserva);

        assertNotNull(result);
        assertTrue(result.getReservaActiva());
    }

}
