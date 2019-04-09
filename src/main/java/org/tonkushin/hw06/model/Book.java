package org.tonkushin.hw06.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1L;     //код в БД
    private String name;      //Наименование книги
    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;      //жанр книги
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;    //автор книги

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<BookComment> comments = new ArrayList<BookComment>();

    public Book() {
    }

    public Book(String name, Genre genre, Author author) {
        this.name = name;
        this.genre = genre;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<BookComment> getComments() {
        return comments;
    }

    public void setComments(List<BookComment> comments) {
        this.comments = comments;
    }
}
