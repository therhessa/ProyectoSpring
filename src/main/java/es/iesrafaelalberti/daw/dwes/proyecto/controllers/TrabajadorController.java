package es.iesrafaelalberti.daw.dwes.proyecto.controllers;

import es.iesrafaelalberti.daw.dwes.proyecto.model.Localidad;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Proyecto;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Titulacion;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Trabajador;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.LocalidadRepository;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.ProyectoRepository;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.Set;

@RestController
public class TrabajadorController {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @GetMapping(value  = "/trabajadores")
    public ResponseEntity<Object> trabajadorList() {
        ResponseEntity<Object> t = new ResponseEntity<>(trabajadorRepository.findAll(), HttpStatus.OK);
        return new ResponseEntity<>(trabajadorRepository.findAll(), HttpStatus.OK);
    }
    //revisar
    @GetMapping("/trabajadores/detail")
    public ResponseEntity<Object> trabajadorDetail(@RequestParam("id") Long id){
        //asi controlamos la gestion de errores
        Trabajador trabajador= trabajadorRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        return new ResponseEntity<>(trabajador, HttpStatus.OK);

       }





    //para dar de alta post
    //recibe por formulario
    @PostMapping("/trabajadores")
    public ResponseEntity<Object>trabajadornadd(@RequestParam("name")String name,
                                                @RequestParam("surname") String surname,
                                                @RequestParam("localidad") Localidad localidad,
                                                @RequestParam("titulacion") Titulacion titulacion,
                                                @RequestParam("proyecto") Proyecto proyecto)
    {
        //para dar de alta en la base e datos añado lo siguiente:
        Trabajador nuevoTrabajador=new Trabajador(name,surname,localidad,titulacion,proyecto);
        trabajadorRepository.save(nuevoTrabajador);
        return new ResponseEntity<>(nuevoTrabajador,HttpStatus.OK);

    }
    //Recibe por json
    //damos de alta en la base de datos
    @PostMapping("/trabajadores/add")
    public ResponseEntity<Object>trabajadorAdd(@RequestBody Trabajador nuevoTrabajador){
        trabajadorRepository.save(nuevoTrabajador);
        return new ResponseEntity<>(nuevoTrabajador,HttpStatus.OK);

    }
    // modificando
    @PutMapping("/trabajadores/{id}")
    public ResponseEntity<Object>trabajadorUpdate(@PathVariable("id") Long id,
                                                  @RequestBody Trabajador nuevoTrabajador) {
        //si no lo encuentra lanza esta excepcion
        trabajadorRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        trabajadorRepository.save(nuevoTrabajador);
        return new ResponseEntity<>(nuevoTrabajador,HttpStatus.OK);



    }

    @DeleteMapping("/trabajadores/{id}")
    public ResponseEntity<Object>trabajadorDelete(@PathVariable("id") Long id) throws Exception{
       Trabajador oldTrabajador=trabajadorRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException());
        trabajadorRepository.delete(oldTrabajador);
        return new ResponseEntity<>(HttpStatus.OK);




    }




}
