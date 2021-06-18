package es.iesrafaelalberti.daw.dwes.proyecto.controllers;

import es.iesrafaelalberti.daw.dwes.proyecto.model.Titulacion;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.TitulacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
//ealiza las tareas de controlador y gestión de la comunicación entre el usuario y la aplicación
@RestController
public class TitulacionController {
    //Anotación: @Autowired
    //
    //paquete: org.springframework.beans.factory.annotation
    //
    //Uso: Nos ayuda a inyectar dependencia. Se puede usar en un campo, en un método setter y/o en un constructor.
    @Autowired
    TitulacionRepository  titulacionRepository;
    // el get para consultas
    @GetMapping("/titulacion")
    public ResponseEntity<Object> titulacionList(){
     //lista de repositorios de titulacion
        return new ResponseEntity<>(titulacionRepository.findAll(), HttpStatus.OK);
    }


    //Get por parametros

    @GetMapping("/titulacion/detail")
    public ResponseEntity<Object> titulacionDetail(@RequestParam("id") Long id){
        //asi controlamos la gestion de errores
        Titulacion titulacion= titulacionRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
       // if(titulacion.isPresent()){
            return new ResponseEntity<>(titulacion, HttpStatus.OK);

        //}
        //return  ResponseEntity.notFound().build();


    }


    //para dar de alta post
    //recibe por formulario
    @PostMapping("/titulacion")
    public ResponseEntity<Object>titulacionadd(@RequestParam("name")String name,
                                               @RequestParam("age") int age){
        //para dar de alta en la base de datos añado lo siguiente:
        Titulacion nuevatitulacion=new Titulacion(name,age);
        titulacionRepository.save(nuevatitulacion);
         //devolvemos el objeto tipo nuevatitulacion
        return new ResponseEntity<>(nuevatitulacion,HttpStatus.OK);

    }
    //Recibe por json
    //damos de alta en la base de datos
    @PostMapping("/titulacion/add")
    public ResponseEntity<Object>titulacionAdd(@RequestBody Titulacion nuevatitulacion){
        titulacionRepository.save(nuevatitulacion);
        return new ResponseEntity<>(nuevatitulacion,HttpStatus.OK);

    }
    //modifica
    @PutMapping("/titulacion/{id}")
    public ResponseEntity<Object>titulacionUpdate(@PathVariable("id") Long id,
                                                  @RequestBody Titulacion nuevatitulacion) {
        //si no lo encuentra lanza esta excepcion
        titulacionRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        // si no pasamos la id el path, lo tenemos q poner asi
        //nuevatitulacion.setId(id);
        titulacionRepository.save(nuevatitulacion);
        return new ResponseEntity<>(nuevatitulacion,HttpStatus.OK);



    }

    @DeleteMapping("/titulacion/{id}")
    public ResponseEntity<Object>titulacionDelete(@PathVariable("id") Long id) throws Exception{
        Titulacion oldTitulacion=titulacionRepository.findById(id)
                                                     .orElseThrow(()->new EntityNotFoundException());
        titulacionRepository.delete(oldTitulacion);
        return new ResponseEntity<>(HttpStatus.OK);
        //optinal nos sale xq puede ser q sea nulo
        //Optional<Titulacion> oldTitulacion=titulacionRepository.findById(id);
       // si lo encuentra hacemos la operacion de borrar
        /*
        if (oldTitulacion.isPresent()){
            titulacionRepository.delete(oldTitulacion.get());//se añade el get xe hay q obtenerlo
            return new ResponseEntity<>(HttpStatus.OK);

        }

         */
        //contruye respuesta de no encontrado
        //return  ResponseEntity.notFound().build();
        //es lo mismo que lo de arriba
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);




    }



}
