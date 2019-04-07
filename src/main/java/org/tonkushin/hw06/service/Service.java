package org.tonkushin.hw06.service;

import java.util.List;

public interface Service<T> {
    T insert(T item);

    T getById(long id);

    List<T> getAll();

    void deleteById(long id);
}
