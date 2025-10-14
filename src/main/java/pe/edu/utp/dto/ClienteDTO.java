package pe.edu.utp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String nacionalidad;
    private String genero;
    private String dni;
}
