package org.tonkushin.hw06.repository.bookcomment;

import org.tonkushin.hw06.model.BookComment;
import org.tonkushin.hw06.repository.Repository;

import java.util.List;

public interface BookCommentRepositoryJpa extends Repository<BookComment> {
    List<BookComment> getCommentsByBookId(long bookId);
}
