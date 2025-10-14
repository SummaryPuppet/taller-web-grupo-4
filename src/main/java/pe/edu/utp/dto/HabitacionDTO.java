package pe.edu.utp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionDTO {

    private String numero;
    private String tipo;
    private Double precio;
    private Boolean ocupada;
}
