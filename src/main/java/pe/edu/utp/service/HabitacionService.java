package pe.edu.utp.service;

import pe.edu.utp.dto.HabitacionDTO;
import pe.edu.utp.modelo.Habitacion;

import java.util.List;
import java.util.Optional;

public interface HabitacionService {
    List<Habitacion> listarTodas();

    Optional<Habitacion> buscarPorNumero(String numero);

    Habitacion guardar(HabitacionDTO habitacion);

    void eliminar(Long numero);

    Habitacion reservar(String numero);

    Habitacion liberar(String numero);
}
