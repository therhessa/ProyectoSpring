package es.iesrafaelalberti.daw.dwes.proyecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter @Getter
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int horas;
    /*
    @ManyToMany(cascade = CascadeType.ALL)
     Set<Trabajador> trabajadors=new HashSet<>();

     */
    /*
    //mucho a muchos

    @ManyToMany(mappedBy = "proyectosparticipa")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
   // @JsonBackReference
    private Set<Trabajador> trabajdoresproyectos = new HashSet<>();
    */
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private Set<Trabajador> trabajproyecto = new HashSet<>();

    public Proyecto() {
    }

    public Proyecto(String name, int horas) {
        this.name = name;
        this.horas = horas;
    }


}
