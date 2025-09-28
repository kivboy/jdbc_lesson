package by.vadarod.javaee.entity;

import java.util.Objects;

public class Patient {
    private Long id;
    private String lastname;
    private String firstname;
    private Integer age;
    private Long diagnosisId;
    private Long roomId;

    public Patient(Long id, String lastname, String firstname, Integer age, Long diagnosisId, Long roomId) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.diagnosisId = diagnosisId;
        this.roomId = roomId;
    }
    public Patient(String lastname, String firstname, Integer age, Long diagnosisId, Long roomId) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.diagnosisId = diagnosisId;
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Long diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) &&
                Objects.equals(lastname, patient.lastname) &&
                Objects.equals(lastname, patient.firstname) &&
                Objects.equals(diagnosisId, patient.diagnosisId) && Objects.equals(roomId, patient.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, diagnosisId, roomId);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", age=" + age +
                ", diagnosisId=" + diagnosisId +
                ", roomId=" + roomId +
                '}';
    }
}
