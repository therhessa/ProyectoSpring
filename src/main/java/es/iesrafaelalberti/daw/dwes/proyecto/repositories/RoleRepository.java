package es.iesrafaelalberti.daw.dwes.proyecto.repositories;

import es.iesrafaelalberti.daw.dwes.proyecto.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query("Select r from Role r JOIN FETCH r.users where r.id = :id")
    Role findUsers(@Param("id") Long id);
}
