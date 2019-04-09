package org.tonkushin.hw06.repository.bookcomment;

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
import org.tonkushin.hw06.model.BookComment;
import org.tonkushin.hw06.model.Genre;
import org.tonkushin.hw06.repository.author.AuthorRepositoryJpa;
import org.tonkushin.hw06.repository.author.AuthorRepositoryJpaImpl;
import org.tonkushin.hw06.repository.book.BookRepositoryJpa;
import org.tonkushin.hw06.repository.book.BookRepositoryJpaImpl;
import org.tonkushin.hw06.repository.genre.GenreRepositoryJpa;
import org.tonkushin.hw06.repository.genre.GenreRepositoryJpaImpl;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({BookRepositoryJpaImpl.class, AuthorRepositoryJpaImpl.class, GenreRepositoryJpaImpl.class, BookCommentRepositoryJpaImpl.class})
@TestPropertySource("/test.properties")
public class BookCommentRepositoryJpaImplTest {
    @Autowired
    private BookRepositoryJpa bookRepository;

    @Autowired
    private AuthorRepositoryJpa authorRepository;

    @Autowired
    private GenreRepositoryJpa genreRepository;

    @Autowired
    private BookCommentRepositoryJpa repository;


    @Test
    public void insert() {
        System.out.println("Проверяем, что после вставки новой записи, её код больше 0, а наименование совпадает с оригинальной записью...");
        BookComment bookComment = getBookComment();
        BookComment savedBookComment = repository.insert(bookComment);
        Assertions.assertThat(savedBookComment.getId()).isGreaterThan(-1);
        Assertions.assertThat(savedBookComment.getCommentText()).isEqualTo(bookComment.getCommentText());
        System.out.println("OK");
    }

    @Test
    public void getById() {
        System.out.println("Проверяем, что после вставки новой записи, мы находим её по коду...");
        BookComment bookCommentToInsert = getBookComment();
        BookComment savedBookComment = repository.insert(bookCommentToInsert);
        BookComment bookComment = repository.getById(savedBookComment.getId());
        Assertions.assertThat(bookComment.getCommentText()).isEqualTo(savedBookComment.getCommentText());
        System.out.println("ОК");
    }

    @Test
    public void getAll() {
        System.out.println("Проверяем, что после вставки одной записи, получаемый список состоит из одного элемента...");
        repository.insert(getBookComment());
        List<BookComment> items = repository.getAll();
        Assertions.assertThat(items.size()).isEqualTo(1);

        System.out.println("ОК");
    }

    @Test
    public void deleteById() {
        System.out.println("Проверяем, что после вставки новой записи, мы находим её по коду и удаляем её");
        BookComment bookCommentToInsert = getBookComment();
        BookComment savedBookComment = repository.insert(bookCommentToInsert);
        Assertions.assertThat(repository.getAll().size()).isEqualTo(1);
        repository.deleteById(savedBookComment.getId());
        Assertions.assertThat(repository.getAll().size()).isEqualTo(0);
        System.out.println("ОК");
    }

    @Test
    public void getCommentsByBookId() {
        System.out.println("Проверяем поиск коментариев по коду книги");
        BookComment bookCommentToInsert = getBookComment();
        BookComment savedBookComment = repository.insert(bookCommentToInsert);

        Assertions.assertThat(repository.getCommentsByBookId(savedBookComment.getBook().getId()).size()).isEqualTo(1);

        System.out.println("ОК");
    }

    BookComment getBookComment() {
        Author a = new Author("Тестовый автор");
        a = authorRepository.insert(a);

        Genre g = new Genre("Тестовый жанр");
        g = genreRepository.insert(g);

        Book b = new Book("Тестовое название", g, a);
        b = bookRepository.insert(b);

        return new BookComment(b, "Тестовый коментарий");
    }
}
