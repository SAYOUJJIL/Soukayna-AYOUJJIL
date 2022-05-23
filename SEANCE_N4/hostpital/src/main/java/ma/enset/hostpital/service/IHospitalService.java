package ma.enset.hostpital.service;

import ma.enset.hostpital.entities.Consultation;
import ma.enset.hostpital.entities.Medecin;
import ma.enset.hostpital.entities.Patient;
import ma.enset.hostpital.entities.RendezVous;

import java.util.Optional;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
    Patient findByNomPatient(String name);
    Optional<Patient> findByIdPatient(Long id);
    Medecin findByNomMedecin(String name);
    Optional<RendezVous> findByIdRDV(Long id);


}
