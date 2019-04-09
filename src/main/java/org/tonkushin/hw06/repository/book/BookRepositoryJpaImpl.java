package org.tonkushin.hw06.repository.book;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tonkushin.hw06.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class BookRepositoryJpaImpl implements BookRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Book insert(Book item) {
        em.persist(item);
        return item;
    }

    @Override
    public Book getById(long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.id = :id", Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
