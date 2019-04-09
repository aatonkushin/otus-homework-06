package org.tonkushin.hw06.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tonkushin.hw06.model.Author;
import org.tonkushin.hw06.model.Book;
import org.tonkushin.hw06.model.BookComment;
import org.tonkushin.hw06.model.Genre;
import org.tonkushin.hw06.repository.author.AuthorRepositoryJpa;
import org.tonkushin.hw06.repository.book.BookRepositoryJpa;
import org.tonkushin.hw06.repository.genre.GenreRepositoryJpa;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa repository;
    private final AuthorRepositoryJpa authorRepository;
    private final GenreRepositoryJpa genreRepository;

    @Autowired
    public BookServiceImpl(BookRepositoryJpa repository, AuthorRepositoryJpa authorRepository, GenreRepositoryJpa genreRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Book insert(Book item) {
        return repository.insert(item);
    }

    @Override
    public Book getById(long id) {
        return repository.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return repository.getAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Book insert(String name, long authorId, long genreId) {
        Author author = authorRepository.getById(authorId);
        Genre genre = genreRepository.getById(genreId);

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setGenre(genre);

        return repository.insert(book);
    }
}
