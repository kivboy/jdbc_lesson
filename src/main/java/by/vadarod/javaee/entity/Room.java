package by.vadarod.javaee.entity;

import java.util.Objects;

public class Room {
    private Long id;
    private String name;

    public Room(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    Long getId() {
        return id;
    }

    String getName() {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room that = (Room) o;
        //return id != null && Objects.equals(id, room.id);
        return Objects.equals(id, that.id) && Objects.equals(name, that.name); // 4. Сравнение полей
    }

    @Override
    public int hashCode() {
        // return getClass().hashCode();
        return Objects.hash(id, name); // 5. Создание хеш-кода из полей
    }
}
