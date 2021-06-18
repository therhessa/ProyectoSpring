package es.iesrafaelalberti.daw.dwes.proyecto.repositories;

import es.iesrafaelalberti.daw.dwes.proyecto.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
