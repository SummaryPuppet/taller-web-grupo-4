package pe.edu.utp.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pe.edu.utp.modelo.Empleado;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmpleadoRepositoryTest {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    private Empleado empleado;

    @BeforeEach
    void setup() {
        empleado = new Empleado("Carlos", "Pérez", "Guerrero", "peruano", "masculino", "1234567890",  "R-501", "Recepcionista");
    }

    @Test
    void guardarEmpleado() {
        Empleado guardado = empleadoRepository.save(empleado);

        assertNotNull(guardado);
        assertNotNull(guardado.getId());
        assertEquals("1234567890", guardado.getDni());
    }

    @Test
    void findByDni() {
        empleadoRepository.save(empleado);
        Optional<Empleado> result = empleadoRepository.findByDni("1234567890");

        assertTrue(result.isPresent());
        assertEquals("Carlos", result.get().getNombre());
    }


    @AfterEach
    void tearDown() {
        empleadoRepository.deleteAll();
    }
}
