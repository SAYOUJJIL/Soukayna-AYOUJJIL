package ma.enset.patientsmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @NotEmpty
     @Size(min = 4, max = 40)
     private String nom;
     @Temporal(TemporalType.DATE)
     @DateTimeFormat(pattern = "yyyyy-MM-dd")
     private Date dateNaissance;
     private boolean malade;
     @DecimalMin("100")
     private int score;
}
