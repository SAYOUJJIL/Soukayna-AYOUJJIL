package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import ma.enset.patientsmvc.security.service.ISecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsMvcApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	//@Bean
	CommandLineRunner start(PatientRepository patientRepository){
		return args->{
			Stream.of("Hayat", "Aya", "Malak", "Adam").forEach(nom->{
				patientRepository.save(new Patient(null, nom, new Date(), Math.random()<0.5?false:true, 129));
			});
			patientRepository.findAll().forEach(p->{
				System.out.println(p.getNom());
			});
		};
	}
	//@Bean
	CommandLineRunner saveUsers(ISecurityService iSecurityService){
		return args -> {
			iSecurityService.saveNewUser("Hayat","1234","1234");
			iSecurityService.saveNewUser("Aya","1234","1234");
			iSecurityService.saveNewUser("Soukayna","1234","1234");

			iSecurityService.saveNewRole("ADMIN","");
			iSecurityService.saveNewRole("USER","");

			iSecurityService.addRoleToUser("Soukayna","ADMIN");
			iSecurityService.addRoleToUser("Soukayna","USER");
			iSecurityService.addRoleToUser("Aya","USER");
			iSecurityService.addRoleToUser("Hayat","USER");
		};
	}

}
