package org.tonkushin.hw06.repository;

import java.util.List;

/**
 * Общий интерфейс для всех репозиториев
 * @param <T> тип модели данных
 */
public interface Repository<T> {
    T insert(T item);

    T getById(long id);

    List<T> getAll();

    void deleteById(long id);
}
