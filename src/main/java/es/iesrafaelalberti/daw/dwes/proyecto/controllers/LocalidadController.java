package es.iesrafaelalberti.daw.dwes.proyecto.controllers;

import es.iesrafaelalberti.daw.dwes.proyecto.model.Localidad;
import es.iesrafaelalberti.daw.dwes.proyecto.model.Titulacion;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class LocalidadController {
    @Autowired
    private LocalidadRepository localidadRepository;

    @GetMapping(value = "/localidades")
    public ResponseEntity<Object> localidadesList() {
        return new ResponseEntity<>(localidadRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/localidades/detail")
    public ResponseEntity<Object> localidadDetail(@RequestParam("id") Long id){
        //asi controlamos la gestion de errores
        Localidad localidad= localidadRepository.findById(id).orElseThrow(()->new EntityNotFoundException());

        return new ResponseEntity<>(localidad, HttpStatus.OK);

    }
    //para dar de alta post
    //recibe por formulario
    @PostMapping("/localidades")
    public ResponseEntity<Object>localidadnadd(@RequestParam("name")String name){
        //para dar de alta en la base de datos añado lo siguiente:
        Localidad nuevalocalidad=new Localidad(name);
        localidadRepository.save(nuevalocalidad);
        return new ResponseEntity<>(nuevalocalidad,HttpStatus.OK);

    }
    //Recibe por json
    //damos de alta en la base de datos
    @PostMapping("/localidades/add")
    public ResponseEntity<Object>localidadesAdd(@RequestBody Localidad nuevalocalidad){
        localidadRepository.save(nuevalocalidad);
        return new ResponseEntity<>(nuevalocalidad,HttpStatus.OK);

    }
    //modifica
    @PutMapping("/localidades/{id}")
    public ResponseEntity<Object>localidadUpdate(@PathVariable("id") Long id,
                                                  @RequestBody Localidad nuevalocalidad) {
        //si no lo encuentra lanza esta excepcion
       localidadRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        // si no pasamos la id el path, lo tenemos q poner asi
        //nuevatitulacion.setId(id);
        localidadRepository.save(nuevalocalidad);
        return new ResponseEntity<>(nuevalocalidad,HttpStatus.OK);



    }

    @DeleteMapping("/localidades/{id}")
    public ResponseEntity<Object>localidadDelete(@PathVariable("id") Long id) throws Exception{
        Localidad oldLocalidad=localidadRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException());
        localidadRepository.delete(oldLocalidad);
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
