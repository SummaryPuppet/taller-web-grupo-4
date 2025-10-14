package pe.edu.utp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class HotelDTO {
    private String nombre;
    private List<HabitacionDTO> habitaciones;
}
