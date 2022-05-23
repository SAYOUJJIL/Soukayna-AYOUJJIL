package ma.enset.hostpital;

import ma.enset.hostpital.entities.*;
import ma.enset.hostpital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HostpitalApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HostpitalApplication.class, args);
	}
	@Bean
	/*CommandLineRunner start(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository){
		return args -> {
			//patientRepository.save(new Patient(null,"Hassan",new Date(),false,null));
			Stream.of("Mohammed","Hssan","Najat").forEach(
					name->{
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						patientRepository.save(patient);

					}
			);

			Stream.of("Hayat","Aya","Soukayna").forEach(
					name->{
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						medecin.setEmail(name+"@gmail.com");
						medecinRepository.save(medecin);

					}
			);

			Patient patient = patientRepository.findById(1L).orElse(null);
			Patient patient1 = patientRepository.findByNom("Mohammed");
			Medecin medecin = medecinRepository.findByNom("Aya");
			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatusRDV(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			rendezVousRepository.save(rendezVous);

			Consultation consultation = new Consultation();
			RendezVous rendezVous1=rendezVousRepository.findById(1L).orElse(null);
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de consultation ....");

			consultationRepository.save(consultation);

		};
	}*/
	CommandLineRunner start(IHospitalService iHospitalService){
		return args->{
			Stream.of("Mohammed","Hssan","Najat").forEach(
					name->{
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						iHospitalService.savePatient(patient);

					}
			);

			Stream.of("Hayat","Aya","Soukayna").forEach(
					name->{
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						medecin.setEmail(name+"@gmail.com");
						iHospitalService.saveMedecin(medecin);

					}
			);

			Patient patient = iHospitalService.findByIdPatient(1L).orElse(null);
			Patient patient1 = iHospitalService.findByNomPatient("Mohammed");
			Medecin medecin = iHospitalService.findByNomMedecin("Aya");
			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatusRDV(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			iHospitalService.saveRendezVous(rendezVous);

			Consultation consultation = new Consultation();
			RendezVous rendezVous1=iHospitalService.findByIdRDV(1L).orElse(null);
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de consultation ....");

			iHospitalService.saveConsultation(consultation);
		};
	}
}
