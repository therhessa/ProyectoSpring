package es.iesrafaelalberti.daw.dwes.proyecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter  @Getter

public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String imageUrl;

/*
    @ManyToMany(mappedBy = "trabajadors")
    Set<Proyecto> projects=new HashSet<>();

 */
    @ManyToOne
    @JoinColumn()
    private Localidad localidad;
    @ManyToOne
    @JoinColumn()
    private Titulacion titulacion;
    @ManyToOne
    @JoinColumn()
    private Proyecto proyecto;
    /*
    //muchos A muchos
    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    @JoinTable(name = "partipar",
            joinColumns = @JoinColumn(name = "trabajador_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_id", referencedColumnName = "id"))
    private Set<Proyecto> proyectosparticipa = new HashSet<>();

    */
    public Trabajador() {
    }
    /*
    public Trabajador(String name, String surname, Localidad localidad, Titulacion titulacion) {
        this.name = name;
        this.surname = surname;
        this.localidad = localidad;
        this.titulacion = titulacion;
    }
    */
    public Trabajador(String name, String surname, Localidad localidad, Titulacion titulacion, Proyecto proyecto) {
        this.name = name;
        this.surname = surname;
        this.localidad = localidad;
        this.titulacion = titulacion;
        this.proyecto = proyecto;
    }
    /*
    public Trabajador(String name, String surname, Proyecto p,Localidad localidad, Titulacion titulacion) {
        this.name = name;
        this.surname = surname;
        proyectosparticipa.add(p);
        // update M:N relation on the other side
        p.getTrabajdoresproyectos().add(this);
        this.localidad = localidad;
        this.titulacion = titulacion;

    }
    public void addProyecto(Proyecto p) {
        proyectosparticipa.add(p);
        p.getTrabajdoresproyectos().add(this);
    }

     */

/*
    public Trabajador(String name, String surname, Set<Proyecto> projects, Localidad localidad, Titulacion titulacion) {
        this.name = name;
        this.surname = surname;
        this.projects = projects;
        this.localidad = localidad;
        this.titulacion = titulacion;
    }

      */
}
