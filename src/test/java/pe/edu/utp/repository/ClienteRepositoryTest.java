package pe.edu.utp.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pe.edu.utp.modelo.Cliente;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository repo;
    private Cliente c1;
    private Cliente c2;
    private List<Cliente> clientes;

    @BeforeEach
    void setup (){
        this.c1 = new Cliente("Alexander", "Jhon", "Andrew", "Estadounidense", "masculino", "7814542");
        this.c2 = new Cliente("Maria", "Guerrero", "Gonzales", "Venezolana", "femenino", "79452855");
        this.clientes = List.of(c1, c2);
    }

    @Test
    void registrarUno(){
       Cliente result = repo.save(c1);

       assertNotNull(result);
       assertEquals(1l, result.getId());
    }

    @Test
    void registrarVarios(){
        List<Cliente> result = repo.saveAll(this.clientes);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Maria", result.get(1).getNombre());
    }

    @AfterEach
    void tearDown() {
        repo.deleteAll();
    }

}
