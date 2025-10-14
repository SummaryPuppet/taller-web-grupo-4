package pe.edu.utp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.dto.HotelDTO;
import pe.edu.utp.modelo.Empleado;
import pe.edu.utp.modelo.Hotel;
import pe.edu.utp.repository.HotelRepository;
import pe.edu.utp.service.HotelService;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel crearHotel(HotelDTO hotel) {
        Hotel obj = new Hotel();

        obj.setNombre(hotel.getNombre());
        obj.setEmpleados(List.of());
        obj.setHabitaciones(List.of());
        obj.setReservas(List.of());

        return hotelRepository.save(obj);
    }

    @Override
    public Hotel buscarHotelPorId(Long id) {
        return hotelRepository.getHotelById(id);
    }


    @Override
    public List<Hotel> listarHoteles() {
        return hotelRepository.findAll();
    }

    @Override
    public void asignarEmpleado(Long hotelId, Empleado empleado) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado"));
        hotel.getEmpleados().add(empleado);
        hotelRepository.save(hotel);
    }
}