package ma.enset.gestionetudiant.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AppRole {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(unique = true)
    private String role;
    private String description;
}
