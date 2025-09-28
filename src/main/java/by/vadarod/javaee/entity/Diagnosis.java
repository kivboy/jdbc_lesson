package by.vadarod.javaee.entity;

import java.util.Objects;

public class Diagnosis {
    private Long id;
    private String name;

    public Diagnosis(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    void setId(Long id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // 1. Сравнение ссылок
        if (o == null || getClass() != o.getClass()) return false; // 2. Проверка на null и класс
        Diagnosis that = (Diagnosis) o; // 3. Приведение типа
        // return id != null && Objects.equals(id, that.id);
        return Objects.equals(id, that.id) && Objects.equals(name, that.name); // 4. Сравнение полей

    }

    @Override
    public int hashCode() {
        //return getClass().hashCode();
        return Objects.hash(id, name); // 5. Создание хеш-кода из полей
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
