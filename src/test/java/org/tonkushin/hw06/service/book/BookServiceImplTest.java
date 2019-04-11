package org.tonkushin.hw06.service.book;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tonkushin.hw06.model.Author;
import org.tonkushin.hw06.model.Book;
import org.tonkushin.hw06.model.BookComment;
import org.tonkushin.hw06.model.Genre;
import org.tonkushin.hw06.repository.author.AuthorRepository;
import org.tonkushin.hw06.repository.book.BookRepository;
import org.tonkushin.hw06.repository.genre.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@TestPropertySource("/test.properties")
public class BookServiceImplTest {

    @Test
    public void insert() {
        Book book = getBook();

        BookRepository repository = Mockito.mock(BookRepository.class);
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        GenreRepository genreRepository = Mockito.mock(GenreRepository.class);

        Mockito.when(repository.save(book)).thenReturn(book);

        BookService service = new BookServiceImpl(repository, authorRepository, genreRepository);
        Assertions.assertThat(service.insert(book)).isEqualTo(book);
    }

    @Test
    public void getById() throws Exception {
        Book book = getBook();
        Optional<Book> opt = Optional.of(book);

        BookRepository repository = Mockito.mock(BookRepository.class);
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        GenreRepository genreRepository = Mockito.mock(GenreRepository.class);

        Mockito.when(repository.findById(1L)).thenReturn(opt);

        BookService service = new BookServiceImpl(repository, authorRepository, genreRepository);
        Assertions.assertThat(service.getById(1L)).isEqualTo(book);
    }

    @Test
    public void getAll() {
        List<Book> books = new ArrayList<>(3);
        for (long i = 1; i <= 3; i++) {
            books.add(getBook());
        }

        BookRepository repository = Mockito.mock(BookRepository.class);
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        GenreRepository genreRepository = Mockito.mock(GenreRepository.class);
        Mockito.when(repository.findAll()).thenReturn(books);

        BookService service = new BookServiceImpl(repository, authorRepository, genreRepository);

        Assertions.assertThat(service.getAll().size()).isEqualTo(3L);
    }

    @Test
    public void insertTest() throws Exception {
        Book book = getBook();
        Optional<Book> opt = Optional.of(book);

        Author author = getAuthor();
        Optional<Author> optAuthor = Optional.of(author);

        Genre genre = getGenre();
        Optional<Genre> optGenre = Optional.of(genre);

        BookRepository repository = Mockito.mock(BookRepository.class);
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        GenreRepository genreRepository = Mockito.mock(GenreRepository.class);

        Mockito.when(repository.save(book)).thenReturn(book);
        Mockito.when(authorRepository.findById(1L)).thenReturn(optAuthor);
        Mockito.when(genreRepository.findById(1L)).thenReturn(optGenre);

        BookService service = new BookServiceImpl(repository, authorRepository, genreRepository);
        Assertions.assertThat(service.insert(book.getName(), 1, 1).getName()).isEqualTo(book.getName());
    }

    @Test
    public void addComment() throws Exception {
        Book book = getBook();
        book.getComments().add(new BookComment(book, "Комментарий"));

        Optional<Book> opt = Optional.of(book);

        Author author = getAuthor();
        Optional<Author> optAuthor = Optional.of(author);

        Genre genre = getGenre();
        Optional<Genre> optGenre = Optional.of(genre);

        BookRepository repository = Mockito.mock(BookRepository.class);
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        GenreRepository genreRepository = Mockito.mock(GenreRepository.class);

        Mockito.when(repository.save(book)).thenReturn(book);
        Mockito.when(repository.findById(1L)).thenReturn(opt);

        Mockito.when(authorRepository.findById(1L)).thenReturn(optAuthor);
        Mockito.when(genreRepository.findById(1L)).thenReturn(optGenre);

        BookService service = new BookServiceImpl(repository, authorRepository, genreRepository);
        Assertions.assertThat(service.addComment(1L, "Комментарий").getComments().isEmpty()).isFalse();
    }

    private Book getBook() {
        Author a = getAuthor();
        Genre g = getGenre();
        return new Book("Капитанская дочь", g, a);
    }

    private Author getAuthor() {
        return new Author("Пушкин");
    }

    private Genre getGenre() {
        return new Genre("Драма");
    }
}
