package pe.edu.utp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String nacionalidad;
    private String genero;
    private String dni;
    private String idEmpleado;
    private String trabajo;
}
