package pe.edu.utp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pe.edu.utp.modelo.Empleado;
import pe.edu.utp.modelo.Habitacion;
import pe.edu.utp.modelo.Hotel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class HotelRepositoryTest {
    @Autowired
    private HotelRepository hotelRepository;

    private Hotel hotel;
    private Habitacion habitacion;
    private Empleado empleado;

    @BeforeEach
    void setup() {
        habitacion = new Habitacion("101", "individual", 100.0);
        empleado = new Empleado("Carlos", "Pérez", "Guerrero", "peruano", "masculino", "1234567890",  "R-501", "Recepcionista");
        hotel = new Hotel("Hotel Maravilla", new ArrayList<>(List.of(habitacion)), new ArrayList<>());
    }

    @Test
    void guardarHotel() {
        Hotel guardado = hotelRepository.save(hotel);

        assertNotNull(guardado);
        assertNotNull(guardado.getId());
        assertEquals("Hotel Maravilla", guardado.getNombre());
    }
}
