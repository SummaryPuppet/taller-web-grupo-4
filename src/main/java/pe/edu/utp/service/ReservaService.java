package pe.edu.utp.service;

import pe.edu.utp.dto.ReservaDTO;
import pe.edu.utp.modelo.Reserva;

import java.util.List;

public interface ReservaService {
    Reserva crearReserva(Reserva reserva);

    Reserva crearReservaDTO(ReservaDTO reservaDTO);

    List<Reserva> listarReservas();

    List<Reserva> listarReservasActivas();

    void cancelarReserva(Long id);
}