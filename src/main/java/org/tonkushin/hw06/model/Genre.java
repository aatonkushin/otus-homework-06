package org.tonkushin.hw06.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Жанры книг
 */
@Entity
@Table(name = "GENRES")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id=-1;          //код в БД

    @Column(name = "name")
    private String name;         //Имя жанра

    /**
     * Конструктор по умолчанию
     */
    public Genre() {
    }

    /**
     * Конструктор с параметрами
     * @param name имя жанра
     */
    public Genre(String name) {
        this.name = name;
    }

    /**
     * код в БД
     * @return код в БД
     */
    public long getId() {
        return id;
    }

    /**
     * код в БД
     * @param id код в БД
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Имя жанра
     * @return Имя жанра
     */
    public String getName() {
        return name;
    }

    /**
     * Имя жанра
     * @param name Имя жанра
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return getId() == genre.getId() &&
                getName().equals(genre.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
