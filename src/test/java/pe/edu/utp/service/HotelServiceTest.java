package pe.edu.utp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.edu.utp.dto.HabitacionDTO;
import pe.edu.utp.dto.HotelDTO;
import pe.edu.utp.modelo.Empleado;
import pe.edu.utp.modelo.Habitacion;
import pe.edu.utp.modelo.Hotel;
import pe.edu.utp.repository.EmpleadoRepository;
import pe.edu.utp.repository.HotelRepository;
import pe.edu.utp.service.impl.HotelServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HotelServiceTest {
    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    private Hotel hotel;
    private Empleado empleado;
    private Habitacion habitacion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        empleado = new Empleado("Carlos", "Pérez", "Guerrero", "peruano", "masculino", "1234567890", "R-501", "Recepcionista");
        habitacion = new Habitacion("101", "individual", 100.0 );
        hotel = new Hotel("Hotel Maravilla", new ArrayList<>(List.of(habitacion)), new ArrayList<>());
    }

    @Test
    void crearHotel() {
        HotelDTO hotelDTO = HotelDTO.builder()
                .nombre("Hotel Maravilla")
                .habitaciones(List.of(
                        HabitacionDTO.builder().numero("101").tipo("Simple").precio(100.0).ocupada(false).build()
                ))
                .build();

        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        Hotel result = hotelService.crearHotel(hotelDTO);

        assertNotNull(result);
        assertEquals("Hotel Maravilla", result.getNombre());
        assertTrue(result.getHabitaciones().size() > 0);
    }

    @Test
    void listarHoteles() {
        List<Hotel> hoteles = List.of(hotel);
        when(hotelRepository.findAll()).thenReturn(hoteles);

        List<Hotel> result = hotelService.listarHoteles();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void asignarEmpleado() {
        hotel.setId(1L);
        empleado.setId(1L);

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        hotelService.asignarEmpleado(1L, empleado);

        assertEquals(1, hotel.getEmpleados().size());
        assertEquals("Carlos", hotel.getEmpleados().get(0).getNombre());
    }
}
