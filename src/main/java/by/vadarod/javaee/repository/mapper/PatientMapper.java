package by.vadarod.javaee.repository.mapper;

import by.vadarod.javaee.entity.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientMapper {
    public static ArrayList<Patient> mapPatients(ResultSet resultSet) throws SQLException {
        ArrayList<Patient> patients = new ArrayList<>();

        while (resultSet != null && resultSet.next()) {
            Long patientId = resultSet.getLong(1);
            String patientLastName = resultSet.getString(2);
            String patientFirstName = resultSet.getString(3);
            Integer patientAge = resultSet.getInt(4);
            Long patientDiagnosisId = resultSet.getLong(5);
            Long patientRoomId = resultSet.getLong(6);

            patients.add(new Patient(patientId,
                    patientLastName, patientFirstName, patientAge,
                    patientDiagnosisId, patientRoomId));
        }

        return patients;
    }
}
