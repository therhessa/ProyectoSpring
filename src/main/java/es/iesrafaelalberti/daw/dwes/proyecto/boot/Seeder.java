package es.iesrafaelalberti.daw.dwes.proyecto.boot;

import es.iesrafaelalberti.daw.dwes.proyecto.model.*;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
    //para acceder a los datos guardados de la entidad titulacion.
    @Autowired
    private TitulacionRepository titulacionRepository;
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private TrabajadorRepository trabajadorRepository;
    @Autowired
    private LocalidadRepository localidadRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        Titulacion t1=titulacionRepository.save(new Titulacion("Ingenieria de sistemas", 4));
        Titulacion t2= titulacionRepository.save(new Titulacion( "Ingenieria de Caminos", 4));
        Titulacion t3= titulacionRepository.save(new Titulacion( "Diseño aplicaciones web", 2));
        Proyecto p1= proyectoRepository.save(new Proyecto( "aplicacion web", 25));
        Proyecto p2= proyectoRepository.save(new Proyecto( "construccion puente", 2000));
        Proyecto p3= proyectoRepository.save(new Proyecto( "construccion rotonda", 2000));
        Proyecto p4= proyectoRepository.save(new Proyecto( "construccion garaje", 2000));
        Localidad l1= localidadRepository.save(new Localidad("vejer"));
        Localidad l2= localidadRepository.save(new Localidad("conil"));
        Trabajador x=trabajadorRepository.save(new Trabajador("Maria","Flores",l2,t2, p1));
        //Trabajador y  =trabajadorRepository.save(new Trabajador("Tere","Melero",l1,t3, p1));
        Trabajador tr1=trabajadorRepository.save(new Trabajador("Tere","Melero",l1,t3, p2));
        Trabajador tr5  = trabajadorRepository.save(new Trabajador("juan", "sanchez",l1,t2,p3));
        //Trabajador tr2 = trabajadorRepository.save(new Trabajador("paco", "perez",p1,l1,t2));
        //Trabajador tr3 = trabajadorRepository.save(new Trabajador("rocio", "melero",p1,l1,t2));

        Role r1 = roleRepository.save(new Role("ROLE_ADMIN"));
        //Role r2 = roleRepository.save(new Role("ROLE_USER"));
        Role r3 = roleRepository.save(new Role("ROLE_GOD"));
        User u1 = userRepository.save(new User("juan", "juanito", r1));
        User u2 = userRepository.save(new User("tere", "tere", r1));
        User u3 = userRepository.save(new User("rocio", "pestillo", r3));

    }
}
