package es.iesrafaelalberti.daw.dwes.proyecto.controllers;

import es.iesrafaelalberti.daw.dwes.proyecto.model.Localidad;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Proyecto;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Titulacion;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.ProyectoRepository;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@RestController
public class ProyectoController {
    @Autowired
    private ProyectoRepository proyectoRepository;
    //corregido
    @GetMapping(value = "/proyectos")
    public ResponseEntity<Object> proyectsList() {
        return new ResponseEntity<>(proyectoRepository.findAll(), HttpStatus.OK);
    }
    //corregido
    @GetMapping("/proyectos/detail")
    public ResponseEntity<Object> proyectoDetail(@RequestParam("id") Long id){
        //asi controlamos la gestion de errores
        Proyecto proyecto= proyectoRepository.findById(id).orElseThrow(()->new EntityNotFoundException());

        return new ResponseEntity<>(proyecto, HttpStatus.OK);


    }
    //para dar de alta post
    //recibe por formulario
    @PostMapping("/proyectos")
    public ResponseEntity<Object>proyectonadd(@RequestParam("name")String name,
                                              @RequestParam("horas") int horas){
        //para dar de alta en la base de datos añado lo siguiente:
        Proyecto nuevoProyecto=new Proyecto(name,horas);
        proyectoRepository.save(nuevoProyecto);
        return new ResponseEntity<>(nuevoProyecto,HttpStatus.OK);

    }

    //Recibe por json
    //damos de alta en la base de datos
    @PostMapping("/proyectos/add")
    public ResponseEntity<Object>proyectoAdd(@RequestBody Proyecto nuevoProyecto){
        proyectoRepository.save(nuevoProyecto);
        return new ResponseEntity<>(nuevoProyecto,HttpStatus.OK);

    }
    //modifica
    @PutMapping("/proyectos/{id}")
    public ResponseEntity<Object>proyectoUpdate(@PathVariable("id") Long id,
                                                  @RequestBody Proyecto nuevoProyecto) {
        //si no lo encuentra lanza esta excepcion
        proyectoRepository.findById(id).orElseThrow(()->new EntityNotFoundException());

        proyectoRepository.save(nuevoProyecto);
        return new ResponseEntity<>(nuevoProyecto,HttpStatus.OK);



    }

    @DeleteMapping("/proyectos/{id}")
    public ResponseEntity<Object>proyectoDelete(@PathVariable("id") Long id) throws Exception{
        Proyecto oldProyecto=proyectoRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException());
        proyectoRepository.delete(oldProyecto);
        return new ResponseEntity<>(HttpStatus.OK);




    }



}
