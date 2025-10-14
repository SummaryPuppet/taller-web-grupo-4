package pe.edu.utp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.dto.ClienteDTO;
import pe.edu.utp.modelo.Cliente;
import pe.edu.utp.repository.ClienteRepository;
import pe.edu.utp.service.ClienteService;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente registrarCliente(ClienteDTO cliente) {
        Cliente obj = new Cliente();
        obj.setNombre(cliente.getNombre());
        obj.setApellidoP(cliente.getApellidoP());
        obj.setApellidoM(cliente.getApellidoM());
        obj.setDni(cliente.getDni());
        obj.setNacionalidad(cliente.getNacionalidad());
        obj.setGenero(cliente.getGenero());

        return repository.save(obj);
    }

    @Override
    public Cliente obtenerClientePorDni(String dni) {
        return repository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con DNI: " + dni));
    }

    @Override
    public List<Cliente> listarClientes() {
        return repository.findAll();
    }
}
