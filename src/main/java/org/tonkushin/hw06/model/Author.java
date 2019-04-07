package org.tonkushin.hw06.model;

import javax.persistence.*;


/**
 * Авторы книг
 */
@Entity
@Table(name = "AUTHORS")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id=-1;          //код в БД

    @Column(name = "name")
    private String name;      //Имя автора

    public Author() {
    }

    /**
     * Конструктор класса
     *
     * @param name имя автора
     */
    public Author(String name) {
        this.name = name;
    }

    public Author(long id, String name) {
        this.id = id;
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
     * Имя автора
     * @return Имя автора
     */
    public String getName() {
        return name;
    }

    /**
     * Имя автора
     * @param name Имя автора
     */
    public void setName(String name) {
        this.name = name;
    }
}

