package es.iesrafaelalberti.daw.dwes.proyecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;
    String imageUrl;
    String token;
    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String password, Role rol) {
        this.username = username;
        // password is encoded before saving user object
        this.password = new BCryptPasswordEncoder().encode(password);
        roles.add(rol);
        // update M:N relation on the other side
        rol.getUsers().add(this);
    }


    public void addRole(Role rol) {
        roles.add(rol);
        rol.getUsers().add(this);
    }
}
