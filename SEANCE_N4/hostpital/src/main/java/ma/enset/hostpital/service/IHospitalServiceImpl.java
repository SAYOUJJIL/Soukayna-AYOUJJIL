package ma.enset.hostpital.service;

import ma.enset.hostpital.entities.Consultation;
import ma.enset.hostpital.entities.Medecin;
import ma.enset.hostpital.entities.Patient;
import ma.enset.hostpital.entities.RendezVous;
import ma.enset.hostpital.repositories.ConsultationRepository;
import ma.enset.hostpital.repositories.MedecinRepository;
import ma.enset.hostpital.repositories.PatientRepository;
import ma.enset.hostpital.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class IHospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    public IHospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Patient findByNomPatient(String name) {
        return patientRepository.findByNom(name);
    }

    @Override
    public Optional<Patient> findByIdPatient(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Medecin findByNomMedecin(String name) {
        return medecinRepository.findByNom(name);
    }

    @Override
    public Optional<RendezVous> findByIdRDV(Long id) {
        return rendezVousRepository.findById(id);
    }
}
