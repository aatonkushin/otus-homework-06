package org.tonkushin.hw06.repository.author;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tonkushin.hw06.model.Author;

import java.util.List;

/**
 * Реализация репозитория для авторов книг
 */

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAll();
}
