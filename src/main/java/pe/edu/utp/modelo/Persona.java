package pe.edu.utp.modelo;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

/**
 * Clase base abstracta que representa los datos personales comunes
 * a distintas entidades como {@code Cliente} y {@code Empleado}.
 *
 * <p>Esta clase está anotada con {@code @MappedSuperclass}, lo que
 * significa que sus atributos se heredarán en las entidades hijas
 * y se incluirán en las tablas correspondientes.</p>
 *
 * <p>Utiliza Lombok para generar automáticamente constructores,
 * getters, setters, y otros métodos estándar.</p>
 *
 * @author Adrian, Nilton, Alexander
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Persona {
    /**
     * Nombre de la persona.
     */
    protected String nombre;

    /**
     * Apellido paterno de la persona.
     */
    protected String apellidoP;

    /**
     * Apellido materno de la persona.
     */
    protected String apellidoM;

    /**
     * Nacionalidad de la persona.
     */
    protected String nacionalidad;

    /**
     * Género de la persona (por ejemplo: "Masculino", "Femenino", etc.).
     */
    protected String genero;

    /**
     * Documento Nacional de Identidad o equivalente.
     */
    protected String dni;
}
