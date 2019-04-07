package org.tonkushin.hw06.repository.genre;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tonkushin.hw06.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre insert(Genre item) {
        em.persist(item);
        return item;
    }

    @Override
    public Genre getById(long id) {
        TypedQuery<Genre> query = em.createQuery("select a from Genre a where a.id = :id", Genre.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select a from Genre a", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Genre a where a.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
