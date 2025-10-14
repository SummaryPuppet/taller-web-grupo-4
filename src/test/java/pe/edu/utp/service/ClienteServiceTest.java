package pe.edu.utp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.edu.utp.dto.ClienteDTO;
import pe.edu.utp.modelo.Cliente;
import pe.edu.utp.repository.ClienteRepository;
import pe.edu.utp.service.impl.ClienteServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente("Alexander", "Jhon", "Andrew", "Estadounidense", "masculino", "7814542" );
    }

    @Test
    void registrarCliente() {
        ClienteDTO clienteDTO = ClienteDTO.builder().build();

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente result = clienteService.registrarCliente(clienteDTO);

        assertNotNull(result);
        assertEquals("7814542", result.getDni());
        assertEquals("Juan", result.getNombre());

        verify(clienteRepository, times(1)).save(any(Cliente.class));

    }

    @Test
    void obtenerClientePorDni() {
        when(clienteRepository.findByDni("7814542")).thenReturn(Optional.of(cliente));

        Cliente result = clienteService.obtenerClientePorDni("7814542");

        assertNotNull(result);
        assertEquals("Jhon", result.getApellidoP());
    }

    @Test
    void listarClientes() {
        List<Cliente> clientes = List.of(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.listarClientes();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
