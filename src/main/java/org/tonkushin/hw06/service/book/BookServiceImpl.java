package org.tonkushin.hw06.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tonkushin.hw06.model.Author;
import org.tonkushin.hw06.model.Book;
import org.tonkushin.hw06.model.BookComment;
import org.tonkushin.hw06.model.Genre;
import org.tonkushin.hw06.repository.author.AuthorRepository;
import org.tonkushin.hw06.repository.book.BookRepository;
import org.tonkushin.hw06.repository.genre.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public BookServiceImpl(BookRepository repository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Book insert(Book item) {
        return repository.save(item);
    }

    @Override
    public Book getById(long id) throws Exception {
        Optional<Book> opt = repository.findById(id);

        if (opt.isPresent())
            return opt.get();

        throw new Exception("Book not found");
    }

    @Override
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Book insert(String name, long authorId, long genreId) throws Exception {
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Genre> genre = genreRepository.findById(genreId);

        Book book = new Book();
        book.setName(name);

        if (author.isPresent())
            book.setAuthor(author.get());
        else
            throw new Exception("Author not found");

        if (genre.isPresent())
            book.setGenre(genre.get());
        else
            throw new Exception("Genre not found");

        return repository.save(book);
    }

    @Override
    @Transactional
    public Book addComment(Long bookId, String commentText) throws Exception {
        Book book = getById(bookId);
        BookComment comment = new BookComment();
        comment.setBook(book);
        comment.setCommentText(commentText);
        book.getComments().add(comment);
        return repository.save(book);
    }
}
