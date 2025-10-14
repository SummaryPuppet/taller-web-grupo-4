package pe.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.modelo.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel getHotelById(Long id);
}