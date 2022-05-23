package ma.enset.jpaenset.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descri;
    @Column(unique = true,length = 20)
    private String roleName;
    //speciser le nom de la jointure
    //@JoinTable(name = "USERS_ROLES",nom des clés etgrangeres )
    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> users = new ArrayList<>();
}
