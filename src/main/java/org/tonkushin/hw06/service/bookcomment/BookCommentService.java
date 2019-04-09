package org.tonkushin.hw06.service.bookcomment;

import org.tonkushin.hw06.model.BookComment;
import org.tonkushin.hw06.service.Service;

import java.util.List;

public interface BookCommentService extends Service<BookComment> {
    List<BookComment> getCommentsByBookId(long bookId);

    void insert(long bookId, String commentText);
}
