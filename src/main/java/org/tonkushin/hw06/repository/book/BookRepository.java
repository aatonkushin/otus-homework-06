package org.tonkushin.hw06.repository.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.tonkushin.hw06.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для книг
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query("select b from Book b left join fetch b.author left join fetch b.genre")
    List<Book> findAll();

    @Query("select b from Book b left join fetch b.author left join fetch b.genre where b.id = :id")
    Optional<Book> findById(Long id);
}
