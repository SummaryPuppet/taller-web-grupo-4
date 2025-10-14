package pe.edu.utp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.dto.HabitacionDTO;
import pe.edu.utp.modelo.Habitacion;
import pe.edu.utp.repository.HabitacionRepository;
import pe.edu.utp.service.HabitacionService;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServiceImpl implements HabitacionService {
    @Autowired
    private HabitacionRepository repository;

    @Override
    public List<Habitacion> listarTodas() {
        return repository.findAll();
    }

    @Override
    public Optional<Habitacion> buscarPorNumero(String numero) {
        return repository.findByNumero(numero);
    }

    @Override
    public Habitacion guardar(HabitacionDTO habitacion) {
        Habitacion obj =  Habitacion.builder()
                .tipo(habitacion.getTipo())
                .numero(habitacion.getNumero())
                .precio(habitacion.getPrecio())
                .ocupada(habitacion.getOcupada())
                .build();
        return repository.save(obj);
    }

    @Override
    public void eliminar(Long numero) {
        repository.deleteById(numero);
    }

    @Override
    public Habitacion reservar(String numero) {
        Habitacion h = repository.findByNumero(numero)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        if (h.isOcupada()) {
            throw new RuntimeException("La habitación ya está ocupada");
        }
        h.setOcupada(true);
        return repository.save(h);
    }

    @Override
    public Habitacion liberar(String numero) {
        Habitacion h = repository.findByNumero(numero)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        if (!h.isOcupada()) {
            throw new RuntimeException("La habitación ya está libre");
        }
        h.setOcupada(false);
        return repository.save(h);
    }

}
