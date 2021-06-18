package es.iesrafaelalberti.daw.dwes.proyecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String imageUrl;
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "localidad", cascade = CascadeType.ALL)
    private Set<Trabajador> trabajlocalidad = new HashSet<>();


    public Localidad() {
    }


    public Localidad(String name) {
        this.name = name;
    }
}
