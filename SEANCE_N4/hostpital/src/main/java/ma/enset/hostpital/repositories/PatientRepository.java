package ma.enset.hostpital.repositories;

import ma.enset.hostpital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByNom(String name);

}
