package es.iesrafaelalberti.daw.dwes.proyecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter @Getter
//@Entity: con esto le decimos que la clase es un objeto persistente, es decir,
// una representación de una entidad correspondiente a una base de datos.
@Entity
public class Titulacion {
    //@Id: la variable que lleva esta anotación representa a la clave primaria.
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO): significa que esta variable
    // representa a un dato con la propiedad auto-increment igual a true.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "titulacion", cascade = CascadeType.ALL)
    Set<Trabajador> trabajtitulaciom = new HashSet<>();

    public Titulacion() {
    }

    public Titulacion(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public Titulacion(String name, Integer age ,Proyecto P) {
        this.name = name;
        this.age = age;


    }
}
