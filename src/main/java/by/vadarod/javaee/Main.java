package by.vadarod.javaee;

import by.vadarod.javaee.config.PostgresConnection;
import by.vadarod.javaee.entity.Patient;
import by.vadarod.javaee.repository.PatientRepositoryImpl;
import by.vadarod.javaee.service.PatientService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        PatientService patientService = new PatientService(new PatientRepositoryImpl(new PostgresConnection()));

        // добавление пациента
        Patient patient1 = new Patient("Филатов","Александр",38,1L,3L);
        patientService.addPatient(patient1);

        System.out.println("Список всех пациентов:");
        // список всех пациентов
        List<Patient> patients = patientService.getPatients();

        for (Patient p:patients) {
            System.out.println(p);
        }

        System.out.println("Пациент по номеру 2:");

        Patient patient = patientService.getPatientById(2L);
        System.out.println(patient);

        System.out.println("Удаление пациента по номеру...");

        patientService.deletePatientById(7L);

        System.out.println("Список всех пациентов:");
        // список всех пациентов
        for (Patient p:patientService.getPatients()) {
            System.out.println(p);
        }

        System.out.println("Список всех пациентов из палаты №2:");
        for (Patient p:patientService.getPatientsByRoomId(2L)) {
            System.out.println(p);
        }

        System.out.println("Список по диагнозу и возрасту:");
        for (Patient p:patientService.findByDiagnosisAndAge("Сопли", 45)) {
            System.out.println(p);
        }

        System.out.println("Пациенты старше 40:");
        for (Patient p:patientService.findPatientsOverAge(40)) {
            System.out.println(p);
        }

        System.out.println("Средний возраст пациентов в палате №2: " + patientService.getAvgAgeInRoom(2L));

        System.out.println("Всего пациентов зарегистрировано: " + patientService.countAllPatients());
    }
}