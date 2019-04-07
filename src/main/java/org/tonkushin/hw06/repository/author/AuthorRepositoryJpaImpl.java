package org.tonkushin.hw06.repository.author;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tonkushin.hw06.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Реализация репозитория для авторов книг
 */

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Author insert(Author item) {
        em.persist(item);
        return item;
    }

    @Override
    public Author getById(long id) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.id = :id", Author.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Author a where a.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
