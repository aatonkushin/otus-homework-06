package org.tonkushin.hw06.service.bookcomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tonkushin.hw06.model.Book;
import org.tonkushin.hw06.model.BookComment;
import org.tonkushin.hw06.repository.book.BookRepositoryJpa;
import org.tonkushin.hw06.repository.bookcomment.BookCommentRepositoryJpa;

import java.util.List;

@Service
public class BookCommentServiceImpl implements BookCommentService {
    private final BookCommentRepositoryJpa repository;
    private final BookRepositoryJpa bookRepository;

    @Autowired
    public BookCommentServiceImpl(BookCommentRepositoryJpa repository, BookRepositoryJpa bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookComment insert(BookComment item) {
        return repository.insert(item);
    }

    @Override
    public BookComment getById(long id) {
        return repository.getById(id);
    }

    @Override
    public List<BookComment> getAll() {
        return repository.getAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<BookComment> getCommentsByBookId(long bookId) {
        return repository.getCommentsByBookId(bookId);
    }

    @Override
    public void insert(long bookId, String commentText) {
        Book book = bookRepository.getById(bookId);
        insert(new BookComment(book, commentText));
    }
}
