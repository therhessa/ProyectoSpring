package es.iesrafaelalberti.daw.dwes.proyecto.repositories;

import es.iesrafaelalberti.daw.dwes.proyecto.model.Proyecto;
import org.apache.catalina.LifecycleState;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  ProyectoRepository  extends CrudRepository<Proyecto,Long> {

   @Query(value = "SELECT  h FROM  Proyecto h")
   List<Proyecto> findAllHoras(Sort sort);
   /*
   @Query("select MAX(p.horas)from Proyecto p ")
   List<Proyecto> findMax();
   */
   @Query("SELECT t FROM Proyecto t JOIN FETCH t.trabajproyecto")
   List<Proyecto> findAllt();


}
