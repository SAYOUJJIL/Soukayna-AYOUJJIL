package ma.enset.jpaap;

import ma.enset.jpaap.entities.Patient;
import ma.enset.jpaap.entities.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // test de demarage de l'app
        //patientRepository.save(new Patient(null,"soukayna",new Date(),false,56));
        //patientRepository.save(new Patient(null,"hayat",new Date(),false,90));
        //patientRepository.save(new Patient(null,"aya",new Date(),false,100));

        // insertion de 100 patients
        for (int i=0;i<100;i++){
            patientRepository.save(new Patient(null,"hassan",new Date(),Math.random()>0.5?true:false,(int) (Math.random()*100)));
        }
        //Afficher listes des patients
        //List<Patient> patients = patientRepository.findAll();

        //pagination
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0,10));
        System.out.println("Total pages : "+patients.getTotalPages());
        System.out.println("Total elements : "+patients.getTotalElements());
        System.out.println("Num page : "+patients.getNumber());
        List<Patient> content = patients.getContent();

       // List<Patient> byMalade = patientRepository.findByMalade(true);
        Page<Patient> byMalade = patientRepository.findByMalade(true,PageRequest.of(0,4));

        List<Patient> patientsList = patientRepository.chercherPatients("%h%",40);


        for (Patient p : patientsList){
            System.out.println("=============================");
            System.out.print("Id : "+p.getId());
            System.out.print(", Nom : "+p.getNom());
            System.out.println(", Score : "+p.getScore());
            System.out.print("Date Naissance : "+p.getDateNaissance());
            System.out.println(", Malade : "+p.getMalade());
        }

        System.out.println("*******************************");
        Patient patient = patientRepository.findById(1L).orElse(null);
        //Patient patient = patientRepository.findById(1L).orElseThrow(()-> new RuntimeException("Patient not found"));
        if(patient!=null){
            System.out.println(patient.getNom());
            System.out.println(patient.getMalade());
        }
        patient.setScore(870);
        patientRepository.save(patient);
        patientRepository.deleteById(1L);
    }
}
