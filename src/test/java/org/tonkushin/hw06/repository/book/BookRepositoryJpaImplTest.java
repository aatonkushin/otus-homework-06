package org.tonkushin.hw06.repository.book;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw06.model.Author;
import org.tonkushin.hw06.model.Book;
import org.tonkushin.hw06.model.Genre;
import org.tonkushin.hw06.repository.author.AuthorRepositoryJpa;
import org.tonkushin.hw06.repository.author.AuthorRepositoryJpaImpl;
import org.tonkushin.hw06.repository.genre.GenreRepositoryJpa;
import org.tonkushin.hw06.repository.genre.GenreRepositoryJpaImpl;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({BookRepositoryJpaImpl.class, AuthorRepositoryJpaImpl.class, GenreRepositoryJpaImpl.class})
@TestPropertySource("/test.properties")
public class BookRepositoryJpaImplTest {

    @Autowired
    private BookRepositoryJpa repository;

    @Autowired
    private AuthorRepositoryJpa authorRepository;

    @Autowired
    private GenreRepositoryJpa genreRepository;

    @Test
    public void insert() {
        System.out.println("Проверяем, что после вставки новой записи, её код больше 0, а наименование совпадает с оригинальной записью...");
        Book book = getBook();
        Book savedBook = repository.insert(book);
        Assertions.assertThat(savedBook.getId()).isGreaterThan(-1);
        Assertions.assertThat(savedBook.getName()).isEqualTo(book.getName());
        System.out.println("OK");
    }

    @Test
    public void getById() {
        System.out.println("Проверяем, что после вставки новой записи, мы находим её по коду...");
        Book bookToInsert = getBook();
        Book savedBook = repository.insert(bookToInsert);
        Book book = repository.getById(savedBook.getId());
        Assertions.assertThat(book.getName()).isEqualTo(savedBook.getName());
        System.out.println("ОК");
    }

    @Test
    public void getAll() {
        System.out.println("Проверяем, что после вставки одной записи, получаемый список состоит из одного элемента...");
        repository.insert(getBook());
        List<Book> items = repository.getAll();
        Assertions.assertThat(items.size()).isEqualTo(1);

        System.out.println("ОК");
    }

    @Test
    public void deleteById() {
        System.out.println("Проверяем, что после вставки новой записи, мы находим её по коду и удаляем её");
        Book bookToInsert = getBook();
        Book savedBook = repository.insert(bookToInsert);
        Assertions.assertThat(repository.getAll().size()).isEqualTo(1);
        repository.deleteById(savedBook.getId());
        Assertions.assertThat(repository.getAll().size()).isEqualTo(0);
        System.out.println("ОК");
    }

    private Book getBook() {
        Author a = new Author("Тестовый автор");
        a = authorRepository.insert(a);

        Genre g = new Genre("Тестовый жанр");
        g = genreRepository.insert(g);

        return new Book("Тестовое название", g, a);
    }
}
