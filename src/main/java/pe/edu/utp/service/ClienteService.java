package pe.edu.utp.service;

import pe.edu.utp.dto.ClienteDTO;
import pe.edu.utp.modelo.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente registrarCliente(ClienteDTO cliente);

    Cliente obtenerClientePorDni(String dni);

    List<Cliente> listarClientes();
}
