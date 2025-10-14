package pe.edu.utp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.utp.modelo.Cliente;
import pe.edu.utp.modelo.Habitacion;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {
    private String dniCliente;
    private String numeroHabitacion;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
