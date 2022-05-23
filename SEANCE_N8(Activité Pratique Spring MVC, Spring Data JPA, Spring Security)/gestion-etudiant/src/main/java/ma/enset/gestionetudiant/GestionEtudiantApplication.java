package ma.enset.gestionetudiant;

import ma.enset.gestionetudiant.entities.Etudiant;
import ma.enset.gestionetudiant.entities.Genre;
import ma.enset.gestionetudiant.repositories.EtudiantRepository;
import ma.enset.gestionetudiant.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GestionEtudiantApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionEtudiantApplication.class, args);
    }

   @Bean
    CommandLineRunner commandLineRunner(EtudiantRepository etudiantRepository){
        return args -> {
            etudiantRepository.save(new Etudiant(null,"AYOUJJIL","Mohammed","Mohammed@gmail.com",new Date(), Genre.MASCULIN,false)) ;
            etudiantRepository.save(new Etudiant(null,"BOUMALLA","Khadija","Khadija@gmail.com",new Date(), Genre.FEMININ,false)) ;
            etudiantRepository.save(new Etudiant(null,"AIT LMADANI","Imran","Imran@gmail.com",new Date(), Genre.MASCULIN,true)) ;
            etudiantRepository.save(new Etudiant(null,"AIT LHADJ","Imane","Imane@gmail.com",new Date(), Genre.FEMININ,false)) ;
            etudiantRepository.save(new Etudiant(null,"AYOUJJIL","Hayat","Hayat@gmail.com",new Date(), Genre.FEMININ,true)) ;
            etudiantRepository.save(new Etudiant(null,"AYOUJJIL","Aya","Aya@gmail.com",new Date(), Genre.FEMININ,false)) ;
            etudiantRepository.save(new Etudiant(null,"AIT LMADANI","Douae","Douaen@gmail.com",new Date(), Genre.FEMININ,true)) ;
            etudiantRepository.save(new Etudiant(null,"AIT LHADJ","ALae","ALae@gmail.com",new Date(), Genre.FEMININ,true)) ;
            etudiantRepository.findAll().forEach(etudiant -> {
                System.out.println(etudiant.getNom());
            });
        };
    }
    @Bean
    CommandLineRunner SaveUser(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("soukayna","1234","1234");
            securityService.saveNewUser("hayat","1234","1234");
            securityService.saveNewUser("aya","1234","1234");
            securityService.savaNewRole("USER","");
            securityService.savaNewRole("ADMIN","");
            securityService.addRoleToUser("soukayna","USER");
            securityService.addRoleToUser("hayat","ADMIN");
        };

    }
}
