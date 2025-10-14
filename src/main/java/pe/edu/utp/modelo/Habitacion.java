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
 * Representa una habitación dentro del sistema de gestión de hotel.
 *
 * <p>Incluye información como el número de habitación, tipo, precio
 * y estado de ocupación.</p>
 *
 * <p>Esta clase está mapeada como una entidad JPA para su persistencia
 * en base de datos.</p>
 *
 * @author Adrian, Nilton, Alexander
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Habitacion {
    /**
     * Identificador único de la habitación (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Número de habitación (por ejemplo: "101", "A12").
     */
    private String numero;
    /**
     * Tipo de habitación (por ejemplo: "Individual", "Doble", "Suite").
     */
    private String tipo;
    /**
     * Precio por noche de la habitación.
     */
    private double precio;
    /**
     * Indica si la habitación está actualmente ocupada.
     */
    private boolean ocupada;

    /**
     * Constructor que permite crear una habitación con número, tipo y precio.
     * Por defecto, la habitación se considera desocupada.
     *
     * @param numero Número de la habitación
     * @param tipo   Tipo de habitación
     * @param precio Precio por noche
     */
    public Habitacion(String numero, String tipo, double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.ocupada = false;
    }

    /**
     * Constructor que permite crear una habitación con número, tipo y precio.
     * Por defecto, la habitación se considera desocupada.
     *
     * @param numero Número de la habitación
     * @param tipo   Tipo de habitación
     * @param precio Precio por noche
     * @param ocupada La habitacion actualmente ocupada
     */
    public Habitacion(String numero, String tipo, double precio, boolean ocupada) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.ocupada = ocupada;
    }
}
