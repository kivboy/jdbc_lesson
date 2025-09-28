package by.vadarod.javaee.service;

import by.vadarod.javaee.entity.Patient;
import by.vadarod.javaee.repository.PatientRepository;
import by.vadarod.javaee.repository.PatientRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepositoryImpl repository) {
        this.patientRepository = repository;
    }

    public List<Patient> getPatients() {
        return patientRepository.getAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public void addPatient(Patient patient) {
        patientRepository.addPatient(patient);
    }

    public void deletePatientById(Long patientId) {
        patientRepository.deletePatientById(patientId);
    }

//    public List<Patient> getPatientsByRoomId(Long roomId) {
//        List<Patient> patients = patientRepository.getAll();
//
//        return patients.stream()
//                .filter(a->a.getRoomId().equals(roomId))
//                .collect(Collectors.toList());
//    }

    public List<Patient> getPatientsByRoomId(Long roomId) {
        return patientRepository.findByRoomId(roomId);
    }

    public List<Patient> findByDiagnosisAndAge(String diagnosisName, Integer patientAge) {
        return patientRepository.findByDiagnosisAndAge(diagnosisName, patientAge);
    }

    public List<Patient> findPatientsOverAge(Integer patientAge) {
        return patientRepository.findPatientsOverAge(patientAge);
    }

    public int getAvgAgeInRoom(Long roomId) {
        return patientRepository.getAvgAgeInRoom(roomId);
    }

    public Long countAllPatients() {
        return patientRepository.countAllPatients();
    }
}
