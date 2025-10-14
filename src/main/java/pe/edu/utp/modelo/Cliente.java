package pe.edu.utp.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * Representa un cliente en el sistema.
 *
 * <p>Hereda los atributos comunes de la clase {@link Persona}.
 * Incluye un identificador único (ID) generado automáticamente.</p>
 *
 * @author Adrian, Nilton, Alexander
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Persona {
    /**
     * Identificador único del cliente.
     * Es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Cliente(String nombre, String apellidoP, String apellidoM, String nacionalidad, String genero, String dni ) {
        super(nombre, apellidoP, apellidoM, nacionalidad, genero, dni);
    }

    /**
     * Obtiene el identificador del cliente.
     *
     * @return el ID del cliente
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del cliente.
     *
     * @param id el nuevo ID del cliente
     */
    public void setId(Long id) {
        this.id = id;
    }
}
