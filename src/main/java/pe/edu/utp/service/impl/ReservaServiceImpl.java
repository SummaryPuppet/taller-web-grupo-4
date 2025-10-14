package pe.edu.utp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.dto.ReservaDTO;
import pe.edu.utp.modelo.Cliente;
import pe.edu.utp.modelo.Habitacion;
import pe.edu.utp.modelo.Reserva;
import pe.edu.utp.repository.ClienteRepository;
import pe.edu.utp.repository.HabitacionRepository;
import pe.edu.utp.repository.ReservaRepository;
import pe.edu.utp.service.ReservaService;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Override
    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva crearReservaDTO(ReservaDTO reservaDTO) {
        Cliente cliente = clienteRepository.findByDni(reservaDTO.getDniCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Habitacion habitacion = habitacionRepository.findByNumero(reservaDTO.getNumeroHabitacion())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setHabitacion(habitacion);
        reserva.setFechaInicio(reservaDTO.getFechaInicio());
        reserva.setFechaFin(reservaDTO.getFechaFin());
        reserva.setReservaActiva(true);

        return reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> listarReservasActivas() {
        return reservaRepository.findByReservaActiva(Boolean.TRUE);
    }

    @Override
    public void cancelarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}