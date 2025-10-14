package pe.edu.utp.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un empleado del sistema.
 *
 * <p>Hereda los atributos generales de {@link Persona}, como nombre,
 * apellidos, nacionalidad, género y DNI.</p>
 *
 * <p>Incluye información específica del empleado como un identificador
 * de base de datos, un código de empleado y su trabajo actual.</p>
 *
 * @author Adrian, Nilton, Alexander
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Empleado extends Persona {
    /**
     * Identificador único del empleado (clave primaria en la base de datos).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Código único del empleado (por ejemplo, número de legajo o interno).
     */
    private String idEmpleado;
    /**
     * Puesto o rol que desempeña el empleado en la organización.
     */
    private String trabajo;

    /**
     * Constructor completo que incluye atributos heredados y propios del empleado.
     *
     * @param nombre       Nombre del empleado
     * @param apellidoP    Apellido paterno del empleado
     * @param apellidoM    Apellido materno del empleado
     * @param nacionalidad Nacionalidad del empleado
     * @param genero       Género del empleado
     * @param dni          Documento de identidad del empleado
     * @param idEmpleado   Código del empleado asignado por la empresa
     * @param trabajo      Puesto o cargo del empleado
     */
    public Empleado(String nombre, String apellidoP, String apellidoM, String nacionalidad, String genero, String dni, String idEmpleado, String trabajo) {
        super(nombre, apellidoP, apellidoM, nacionalidad, genero, dni);
        this.idEmpleado = idEmpleado;
        this.trabajo = trabajo;
    }
}
