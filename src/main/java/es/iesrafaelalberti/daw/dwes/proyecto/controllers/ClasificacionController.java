package es.iesrafaelalberti.daw.dwes.proyecto.controllers;

import es.iesrafaelalberti.daw.dwes.proyecto.model.Localidad;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Proyecto;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Trabajador;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.LocalidadRepository;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.ProyectoRepository;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class ClasificacionController {
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private TrabajadorRepository trabajadorRepository;
    @Autowired
    private LocalidadRepository localidadRepository;

    @GetMapping(value = "/clasificacion")
    public ResponseEntity<Object> horasList() {
        return new ResponseEntity<>(proyectoRepository.findAllHoras(Sort.by(Sort.Direction.DESC, "horas")), HttpStatus.OK);
    }

    @GetMapping(value = "/clasificacion/listaP")
    public ResponseEntity<Object> trabproList() {
        return new ResponseEntity<>(trabajadorRepository.findAlltrabajador(JpaSort.unsafe("LENGTH(name)")), HttpStatus.OK);
    }
    @GetMapping(value = "/clasificacion/listado")
    public ResponseEntity<Object> pList() {
        return new ResponseEntity<>(proyectoRepository.findAllt(), HttpStatus.OK);
    }
    @GetMapping(value = "/clasificacion/listadop")
    public ResponseEntity<Object> pListp() {
        return new ResponseEntity<>(trabajadorRepository.findtrabaproyec(), HttpStatus.OK);
    }

    @GetMapping(value = "/clasificacion/{name}")
    public ResponseEntity<Object> Localidadtraba(@PathVariable("name")  String name){
        return new ResponseEntity<>(new Object[]{trabajadorRepository.findAllByLocalidad(name)},HttpStatus.OK);

    }
    @GetMapping(value = "/clasificacionp/{name}")
    public ResponseEntity<Object>Proyectotraba(@PathVariable("name")  String name){
        return new ResponseEntity<>(new Object[]{trabajadorRepository.findAllByProyecto(name)},HttpStatus.OK);

    }
    @GetMapping(value = "/clasificaciont/{name}")
    public ResponseEntity<Object>Titulaciontraba(@PathVariable("name")  String name){
        return new ResponseEntity<>(new Object[]{trabajadorRepository.findAllByTitulacion(name)},HttpStatus.OK);

    }

    //DUDA

    //@GetMapping(value = "/clasification/{localidad}")
   // public ResponseEntity<?> trabajproyecto(@PathVariable("localidad") String localidad){
      //  return new ResponseEntity<>(trabajadorRepository.finddatos(localidad), HttpStatus.OK);
   // }

    /*
    @GetMapping(value = "/clasificacion/maximo")
    public ResponseEntity<Object> maxhorasproyecto() {
        return new ResponseEntity<>(proyectoRepository.findMax(), HttpStatus.OK);
    }
    */

    /*
    @GetMapping(value = "/clasificacion/{id}")
    public ResponseEntity<Object> proyectoTraList(@PathVariable("id") Long id) {
        return new ResponseEntity<>(trabajadorRepository.proyectotrabajador(id), HttpStatus.OK);
    }
   */
   // @GetMapping("/clasificacion/{localidad}")
   // public ResponseEntity<Object> localidadtrab(@PathVariable("localidad")Localidad localidad) {
    //    return new ResponseEntity<>(trabajadorRepository.findAllByLocalidad(localidad), HttpStatus.OK);
   // }
   // @GetMapping("/clasificacion/{localidad}")
   // public ResponseEntity<Object> localidadtrab(@PathVariable("localidad")Localidad localidad) {
    //    return new ResponseEntity<>(trabajadorRepository.findAllByLocalidad(localidad), HttpStatus.OK);

    //}
    /*
    @GetMapping("/clasificacion/listartrab")
    public ResponseEntity<Object> trabList() {
        return new ResponseEntity<>(trabajadorRepository.findname("name","surname"), HttpStatus.OK);
    }
*/
     /*
    public ResponseEntity<Object> trabajadorList() {
        //return new ResponseEntity<>(trabajadorRepository.findById(Sort.by(Sort.Direction.DESC,"localidad")), HttpStatus.OK);

      */

    /*
    @GetMapping("/clasificacion")
    public ResponseEntity<Object> trabajadorproyecto() {
        return new ResponseEntity<>(trabajadorRepository.findAllproyecto((Sort.by(Sort.Direction.DESC, "proyecto"))), HttpStatus.OK);


    }
    */

}














