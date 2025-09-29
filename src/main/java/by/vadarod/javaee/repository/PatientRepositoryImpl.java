package by.vadarod.javaee.repository;

import by.vadarod.javaee.config.JdbcConnection;
import by.vadarod.javaee.entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.vadarod.javaee.repository.mapper.PatientMapper.mapPatients;

public class PatientRepositoryImpl implements PatientRepository{

    private final String SELECT_ALL_PATIENTS = "SELECT * FROM hospital.patients";
    private final String FIND_PATIENT_BY_ID = "SELECT * FROM hospital.patients p WHERE p.id = ?";
    private final String FIND_PATIENTS_BY_ROOMID = "SELECT * FROM hospital.patients p WHERE p.room_id = ?";
    private final String ADD_NEW_PATIENT = "INSERT INTO hospital.patients(lastname, firstname, age, diagnosis_id, room_id) VALUES (?, ?, ?, ?, ?)";
    private final String DELETE_PATIENT_BY_ID = "DELETE FROM hospital.patients p WHERE p.id = ?";
    private final String FIND_PATIENTS_BY_DIAGNOSIS_AND_AGE = "SELECT * FROM hospital.patients p JOIN hospital.diagnosises d ON d.id = p.diagnosis_id WHERE d.\"name\" = ? AND p.age = ?";
    private final String FIND_PATIENTS_OVER_AGE = "SELECT * FROM hospital.patients p WHERE p.age > ? ORDER BY p.age DESC";
    private final String AVG_PATIENTS_AGE_IN_ROOM = "SELECT ROUND(AVG(age)) AS avg_age FROM hospital.patients p WHERE p.room_id = ? GROUP BY p.room_id";
    private final String COUNT_ALL_PATIENTS = "SELECT COUNT(*) FROM hospital.patients p";

    private final JdbcConnection jdbcConnection;

    public PatientRepositoryImpl(JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    @Override
    public List<Patient> getAll() {

        try (Connection connection = jdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeQuery(SELECT_ALL_PATIENTS);
            ResultSet resultSet = statement.getResultSet();
            return mapPatients(resultSet);

        } catch (SQLException e)  {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Patient findById(Long id) {

        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PATIENT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Patient> patients = mapPatients(resultSet);
            return patients.get(0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPatient(Patient patient) {

        if (patient == null) {
            return;
        }

        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_PATIENT);

            preparedStatement.setString(1, patient.getLastname());
            preparedStatement.setString(2, patient.getFirstname());
            preparedStatement.setInt(3, patient.getAge());
            preparedStatement.setLong(4, patient.getDiagnosisId());
            preparedStatement.setLong(5, patient.getRoomId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePatientById(Long id) {
        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PATIENT_BY_ID);

            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> findByRoomId(Long roomId) {

        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PATIENTS_BY_ROOMID);
            preparedStatement.setLong(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return mapPatients(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> findByDiagnosisAndAge(String diagnosisName, Integer patientAge) {

        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PATIENTS_BY_DIAGNOSIS_AND_AGE);

            preparedStatement.setString(1, diagnosisName);
            preparedStatement.setInt(2, patientAge);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapPatients(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> findPatientsOverAge(Integer patientAge) {

        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PATIENTS_OVER_AGE);

            preparedStatement.setInt(1, patientAge);
            ResultSet resultSet = preparedStatement.executeQuery();

           return mapPatients(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getAvgAgeInRoom(Long roomId) {

        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AVG_PATIENTS_AGE_IN_ROOM);

            preparedStatement.setLong(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();

            int avgAge = 0;
            if (resultSet != null && resultSet.next()) {
                avgAge = resultSet.getInt(1);
            }
            return avgAge;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long countAllPatients() {
        try (Connection connection = jdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeQuery(COUNT_ALL_PATIENTS);
            ResultSet resultSet = statement.getResultSet();

            Long count = 0L;
            if (resultSet != null && resultSet.next()) {
                count = resultSet.getLong(1);
            }
            return count;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
