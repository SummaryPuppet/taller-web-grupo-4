package pe.edu.utp.service;

import pe.edu.utp.dto.HotelDTO;
import pe.edu.utp.modelo.Empleado;
import pe.edu.utp.modelo.Hotel;

import java.util.List;

public interface HotelService {
    Hotel crearHotel(HotelDTO hotel);

    Hotel buscarHotelPorId(Long id);

    List<Hotel> listarHoteles();

    void asignarEmpleado(Long hotelId, Empleado empleado);
}