package es.iesrafaelalberti.daw.dwes.proyecto.repositories;

import es.iesrafaelalberti.daw.dwes.proyecto.model.Localidad;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Proyecto;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Role;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Trabajador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TrabajadorRepository extends CrudRepository<Trabajador,Long> {

    @Query("Select t from Trabajador t JOIN Localidad l on l.id= t.localidad.id where l.name Like CONCAT('%',:name,'%') ")
    List<Trabajador> findAllByLocalidad(@Param("name") String name);
    @Query("Select t from Trabajador t JOIN Proyecto p on p.id= t.proyecto.id where p.name Like CONCAT('%',:name,'%') ")
    List<Trabajador> findAllByProyecto(@Param("name") String name);
    @Query("Select t from Trabajador t JOIN Titulacion titu on titu.id= t.titulacion.id where titu.name Like CONCAT('%',:name,'%') ")
    List<Trabajador> findAllByTitulacion(@Param("name") String name);

    @Query(value = "SELECT t FROM Trabajador t JOIN FETCH t.proyecto a")
    List<Trabajador> findtrabaproyec();

    @Query(value = "SELECT  t FROM  Trabajador t")
    List<Trabajador> findAlltrabajador(Sort sort);



}






