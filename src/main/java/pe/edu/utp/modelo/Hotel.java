package pe.edu.utp.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Representa un hotel dentro del sistema de gestión.
 *
 * <p>Contiene información general como el nombre del hotel y listas
 * de habitaciones, empleados y reservas asociadas.</p>
 *
 * <p>Esta clase está anotada como entidad JPA para su persistencia
 * en base de datos.</p>
 *
 * @author Adrian, Nilton, Alexander
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {
    /**
     * Identificador único del hotel (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nombre del hotel.
     */
    private String nombre;

    /**
     * Lista de habitaciones disponibles en el hotel.
     *
     * <p>Se mapea con una relación de uno a muchos. El uso de
     * {@code CascadeType.ALL} indica que las operaciones realizadas
     * sobre el hotel también se aplicarán a sus habitaciones.</p>
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones;

    /**
     * Lista de empleados que trabajan en el hotel.
     *
     * <p>Relación de uno a muchos. El uso de cascada completa permite
     * gestionar automáticamente los empleados relacionados.</p>
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Empleado> empleados;

    /**
     * Lista de reservas realizadas en el hotel.
     *
     * <p>Inicializada como lista vacía por defecto en el constructor
     * personalizado.</p>
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    /**
     * Constructor personalizado que permite crear un hotel con nombre,
     * habitaciones y empleados. La lista de reservas se inicializa vacía.
     *
     * @param nombre      Nombre del hotel
     * @param habitaciones Lista de habitaciones del hotel
     * @param empleados   Lista de empleados del hotel
     */
    public Hotel(String nombre, List<Habitacion> habitaciones, List<Empleado> empleados) {
        this.nombre = nombre;
        this.habitaciones = habitaciones;
        this.empleados = empleados;
        this.reservas = new ArrayList<>();
    }
}
