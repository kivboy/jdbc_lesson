package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Patient;

import java.util.List;

public interface PatientRepository {
    List<Patient> getAll();
    Patient findById(Long id);
    void addPatient(Patient patient);
    void deletePatientById(Long id);
    List<Patient> findByRoomId(Long roomId);
    List<Patient> findByDiagnosisAndAge(String diagnosisName, Integer patientAge);
    List<Patient> findPatientsOverAge(Integer patientAge);
    int getAvgAgeInRoom(Long roomId);
    Long countAllPatients();
}
