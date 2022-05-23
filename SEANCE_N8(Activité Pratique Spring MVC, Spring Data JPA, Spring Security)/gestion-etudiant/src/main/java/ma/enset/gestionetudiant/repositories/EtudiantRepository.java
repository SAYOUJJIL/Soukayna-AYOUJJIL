package ma.enset.gestionetudiant.repositories;

import ma.enset.gestionetudiant.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    Page<Etudiant> findByNomContaining (String nom, Pageable pageable);
}
