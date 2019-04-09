package org.tonkushin.hw06.repository.bookcomment;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tonkushin.hw06.model.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class BookCommentRepositoryJpaImpl implements BookCommentRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Override
    public BookComment insert(BookComment item) {
        em.persist(item);
        return item;
    }

    @Override
    public BookComment getById(long id) {
        TypedQuery<BookComment> query = em.createQuery("select b from BookComment b where b.id = :id", BookComment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<BookComment> getAll() {
        TypedQuery<BookComment> query = em.createQuery("select b from BookComment b", BookComment.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from BookComment b where b.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<BookComment> getCommentsByBookId(long bookId) {
        TypedQuery<BookComment> query = em.createQuery("select b from BookComment b where b.book.id = :bookId", BookComment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }
}
