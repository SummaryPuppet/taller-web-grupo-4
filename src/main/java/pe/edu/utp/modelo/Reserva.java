package pe.edu.utp.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

/**
 * Representa una reserva realizada por un cliente para una habitación específica.
 *
 * <p>Contiene información sobre las fechas de inicio y fin de la reserva,
 * así como su estado actual.</p>
 *
 * <p>Esta clase es una entidad JPA para persistencia en base de datos.</p>
 *
 * @author Adrian, Nilton, Alexander
 */
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reserva {
    /**
     * Identificador único de la reserva (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cliente que realiza la reserva.
     *
     * <p>Relación uno a uno con la entidad {@link Cliente}.</p>
     */
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    /**
     * Habitación reservada.
     *
     * <p>Relación uno a uno con la entidad {@link Habitacion}.</p>
     */
    @OneToOne
    @JoinColumn(name = "habitacion_numero")
    private Habitacion habitacion;

    /**
     * Fecha y hora de inicio de la reserva.
     */
    private LocalDateTime fechaInicio;

    /**
     * Fecha y hora de fin de la reserva.
     */
    private LocalDateTime fechaFin;

    /**
     * Indica si la reserva está activa o no.
     */
    private Boolean reservaActiva;
}
