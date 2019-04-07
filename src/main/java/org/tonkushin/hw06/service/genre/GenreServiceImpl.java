package org.tonkushin.hw06.service.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tonkushin.hw06.model.Genre;
import org.tonkushin.hw06.repository.genre.GenreRepositoryJpa;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepositoryJpa repository;

    @Autowired
    public GenreServiceImpl(GenreRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Genre insert(Genre item) {
        return repository.insert(item);
    }

    @Override
    public Genre getById(long id) {
        return repository.getById(id);
    }

    @Override
    public List<Genre> getAll() {
        return repository.getAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
