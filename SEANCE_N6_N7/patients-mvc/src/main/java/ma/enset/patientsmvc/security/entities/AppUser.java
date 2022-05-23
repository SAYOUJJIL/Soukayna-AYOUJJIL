package ma.enset.patientsmvc.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AppUser {

    @Id
    private String id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean active;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<AppRole> appRoles = new ArrayList<>();
}