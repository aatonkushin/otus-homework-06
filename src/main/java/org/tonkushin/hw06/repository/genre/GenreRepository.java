package org.tonkushin.hw06.repository.genre;

import org.springframework.data.repository.CrudRepository;
import org.tonkushin.hw06.model.Genre;

import java.util.List;

/**
 * Репозиторий для жанров
 */
public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAll();
}
