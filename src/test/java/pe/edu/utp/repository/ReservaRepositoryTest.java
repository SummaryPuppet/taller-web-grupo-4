package pe.edu.utp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pe.edu.utp.modelo.Cliente;
import pe.edu.utp.modelo.Habitacion;
import pe.edu.utp.modelo.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReservaRepositoryTest {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    private Cliente cliente;
    private Habitacion habitacion;
    private Reserva reserva;

    @BeforeEach
    void setup() {
        cliente = new Cliente("Luis", "Mendoza", "García", "Colombiano", "masculino", "101010101" );

        habitacion = new Habitacion("201", "doble", 150.0, true);

        reserva = new Reserva(null, cliente, habitacion, LocalDateTime.now(), LocalDateTime.now().plusDays(3), true );

        cliente = clienteRepository.save(cliente);
        habitacion = habitacionRepository.save(habitacion);
    }

    @Test
    void guardarReserva() {
        Reserva guardada = reservaRepository.save(reserva);

        assertNotNull(guardada);
        assertNotNull(guardada.getId());
        assertTrue(guardada.getReservaActiva());
    }

    @Test
    void findByHabitacionId() {
        reservaRepository.save(reserva);
        List<Reserva> resultado = reservaRepository.findByHabitacionId(habitacion.getId());

        assertFalse(resultado.isEmpty());
        assertEquals(habitacion.getId(), resultado.get(0).getHabitacion().getId());
    }

    @Test
    void findByReservaActiva() {
        reservaRepository.save(reserva);
        List<Reserva> resultado = reservaRepository.findByReservaActiva(true);

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.get(0).getReservaActiva());
    }
}
