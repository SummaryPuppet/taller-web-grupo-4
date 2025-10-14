package pe.edu.utp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pe.edu.utp.modelo.Habitacion;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class HabitacionRepositoryTest {
    @Autowired
    private HabitacionRepository habitacionRepository;

    private Habitacion habitacion;

    @BeforeEach
    void setup() {
        habitacion = new Habitacion("101", "individual", 100.0);
    }

    @Test
    void guardarHabitacion() {
        Habitacion guardada = habitacionRepository.save(habitacion);

        assertNotNull(guardada);
        assertNotNull(guardada.getId());
        assertEquals("101", guardada.getNumero());
    }

    @Test
    void findByNumero() {
        habitacionRepository.save(habitacion);
        Optional<Habitacion> result = habitacionRepository.findByNumero("101");

        assertTrue(result.isPresent());
        assertEquals("individual", result.get().getTipo());
    }
}
