package ma.enset.jpaap.entities.repositories;

import ma.enset.jpaap.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    //CLASSE, type de id
    public List<Patient> findByMalade(boolean m);
   Page<Patient> findByMalade(boolean m, Pageable pageable);
   // liste des patients dont le score est inferieure a score
   List<Patient> findAllByMaladeAndScoreLessThan(boolean m,int score);
   List<Patient> findAllByMaladeIsTrueAndScoreLessThan(int score);
   List<Patient> findAllByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1, Date d2,String mc);
   @Query("select p from Patient p where p.nom like :x and  p.score<:y")
   List<Patient> chercherPatients(@Param("x") String nom, @Param("y") int scoreMin) ;

}
