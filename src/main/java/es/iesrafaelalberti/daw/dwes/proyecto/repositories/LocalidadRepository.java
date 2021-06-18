package es.iesrafaelalberti.daw.dwes.proyecto.repositories;

import es.iesrafaelalberti.daw.dwes.proyecto.model.Localidad;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Proyecto;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Trabajador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public  interface LocalidadRepository extends CrudRepository<Localidad,Long> {

}