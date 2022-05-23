package ma.enset.hostpital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class RendezVous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    // par defaut c'est ordinal
    //mais si on veut afficher le texte
    //de chaque enumerateur
    @Enumerated(EnumType.STRING)
    private StatusRDV statusRDV;
    //c'est pas la peine de me donner patient en format json QUAND JE FAIT L'ajout
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Medecin medecin;
    @OneToOne(mappedBy = "rendezVous")
    private  Consultation consultation;

}
