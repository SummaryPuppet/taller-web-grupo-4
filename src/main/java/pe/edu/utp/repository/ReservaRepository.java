package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.modelo.Reserva;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByHabitacionId(Long habitacionId);

    List<Reserva> findByReservaActiva(Boolean reservaActiva);
}