package org.tonkushin.hw06.service.book;

import org.tonkushin.hw06.model.Book;
import org.tonkushin.hw06.service.Service;

public interface BookService extends Service<Book> {
    Book insert(String name, long authorId, long genreId) throws Exception;
    Book addComment(Long bookId, String commentText) throws Exception;
}
